package sms.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
// TODO: 2019/5/19 change the select result

public class ClassUtil {
  private JdbcUtil db;

  public ClassUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }


  /**
   * @return -1 for fail; else the count of table rows
   */
  public int getClassCount() {
    String sql = "select count(0) as count\n" +
            "from class;";
    int res = -1;
    try {
      res = Integer.parseInt(db.dbFindSingleResult(sql, null).
              get("count").toString());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Map<String, Object>> findAllClassPage(int page, int singlePageRowsNum) {
    String sql = "select c.*, m.major_name as major_name, c2.course_name as course_name, s.semester, s.year, c2.acronym\n" +
            "from class c\n" +
            "         join course c2 on c.course_id = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "order by c.class_id\n" +
            "limit ?, ?;";
    List<Map<String, Object>> res = null;
    List<Object> params = new ArrayList<>();
    int start = (page - 1) * singlePageRowsNum;
    int end = page * singlePageRowsNum;
    params.add(start);
    params.add(end);
    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public Map<String, Object> findClassByID(int id) {
    String sql = "select c.*, m.name as major_name, c2.name as course_name, s.semester, s.year\n" +
            "from class c\n" +
            "         join course c2 on c.course = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "where c.class_id = ?;";
    Map<String, Object> res = null;
    List<Object> params = new ArrayList<>();
    params.add(id);
    try {
      res = db.dbFindSingleResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Map<String, Object>> findClassByCourseID(int id) {
    String sql = "select * from Class where course_id = ?;";
    List<Object> params = new ArrayList<>();
    List<Map<String, Object>> res = null;
    params.add(id);
    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  /**
   * 只有当前学期的班级，不会出现之前学期的班级
   *
   * @param id
   * @return
   */
  public List<Map<String, Object>> findCurrentSemesterClassByInstructorID(int id) {
    String sql = "select distinct year,\n" +
            "                semester,\n" +
            "                c.course_id,\n" +
            "                course_name,\n" +
            "                c.class_id\n" +
            "from class c\n" +
            "         join course c2\n" +
            "              on c.course_id = c2.course_id\n" +
            "         join lesson l on c.class_id = l.class_id\n" +
            "         join instructor i on l.instructor_id = i.instructor_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "\n" +
            "where i.instructor_id = ?\n" +
            "  and year = DATE_FORMAT(now(), '%Y')\n" +
            "  and semester = if(DATE_FORMAT(now(), '%M') in\n" +
            "                    ('January', 'February', 'March', 'April', 'May', 'June'), 1, 2);";
    List<Object> params = new ArrayList<>();
    List<Map<String, Object>> res = null;
    params.add(id);
    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Map<String, Object>> findClassByScheduleID(int id) {
    String sql = "select * from Class where schedule_id = ?;";
    List<Object> params = new ArrayList<>();
    List<Map<String, Object>> res = null;
    params.add(id);
    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public boolean addClass(Map<String, Object> map) {
    boolean flag = false;
    String sql = "insert into Class(course_id, schedule_id) value (?,?)";
    List<Object> params = new ArrayList<>();
    params.add(map.get("course_id"));
    params.add(map.get("schedule_id"));

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean updateClass(Map<String, Object> map) {
    boolean flag = false;
    String sql = "update Class set (course_id = ?, schedule_id = ?) where class_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(map.get("course_id"));
    params.add(map.get("schedule_id"));
    params.add(map.get("class_id"));

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean deleteClass(int id) {
    boolean flag = false;
    String sql = "delete from Class where class_id = ?";
    List<Object> params = new ArrayList<>();
    params.add(id);
    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  /**
   * GC销毁对象时调用, 断开数据库连接
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    if (db != null) {
      db.releaseConn();
      db = null;
    }
  }

}

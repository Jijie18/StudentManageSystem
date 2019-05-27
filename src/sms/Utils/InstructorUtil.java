package sms.Utils;

import sms.beans.Instructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class InstructorUtil {
  private JdbcUtil db;

  public InstructorUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }
  /**
   * @return -1 for fail; else the count of table rows
   */
  public int getInstructorCount() {
    String sql = "select count(0) as count\n" +
            "from instructor;";
    int res = -1;
    try {
      res = Integer.parseInt(db.dbFindSingleResult(sql, null).
              get("count").toString());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }
  /**
   * @return all instructors in table
   */
  public List<Map<String, Object>> findAllInstructorsPages(int page, int singlePageRowsNum) {
    String sql = "select department_name, major_name, i.*\n" +
            "from Instructor i\n" +
            "         join major m on i.major_id = m.major_id\n" +
            "         join department d on m.department_id = d.department_id\n" +
            "order by instructor_id\n" +
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


  /**
   * @param id use id as instructor_id to find data in table
   * @return row of the instructor
   */
  public Map<String, Object> findInstructorByID(int id) {
    String sql = "select department_name, major_name, i.*\n" +
            "from Instructor i\n" +
            "         join major m on i.major_id = m.major_id\n" +
            "         join department d on m.department_id = d.department_id\n" +
            "where instructor_id = ?;";
    List<Object> params = new ArrayList<>();
    Map<String, Object> res = null;
    params.add(id);
    try {
      res = db.dbFindSingleResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }


  /**
   * @param id major id
   * @return all instructor department_name, major_name, i.* data in this major
   */
  public Map<String, Object> findInstructorByMajorID(int id) {
    String sql = "select department_name,\n" +
            "       major_name,\n" +
            "       i.*\n" +
            "from Instructor i\n" +
            "         join major m\n" +
            "              on i.major_id = m.major_id\n" +
            "         join department d on m.department_id = d.department_id\n" +
            "where i.major_id = ?;";
    List<Object> params = new ArrayList<>();
    Map<String, Object> res = null;
    params.add(id);
    try {
      res = db.dbFindSingleResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }
  public boolean changeInstructorPassword(int id, String newPassword) {
    boolean flag = false;
    String sql = "update instructor\n" +
            "set password = ?\n" +
            "where instructor_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(newPassword);
    params.add(id);
    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean addInstructor(Map<String, Object> map) {
    boolean flag = false;
    String sql = "insert into Instructor (major_id, instructor_name, age, sex, joined_date) value (?,?,?,?,?)";
    List<Object> params = new ArrayList<>();
    params.add(map.get("major_id"));
    params.add(map.get("instructor_name"));
    params.add(map.get("age"));
    params.add(map.get("sex"));
    params.add(map.get("joined_date"));


    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean updateInstructor(Map<String, Object> map) {
    boolean flag = false;
    String sql = "update Instructor\n" +
            "set major_id=?,\n" +
            "    name=?,\n" +
            "    age=?,\n" +
            "    sex=?,\n" +
            "    joined_date=?\n" +
            "where instructor_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(map.get("major_id"));
    params.add(map.get("name"));
    params.add(map.get("age"));
    params.add(map.get("sex"));
    params.add(map.get("joined_date"));
    params.add(map.get("instructor_id"));
    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean deleteInstructor(int id) {
    boolean flag = false;
    String sql = "delete from Instructor where instructor_id = ?";
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
    System.out.println(this.getClass() + "已经销毁");
  }
}

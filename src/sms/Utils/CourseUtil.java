package sms.Utils;

import sms.Session;
import sms.beans.Student;

import java.sql.SQLException;
import java.util.*;

public class CourseUtil {
  private JdbcUtil db;

  public CourseUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }

  public List<Map<String, Object>> findAllCourses() {
    String sql = "select m.major_name as major_name, c.*\n" +
            "from course c\n" +
            "         join major m on c.major_id = m.major_id\n" +
            "order by course_id;";
    List<Map<String, Object>> res = null;
    try {
      res = db.dbFindMultiResult(sql, null);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Map<String, Object>> findCourseByStudentID(int id) {
    String sql = "select year, semester, co.course_id, co.acronym, course_name as course_name, c.class_id\n" +
            "from student_class sc\n" +
            "         join class c on sc.class_id = c.class_id\n" +
            "         join course co on co.course_id = c.course_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "where student_id = ?\n" +
            "  and year = DATE_FORMAT(now(), '%Y')\n" +
            "  and semester = if(DATE_FORMAT(now(), '%M') in ('January', 'February', 'March', 'April', 'May', 'June'), 1, 2);\n";
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

  public Map<String, Object> findCourseByID(int id) {
    String sql = "select m.major_name as major_name, c.*\n" +
            "from course c\n" +
            "         join major m on c.major_id = m.major_id\n" +
            "where course_id = ?;";
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

  /**
   * 查询该专业开的所有课
   *
   * @param id majorID
   * @return all corresponding courses
   */
  public List<Map<String, Object>> findCourseByMajorID(int id) {
    String sql = "select m.major_name as major_name, c.*\n" +
            "from course c\n" +
            "         join major m on c.major_id = m.major_id\n" +
            "where c.major_id = ?;";
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
   * 找到自己当前尚未修读的专业相关课程, 只有成绩大于60才算已修读
   *
   * @param student_id student's id 从Session获取
   * @param major_id the id of major 从Session获取
   * @param course_type 必修 BX,专业先修 Z,选修 X
   * @return all course name and id which is wanted
   */
  public List<Map<String, Object>> findGraduNeedCourse(int student_id, int major_id, String course_type) {
    List<Map<String, Object>> res = null;

      String sql = "select c.class_id, tmp.course_id, course_name, acronym, tmp.capacity, type_require\n" +
              "from class c\n" +
              "         join (select table1.course_name,\n" +
              "                      table1.course_id,\n" +
              "                      capacity,\n" +
              "\n" +
              "                      acronym,\n" +
              "                      case\n" +
              "                          when type_require =\n" +
              "                               'BX' then\n" +
              "                              '必修'\n" +
              "                          when type_require =\n" +
              "                               'X' then\n" +
              "                              '选修'\n" +
              "                          when type_require =\n" +
              "                               'Z' then\n" +
              "                              '专业先修' end as type_require\n" +
              "               from (\n" +
              "                     (select co.course_name, co.course_id as course_id, type_require, capacity, acronym\n" +
              "                      from course_major cm\n" +
              "                               join course co on cm.course_id = co.course_id\n" +
              "                               join class c2 on co.course_id = c2.course_id\n" +
              "                      where (cm.major_id = ? and type_require = ?)) table1\n" +
              "                        left join (select co.course_name as course_name, co.course_id as course_id\n" +
              "                                   from student_class_score sc\n" +
              "                                            join class c\n" +
              "                                                 on sc.class_id = c.class_id\n" +
              "                                            join course co on c.course_id = co.course_id\n" +
              "                                            join course_major cm on co.course_id = cm.course_id\n" +
              "                                   where sc.student_id = ?\n" +
              "                                     and sc.score >= 60\n" +
              "                                     and cm.type_require = ?\n" +
              "                                     AND cm.major_id = ?) table2 on table1.course_id = table2.course_id\n" +
              "                   )\n" +
              "               where table2.course_id is null) tmp on c.course_id = tmp.course_id\n" +
              "         join schedule s on c.schedule_id = s.schedule_id\n" +
              "where year = DATE_FORMAT(now(), '%Y')\n" +
              "  and semester = if(DATE_FORMAT(now(), '%M') in ('January', 'February', 'March', 'April', 'May', 'June'), 1, 2);";
      List<Object> params = new ArrayList<>();
      params.add(major_id);
      params.add(course_type.trim().toUpperCase());
      params.add(student_id);
      params.add(major_id);
      params.add(course_type.trim().toUpperCase());

      try {
        res = db.dbFindMultiResult(sql, params);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return res;
  }

  // TODO: 2019/5/23 bug
  public List<Map<String, Object>> findOtherMajorCourse(int student_id, int major_id) {
    List<Map<String, Object>> res = null;

    String sql = "select c.class_id, tmp.course_id, course_name, acronym, capacity, major_name\n" +
            "from class c\n" +
            "         join (select table1.course_id as course_id, table1.course_name, acronym, major_id\n" +
            "               from (\n" +
            "                     (select co.course_name, co.course_id as course_id, type_require, acronym, cm.major_id\n" +
            "                      from course_major cm\n" +
            "                               join course co on cm.course_id = co.course_id\n" +
            "                      where (cm.major_id != ?)) table1\n" +
            "                        left join (select co.course_name as course_name, co.course_id as course_id\n" +
            "                                   from student_class_score sc\n" +
            "                                            join class c\n" +
            "                                                 on sc.class_id = c.class_id\n" +
            "                                            join course co on c.course_id = co.course_id\n" +
            "                                            join course_major cm on co.course_id = cm.course_id\n" +
            "                                   where sc.student_id = ?\n" +
            "                                     and sc.score >= 60\n" +
            "                                     AND cm.major_id != ?) table2 on table1.course_id = table2.course_id\n" +
            "                   )\n" +
            "               where table2.course_id is null) tmp on c.course_id = tmp.course_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "         join major m on tmp.major_id = m.major_id;";
    List<Object> params = new ArrayList<>();
    params.add(major_id);
    params.add(student_id);
    params.add(major_id);

    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }
//
//  public static void main(String[] args) {
//    CourseUtil courseUtil = new CourseUtil();
//    System.out.println(courseUtil.findGraduNeedByStudentID(1, 1,"BX"));
//
//  }


//
//  /**
//   * 用hashset重写了一遍
//   *
//   * @param chosenCourse 已经选过的课
//   * @param allCourse 所有要求的必修课
//   * @return 所有未选的课的名字
//   */
//  private List<String> findNotChoose(List<Map<String, Object>> chosenCourse,
//                                     List<Map<String, Object>> allCourse) {
//    Set<Integer> set1 = new HashSet<>();
//    Set<Integer> set2 = new HashSet<>();
//
//    for (Map<String, Object> fromAllCourse : allCourse) {
//      set1.add((Integer) fromAllCourse.get("course_id"));
//    }
//    for (Map<String, Object> fromChosenCourse : chosenCourse) {
//      set2.add((Integer) fromChosenCourse.get("course_id"));
//    }
//
//    Set<Integer> set = new HashSet<>(set1);
//    set.removeAll(set2);
//
//    List<String> notChosenCourseNameList = new ArrayList<>();
//    List<String> res = new ArrayList<>();
//
//    for (Map<String, Object> fromAllCourse : allCourse) {
//      int tmp_course_id = (int) fromAllCourse.get("course_id");
//      if (set.contains(tmp_course_id)) {
//        res.add((String) fromAllCourse.get("name"));
//      }
//    }
//
//    return res;
//  }

  public boolean addCourse(Map<String, Object> map) {
    boolean flag = false;
    String sql = "insert into Course(major_id,course_name,acronym,total_hour,credit) value (?,?,?,?,?)";
    List<Object> params = new ArrayList<>();
    params.add(map.get("major_id"));
    params.add(map.get("course_name"));
    params.add(map.get("acronym"));
    params.add(map.get("total_hour"));
    params.add(map.get("credit"));

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean updateCourse(Map<String, Object> map) {
    boolean flag = false;
    String sql = "update Course set major_id=?,course_name=?,acronym=?,total_hour=?,credit=? where course_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(map.get("major_id"));
    params.add(map.get("course_name"));
    params.add(map.get("acronym"));
    params.add(map.get("total_hour"));
    params.add(map.get("credit"));
    params.add(map.get("course_id"));

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean deleteCourse(int id) {
    boolean flag = false;
    String sql = "delete from Course where course_id = ?";
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
   * GC销毁对象时断开数据库连接
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

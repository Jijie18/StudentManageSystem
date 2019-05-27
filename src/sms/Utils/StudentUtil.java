package sms.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentUtil {
  private JdbcUtil db;

  public StudentUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }

  /**
   * @return -1 for fail; else the count of table rows
   */
  public int getStudentCount() {
    String sql = "select count(0) as count\n" +
            "from student;";
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
   * @return all students in table
   */
  public List<Map<String, Object>> findAllStudentsPage(int page, int single_page_num) {
    String sql = "select s.*, instructor_name, major_name\n" +
            "from Student s\n" +
            "         join instructor i on s.instructor_id = i.instructor_id\n" +
            "         join major m on s.major_id = m.major_id\n" +
            "order by student_id\n" +
            "limit ?, ?;";
    List<Map<String, Object>> res = null;
    List<Object> params = new ArrayList<>();
    int start = (page - 1) * single_page_num;
    int end = page * single_page_num;
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
   * @param id use id to find single student
   * @return the data of the student in <code>Map<String, Object></code>
   */
  public Map<String, Object> findStudentByID(int id) {
    String sql = "select i.instructor_name             as instructor_name,\n" +
            "       m.major_name             as major_name,\n" +
            "       s.*\n" +
            "from Student s\n" +
            "         join Instructor i on s.instructor_id = i.instructor_id\n" +
            "         join major m on s.major_id = m.major_id\n" +
            "where s.student_id = ?;";
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


//  public static void main(String[] args) {
//    StudentUtil studentUtil = new StudentUtil();
//    System.out.println(studentUtil.findStudentByID(1));
//    System.out.println(studentUtil.findStudentByID(1).containsKey("instructor_name"));
//  }

  /**
   * @param id InstructorID
   * @return all student whose instructor'ID is input
   */
  public List<Map<String, Object>> findStudentByInstructorID(int id) {
    String sql = "select i.instructor_name             as instructor_name,\\n\" +\n" +
            "            \"       m.major_name             as major_name,\\n\" +\n" +
            "            \"       s.*\\n\" +\n" +
            "            \"from Student s\\n\" +\n" +
            "            \"         join Instructor i on s.instructor_id = i.instructor_id\\n\" +\n" +
            "            \"         join major m on s.major_id = m.major_id\\n\" +\n" +
            "            \"where s.student_id = ?;";
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


//
//  /**
//   *
//   * @param id use id to find single student
//   * @return the data of the student
//   */
//  public Student findStudentObjectByID(int id) {
//    Student res = null;
//    String com.sql = "select * from Student where student_id = ?;";
//    List<Object> params = new ArrayList<>();
//    Map<String, Object> map = null;
//    params.add(id);
//    try {
//      map = db.dbFindSingleResult(com.sql, params);
//      res = MapToObjectUtil.mapToObject(map, Student.class);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return res;
//  }

  /**
   * insert without grade
   *
   * @param map new data
   * @return true: update success; false: sql statement return nothing
   */
  public boolean addStudent(Map<String, Object> map) {
    boolean flag = false;
    String sql = "insert into Student(student_name,  age, sex, admission_year," +
            " administrative_class, grade, instructor_id, major_id) value (?,?,?,?,?,?,?,?)";
    List<Object> params = new ArrayList<>();
    params.add(map.get("student_name"));
    params.add(map.get("age"));
    params.add(map.get("sex"));
    params.add(map.get("admission_year"));
    params.add(map.get("administrative_class"));
    params.add(map.get("grade"));
    params.add(map.get("instructor_id"));
    params.add(map.get("major_id"));

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean changeStudentPassword(int id, String newPassword) {
    boolean flag = false;
    String sql = "update student\n" +
            "set password = ?\n" +
            "where student_id = ?;";
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

  /**
   * @param map map.get("student_id") is the id of the student need to update
   * @return true: update success; false: com.sql statement return nothing
   */
  public boolean updateStudent(Map<String, Object> map) {
    boolean flag = false;
    String sql = "update Student\n" +
            "set student_name                 = ?,\n" +
            "    age                  = ?,\n" +
            "    sex                  = ?,\n" +
            "    admission_year       = ?,\n" +
            "    administrative_class = ?,\n" +
            "    grade                = ?,\n" +
            "    instructor_id        = ?,\n" +
            "    major_id             =?\n" +
            "where student_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(map.get("student_name"));
    params.add(map.get("age"));
    params.add(map.get("sex"));
    params.add(map.get("admission_year"));
    params.add(map.get("administrative_class"));
    params.add(map.get("grade"));
    params.add(map.get("instructor_id"));
    params.add(map.get("major_id"));
    params.add(map.get("student_id"));
    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  /**
   * @param id the student_id of student will be deleted
   * @return true: update success; false: com.sql statement return nothing
   */
  public boolean deleteStudent(int id) {
    boolean flag = false;
    String sql = "delete from Student where student_id = ?";
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

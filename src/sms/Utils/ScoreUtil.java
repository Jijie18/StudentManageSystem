package sms.Utils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreUtil {
  private JdbcUtil db;

  public ScoreUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }

  public List<Map<String, Object>> getAllScore() {
    List<Map<String, Object>> res = null;
    String sql = "select sc.students_id, co.course_name, sc.grade\n" +
            "from students_class sc\n" +
            "         join class c on sc.class_id = c.class_id\n" +
            "         join course co on c.course = co.course_id\n" +

            "order by co.course_id;";
    try {
      res = db.dbFindMultiResult(sql, null);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Map<String, Object>> getScoreByStudentID(int id) {
    List<Map<String, Object>> res = null;
    String sql = "select s.year, s.semester, sc.student_id, st.student_name, co.course_name as course_name, sc.score\n" +
            "from student_class_score sc\n" +
            "         join class c on sc.class_id = c.class_id\n" +
            "         join course co on c.course_id = co.course_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "         join student st on sc.student_id = st.student_id\n" +
            "where sc.student_id = ?\n" +
            "order by co.course_id;";
    List<Object> params = new ArrayList<>();
    params.add(id);

    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Map<String, Object>> getScoreByCourseID(int id) {
    List<Map<String, Object>> res = null;
    String sql = "select s.year, s.semester, sc.student_id, st.student_name, st.grade, co.course_name as course_name, sc.score\n" +
            "from student_class_score sc\n" +
            "         join class c on sc.class_id = c.class_id\n" +
            "         join course co on c.course_id = co.course_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "         join student st on sc.student_id = st.student_id\n" +
            "where c.course_id = 1\n" +
            "order by co.course_id;";
    List<Object> params = new ArrayList<>();
    params.add(id);

    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;

  }


  public List<Map<String, Object>> getScoreByClassID(int id) {
    List<Map<String, Object>> res = null;
    String sql = "select \n" +
            "       s.year,\n" +
            "       s.semester,\n" +
            "       sc.student_id,\n" +
            "       c.class_id,\n" +
            "       st.student_name,\n" +
            "       st.grade,\n" +
            "       co.course_name as course_name,\n" +
            "       sc.score\n" +
            "from student_class_score sc\n" +
            "         join class c on sc.class_id = c.class_id\n" +
            "         join course co on c.course_id = co.course_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "         join student st on sc.student_id = st.student_id\n" +
            "where c.class_id = ?\n" +
            "order by co.course_id;";
    List<Object> params = new ArrayList<>();
    params.add(id);

    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;

  }

  public List<Map<String, Object>> getScoreByInstructorID(int id) {
    List<Map<String, Object>> res = null;
    String sql = "select s.year,\n" +
            "       s.semester,\n" +
            "       sc.student_id,\n" +
            "       st.student_name,\n" +
            "       st.grade,\n" +
            "       co.course_name as course_name,\n" +
            "       sc.score\n" +
            "from student_class_score sc\n" +
            "         join class c on sc.class_id = c.class_id\n" +
            "         join course co on c.course_id = co.course_id\n" +
            "         join lesson l on c.class_id = l.class_id\n" +
            "         join schedule s on c.schedule_id = s.schedule_id\n" +
            "         join student st on sc.student_id = st.student_id\n" +
            "where l.instructor_id = ?\n" +
            "order by co.course_id;";
    List<Object> params = new ArrayList<>();
    params.add(id);

    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  /**
   * get the student's current GPA
   *
   * @param id student id
   * @return current the student's GPA
   */
  public double getStudentCurrentGPA(int id) {
    double gpa = 0;
    BigDecimal ret_gpa = null;
    String sql = "select sum(credit * gpa) / sum(credit) as GPA\n" +
            "from class c\n" +
            "         join course co on c.course_id = co.course_id\n" +
            "         join (\n" +
            "    select class_id,\n" +
            "           case\n" +
            "               when score >= 97 then 4.0\n" +
            "               when score >= 93 then 3.94\n" +
            "               when score >= 90 then 3.85\n" +
            "               when score >= 87 then 3.73\n" +
            "               when score >= 83 then 3.55\n" +
            "               when score >= 80 then 3.32\n" +
            "               when score >= 77 then 3.09\n" +
            "               when score >= 73 then 2.78\n" +
            "               when score >= 70 then 2.42\n" +
            "               when score >= 67 then 2.08\n" +
            "               when score >= 63 then 1.63\n" +
            "               when score >= 60 then 1.15\n" +
            "               else 0 end as gpa\n" +
            "    from student_class_score sc\n" +
            "    where sc.student_id = ?\n" +
            ") gpa on gpa.class_id = c.class_id;";
    List<Object> params = new ArrayList<>();
    params.add(id);

    try {
      ret_gpa = (BigDecimal) db.dbFindSingleResult(sql, params).get("GPA");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (ret_gpa != null)
      gpa = ret_gpa.doubleValue();
    gpa = Math.round(gpa * 100) / 100.0; // 四舍五入取两位小数
    return gpa;
  }

  /**
   * 导师查询指导的学生的gpa
   *
   * @param id instructor id
   * @return all gpa in <code>bigDecimal</code>
   * @author PengWeiyuan
   */
  public List<Map<String, Object>> getStudentGPAByInstructorID(int id) {
    List<Map<String, Object>> res = null;
    String sql = "select student_id,\n" +
            "       student_name,\n" +
            "       administrative_class,\n" +
            "       admission_year,\n" +
            "       truncate(sum(credit * gpa) / sum(credit), 2) as GPA\n" +
            "from class c\n" +
            "         join course co on c.course_id = co.course_id\n" +
            "         join\n" +
            "     (\n" +
            "         select class_id,\n" +
            "                s.student_id,\n" +
            "                i.instructor_id,\n" +
            "                s.administrative_class,\n" +
            "                s.admission_year,\n" +
            "                s.student_name as student_name,\n" +
            "                case\n" +
            "                    when score >= 97 then 4.0\n" +
            "                    when score >= 93 then 3.94\n" +
            "                    when score >= 90 then 3.85\n" +
            "                    when score >= 87 then 3.73\n" +
            "                    when score >= 83 then 3.55\n" +
            "                    when score >= 80 then 3.32\n" +
            "                    when score >= 77 then 3.09\n" +
            "                    when score >= 73 then 2.78\n" +
            "                    when score >= 70 then 2.42\n" +
            "                    when score >= 67 then 2.08\n" +
            "                    when score >= 63 then 1.63\n" +
            "                    when score >= 60 then 1.15\n" +
            "                    else 0 end as gpa\n" +
            "         from student_class_score sc\n" +
            "                  join student s on sc.student_id = s.student_id\n" +
            "                  join instructor i on s.instructor_id = i.instructor_id\n" +
            "         where i.instructor_id = ?) gpa on gpa.class_id = c.class_id\n" +
            "group by student_id;";
    List<Object> params = new ArrayList<>();
    params.add(id);

    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }


  public boolean deleteScore(int student_id, int class_id) {
    boolean flag = false;
    String sql = "delete\n" +
            "from student_class_score\n" +
            "where student_id = ?\n" +
            "  and class_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(student_id);
    params.add(class_id);
    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  /**
   * 表的设计问题，插入成绩等于将学生加入该班级
   *
   * @param student_id
   * @param class_id
   * @param score
   * @return
   */
  public boolean saveScore(int student_id, int class_id, int score) {
    boolean flag = false;
    String sql = "select count(*) as flag\n" +
            "from student_class_score\n" +
            "where class_id = ?\n" +
            "   and student_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(class_id);
    params.add(student_id);

    try {
      System.out.println(Integer.parseInt(db.dbFindSingleResult(sql, params).
              get("flag").toString()));
      if (Integer.parseInt(db.dbFindSingleResult(sql, params).
              get("flag").toString()) == 0) {
        flag = addScore(student_id, class_id, score);
      } else {
        flag = updateScore(student_id, class_id, score);

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean updateScore(int student_id, int class_id, int score) {
    boolean flag = false;
    String sql = "update student_class_score\n" +
            "set score = ?\n" +
            "where student_id = ?\n" +
            "  and class_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(score);
    params.add(student_id);
    params.add(class_id);

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean addScore(int student_id, int class_id, int score) {
    boolean flag = false;
    String sql = "insert into student_class_score (student_id, class_id, score) values (?, ?, ?);";
    List<Object> params = new ArrayList<>();
    params.add(student_id);
    params.add(class_id);
    params.add(score);
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
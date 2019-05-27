package sms.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 public class ChooseClassUtil {
  private JdbcUtil db;

  public ChooseClassUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }

  /**
   * @return -1 for fail; else the count of table rows
   */
  public int getChooseClassCount() {
    String sql = "select count(0) as count\n" +
            "from class_selection;";
    int res = -1;
    try {
      res = Integer.parseInt(db.dbFindSingleResult(sql, null).
              get("count").toString());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Map<String, Object>> findAllClassChoosePage(int page, int singlePageRowsNum) {
    List<Map<String, Object>> res = null;
    String sql = "select s.student_id, student_name,c.class_id, c.course_id, acronym, course_name, capacity, major_name, wage_number\n" +
            "from class_selection cs\n" +
            "         join class c on cs.class_id = c.class_id\n" +
            "         join course c2 on c.course_id = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "join student s on cs.student_id = s.student_id\n" +
            "limit ?, ?;";

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
  public List<Map<String, Object>> findChooseClassByStudentID(int id) {
    List<Map<String, Object>> res = null;
    String sql = "select c.class_id, c.course_id, acronym, course_name, capacity, major_name, wage_number\n" +
            "from class_selection cs\n" +
            "         join class c on cs.class_id = c.class_id\n" +
            "         join course c2 on c.course_id = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "where student_id = ?;";

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
   * 将ClassSelection中的选课结果导入到最终的课程学生表中,
   * 并初始化学生剩余积分为100
   *
   * @return true for success import
   */
  public boolean importClassSelecltionResult() {
    boolean flag1 = false;
    boolean flag2 = false;
    String sql = "insert into student_class (student_id, class_id)\n" +
            "select student_id, t1.class_id\n" +
            "from class_selection t1\n" +
            "         join class c on t1.class_id = c.class_id\n" +
            "where (select count(*)\n" +
            "       from class_selection t2\n" +
            "       where (t2.class_id = t1.class_id and t2.wage_number > t1.wage_number)\n" +
            "          or (t2.class_id = t1.class_id and t2.wage_number = t1.wage_number and t2.student_id < t1.student_id)) <=\n" +
            "      c.capacity;";

    String sql2 = "update student\n" +
            "set left_wage = 100\n" +
            "where 1 = 1;";
    try {
      flag1 = db.dbUpdate(sql, null);
      flag2 = db.dbUpdate(sql2, null);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag1 & flag2;
  }

  public boolean clearClassSelection() {
    boolean flag = false;
    String sql = "truncate class_selection;";
    try {
      flag = db.dbUpdate(sql, null);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

//  public static void main(String[] args) {
//    ChooseClassUtil chooseCourseUtil = new ChooseClassUtil();
//    chooseCourseUtil.clearClassSelection();
//  }

  /**
   * delete one row from classSelection table
   *
   * @param student_id
   * @param class_id
   * @return
   */
  public boolean deleteClassSelection(int student_id, int class_id) {
    boolean flag2 = false;
    boolean flag1 = false;
    String sql2 = "update student a\n" +
            " join (select wage_number, student_id from class_selection" +
            " where student_id = ? and class_id = ?) c\n" +
            "    on a.student_id = c.student_id\n" +
            "set a.left_wage = left_wage + wage_number;";
    List<Object> params2 = new ArrayList<>();
    params2.add(student_id);
    params2.add(class_id);

    String sql1 = "delete\n" +
            "from class_selection\n" +
            "where student_id = ?\n" +
            "  and class_id = ?;";
    List<Object> params1 = new ArrayList<>();
    params1.add(student_id);
    params1.add(class_id);


    try {
      flag2 = db.dbUpdate(sql2, params2);
      flag1 = db.dbUpdate(sql1, params1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag1 && flag2;
  }

  /**
   * @param student_id
   * @param class_id
   * @return true: can choose; false: can not choose because of time conflict
   */
  private boolean timeJudge(int student_id, int class_id) {
    String sql = "select count(*) as flag\n" +
            "from (select l.time_id\n" +
            "      from class_selection\n" +
            "               join class c on class_selection.class_id = c.class_id\n" +
            "               join lesson l on c.class_id = l.class_id\n" +
            "      where student_id = ?) a\n" +
            "         join\n" +
            "     (select distinct l.time_id\n" +
            "      from class c\n" +
            "               join lesson l on c.class_id = l.class_id\n" +
            "      where c.class_id = ?\n" +
            "     ) b on a.time_id = b.time_id;";
    List<Object> params = new ArrayList<>();
    params.add(student_id);
    params.add(class_id);
    Map<String, Object> flag = null;
    try {
      flag = db.dbFindSingleResult(sql, params);
//      System.out.println(flag);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int ret = Integer.parseInt(flag.get("flag").toString());

    return ret == 0;
  }

  private boolean haveChooseCourseJudge(int student_id, int class_id) {
    String sql1 = "select count(0) as flag1\n" +
            "from (select c.course_id\n" +
            "      from class_selection\n" +
            "               join class c on class_selection.class_id = c.class_id\n" +
            "               join course c2 on c.course_id = c2.course_id\n" +
            "      where student_id = ?) a\n" +
            "         join (select c.course_id\n" +
            "               from class\n" +
            "                        join course c on class.course_id = c.course_id\n" +
            "               where class_id = ?) b on a.course_id = b.course_id;";

    String sql2 = "select count(0) as flag2\n" +
            "from (select c3.course_id\n" +
            "      from student_class\n" +
            "               join class c2 on student_class.class_id = c2.class_id\n" +
            "               join course c3 on c2.course_id = c3.course_id\n" +
            "      where student_id = ?) a\n" +
            "         join (select c.course_id\n" +
            "               from class\n" +
            "                        join course c on class.course_id = c.course_id\n" +
            "               where class_id = ?) b on a.course_id = b.course_id;";
    List<Object> params1 = new ArrayList<>();
    List<Object> params2 = new ArrayList<>();

    params1.add(student_id);
    params1.add(class_id);
    params2.add(student_id);
    params2.add(class_id);
    Map<String, Object> flag1 = null;
    Map<String, Object> flag2 = null;

    try {
      flag1 = db.dbFindSingleResult(sql1, params1);
      flag2 = db.dbFindSingleResult(sql2, params2);
//      System.out.println(flag1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int ret1 = Integer.parseInt(flag1.get("flag1").toString());
    int ret2 = Integer.parseInt(flag2.get("flag2").toString());


    return ret1 == 0 && ret2 == 0;
  }

  public boolean leftWageJudge(int student_id, int wage_number) {
    String sql = "select left_wage\n" +
            "from student\n" +
            "where student_id = ?";
    List<Object> params = new ArrayList<>();
    params.add(student_id);
    Map<String, Object> flag = null;
    try {
      flag = db.dbFindSingleResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    int left_wage = 0;
    if (flag != null) {
      left_wage = Integer.parseInt(flag.get("left_wage").toString());
    }

    return left_wage >= wage_number;
  }

  public boolean insertChooseClass(int student_id, int class_id, int wage_number) {
    String sql = "insert into class_selection (student_id, class_id, wage_number)\n" +
            "VALUES (?, ?, ?);";
    boolean flag = false;
    List<Object> params = new ArrayList<>();
    params.add(student_id);
    params.add(class_id);
    params.add(wage_number);

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }


  /**
   * 不需要判断是否已经修过课程，因为前端展示的课表全部为当前未修的课
   *
   * @param student_id
   * @param class_id
   * @return -4: pre-require is not satisfied
   *         -3: left wage < wage_number;
   *         -2: time conflict;
   *         -1: have choose same course;
   *          0: insert success
   */
  public int chooseClass(int student_id, int class_id, int wage_number) {

    if (!timeJudge(student_id, class_id))
      return -2;
    else if (!haveChooseCourseJudge(student_id, class_id))
      return -1;
    else if (!leftWageJudge(student_id, wage_number))
      return -3;
    else {
      insertChooseClass(student_id, class_id, wage_number);
      String sql = "update student\n" +
              "set left_wage= left_wage - ?\n" +
              "where student_id = ?;";
      List<Object> params = new ArrayList<>();
      params.add(wage_number);
      params.add(student_id);
      try {
        db.dbUpdate(sql, params);
      } catch (SQLException e) {
        e.printStackTrace();
        String sqlState = e.getSQLState();
        if (sqlState.equals("45000")) {
          return -4;
        }
      }
      return 0;
    }
  }


  public int getStudentLeftWage(int id) {
    String sql = "select left_wage\n" +
            "from student\n" +
            "where student_id = ?;";
    List<Object> params = new ArrayList<>();
    Map<String, Object> res = null;
    params.add(id);
    try {
      res = db.dbFindSingleResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Integer.parseInt(res.get("left_wage").toString());
  }
//
//  public static void main(String[] args) {
//    ChooseClassUtil chooseCourseUtil = new ChooseClassUtil();
//    boolean flag = chooseCourseUtil.timeJudge(1, 9);
//    System.out.println(flag);
//  }
}

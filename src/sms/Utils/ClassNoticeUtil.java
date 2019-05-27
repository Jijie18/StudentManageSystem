package sms.Utils;

import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassNoticeUtil {
  private JdbcUtil db;

  public ClassNoticeUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }

  /**
   * notice that <code>publish_time</code>is an instance of <code>java.sql.Timestamp</code>
   *
   * @param id the class id of which to find
   * @return two column data (publish_time, text)
   */
  public List<Map<String, Object>> findNoticeByClassId(int id) {
    List<Map<String, Object>> res = null;
    String sql = "select n.publish_time as publish_time,\n" +
            "       n.text         as text,\n" +
            "       c.class_id,\n" +
            "       n.notice_id\n" +
            "from class_notice cs\n" +
            "         join class c on cs.class_id = c.class_id\n" +
            "         join notice n on cs.notice_id = n.notice_id\n" +
            "where c.class_id = ?\n" +
            "limit 20;";
    List<Object> params = new ArrayList<>();
    params.add(id);

    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Map<String, Object>> findNoticeByStudentId(int id) {
    List<Map<String, Object>> res = null;
    String sql = "select c.class_id, course_name, text, publish_time\n" +
            "from notice n\n" +
            "         join class_notice cn on n.notice_id = cn.notice_id\n" +
            "         join class c on cn.class_id = c.class_id\n" +
            "    join course c2 on c.course_id = c2.course_id\n" +
            "         join student_class sc on c.class_id = sc.class_id\n" +
            "where student_id = ?\n" +
            "limit 20;";
    List<Object> params = new ArrayList<>();
    params.add(id);

    try {
      res = db.dbFindMultiResult(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

//  /**
//   * 只是将notice插入了notice表中, 如果要推送到班级需要配合使用<code>publishNoticeToSingleClass</code>
//   *
//   * @param map insert data
//   * @return true: update success; false: sql statement return nothing
//   */
//  public boolean addNotice(Map<String, Object> map) {
//    boolean flag = false;
//    String sql = "insert into Notice (text, publish_time) value (?, ?)";
//    List<Object> params = new ArrayList<>();
//    params.add(map.get("text"));
//    params.add(map.get("publish_time"));
//    try {
//      flag = db.dbUpdate(sql, params);
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return flag;
//  }

  /**
   * 在Notice表内添加要推送的信息, 同时推送notice到对应班级, 直接使用当前的系统时间
   *
   * @param class_id 推送到的班级id
   * @param text     要推送的通知信息
   * @return true: update success; false: sql statement return nothing
   */
  public boolean publishNoticeToClass(int class_id, String text) {
    boolean flag = false;
    BigInteger last_notice_id = null;
    String sql1 = "insert into Notice (text, publish_time) value (?, ?)";
    List<Object> params1 = new ArrayList<>();
    params1.add(text);
    params1.add(new Timestamp(System.currentTimeMillis()));
    String sql2 = "SELECT LAST_INSERT_ID() as last_notice_id;";


    try {
      flag = db.dbUpdate(sql1, params1);
      // because the jdbc convert LAST_INSERT_ID() stored in db to BigInteger in Java
      last_notice_id = (BigInteger) db.dbFindSingleResult(sql2, null).get("last_notice_id");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String sql3 = "insert into class_notice (class_id, notice_id) values (?, ?);";
    List<Object> params3 = new ArrayList<>();
    params3.add(class_id);
    params3.add(last_notice_id);

    try {
      db.dbUpdate(sql3, params3);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return flag;
  }

  /**
   * update data in Notice table, publish_time gets from sys time
   *
   * @param map new data
   * @return true: update success; false: sql statement return nothing
   */
  public boolean updateNotice(Map<String, Object> map) {
    boolean flag = false;
    String sql = "update Notice set publish_time = ?, text = ? where notice_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(new Timestamp(System.currentTimeMillis()));
    params.add(map.get("text"));
    params.add(map.get("notice_id"));
    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean deleteNotice(int notice_id) {
    boolean flag = false;
    String sql = "delete from notice where notice_id = ?";
    List<Object> params = new ArrayList<>();
    params.add(notice_id);

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

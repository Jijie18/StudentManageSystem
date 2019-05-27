package sms.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LessonUtil {
  private JdbcUtil db;

  public LessonUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }

  public List<Map<String, Object>> findAllLessones() {
    String sql = "select l.*, m.major_name as major_name, t.*, c2.name as course_name, i.name as instructor_name\n" +
            "from lesson l\n" +
            "         join class c on l.class_id = c.class_id\n" +
            "         join course c2 on c.course_id = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "         join time t on l.time_id = t.time_id\n" +
            "         join instructor i on l.instructor_id = i.instructor_id\n" +
            "order by lesson_id;";
    List<Map<String, Object>> res = null;
    try {
      res = db.dbFindMultiResult(sql, null);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public Map<String, Object> findLessonByID(int id) {
    String sql = "select l.*,\n" +
            "       m.major_name as major_name,\n" +
            "       t.*,\n" +
            "       c2.name      as course_name,\n" +
            "       i.name       as instructor_name,\n" +
            "       r.*,\n" +
            "       b.*\n" +
            "from lesson l\n" +
            "         join class c on l.class_id = c.class_id\n" +
            "         join course c2 on c.course_id = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "         join time t on l.time_id = t.time_id\n" +
            "         join instructor i on l.instructor_id = i.instructor_id\n" +
            "         join room r on l.room_id = r.room_id\n" +
            "         join building b on r.building_id = b.building_id\n" +
            "where l.lesson_id = ?;";
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

  public List<Map<String, Object>> findLessonByClassID(int id) {
    String sql = "select lesson.*,\n" +
            "       class_id,\n" +
            "       concat(t.start, '-', t.end)      as time,\n" +
            "       t.week,\n" +
            "       t.weekday,\n" +
            "       instructor_name,\n" +
            "       concat(area, ' ', building_name) as place,\n" +
            "       floor,\n" +
            "       room_number\n" +
            "from lesson\n" +
            "         join time t on lesson.time_id = t.time_id\n" +
            "         join room r on lesson.room_id = r.room_id\n" +
            "         join building b on r.building_id = b.building_id\n" +
            "         join instructor i on lesson.instructor_id = i.instructor_id\n" +
            "where class_id = ?;";
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

  public List<Map<String, Object>> findLessonByRoomID(int id) {
    String sql = "select l.*,\n" +
            "       m.major_name,\n" +
            "       t.*,\n" +
            "       c2.name as course_name,\n" +
            "       i.name  as instructor_name,\n" +
            "       r.*,\n" +
            "       b.*\n" +
            "from lesson l\n" +
            "         join class c on l.class_id = c.class_id\n" +
            "         join course c2 on c.course_id = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "         join time t on l.time_id = t.time_id\n" +
            "         join instructor i on l.instructor_id = i.instructor_id\n" +
            "         join room r on l.room_id = r.room_id\n" +
            "         join building b on r.building_id = b.building_id\n" +
            "where l.room_id = ?;";
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
  public List<Map<String, Object>> findLessonByInstructorID(int id) {
    String sql = "select l.*, m.major_name as major_name, t.*, c2.name as course_name, i.name as instructor_name,\n" +
            "       r.*, b.*\n" +
            "from lesson l\n" +
            "         join class c on l.class_id = c.class_id\n" +
            "         join course c2 on c.course_id = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "         join time t on l.time_id = t.time_id\n" +
            "         join instructor i on l.instructor_id = i.instructor_id\n" +
            "         join room r on l.room_id = r.room_id\n" +
            "         join building b on r.building_id = b.building_id" +
            " where instructor_id = ?;";
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

  public List<Map<String, Object>> findLessonByTimeID(int id) {
    String sql = "select l.*, m.major_name as major_name, t.*, c2.name as course_name, i.name as instructor_name,\n" +
            "       r.*, b.*\n" +
            "from lesson l\n" +
            "         join class c on l.class_id = c.class_id\n" +
            "         join course c2 on c.course_id = c2.course_id\n" +
            "         join major m on c2.major_id = m.major_id\n" +
            "         join time t on l.time_id = t.time_id\n" +
            "         join instructor i on l.instructor_id = i.instructor_id\n" +
            "         join room r on l.room_id = r.room_id\n" +
            "         join building b on r.building_id = b.building_id" +
            " where time_id = ?;";
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
  public boolean addLesson(Map<String, Object> map) {
    boolean flag = false;
    String sql = "insert into Lesson(class_id, room_id, instructor_id, time_id) value (?, ?, ?, ?)";
    List<Object> params = new ArrayList<>();
    params.add(map.get("class_id"));
    params.add(map.get("room_id"));
    params.add(map.get("instructor_id"));
    params.add(map.get("time_id"));

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }


  public boolean updateLesson(Map<String, Object> map) {
    boolean flag = false;
    String sql = "update Lesson\n" +
            "set class_id=?, room_id=?, instructor_id=?, time_id=?\n" +
            "where lesson_id = ?;";
    List<Object> params = new ArrayList<>();
    params.add(map.get("class_id"));
    params.add(map.get("room_id"));
    params.add(map.get("instructor_id"));
    params.add(map.get("time_id"));
    params.add(map.get("lesson_id"));

    try {
      flag = db.dbUpdate(sql, params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return flag;
  }

  public boolean deleteLesson(int id) {
    boolean flag = false;
    String sql = "delete from Lesson where lesson_id = ?";
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

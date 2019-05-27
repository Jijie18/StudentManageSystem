package sms.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminUtil {
  private JdbcUtil db;

  public AdminUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }
  public boolean changeAdminPassword(int id, String newPassword) {
    boolean flag = false;
    String sql = "update admin\n" +
            "set password = ?\n" +
            "where admin_id = ?;";
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

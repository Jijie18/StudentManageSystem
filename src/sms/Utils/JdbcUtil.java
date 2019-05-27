package sms.Utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

import com.mysql.jdbc.Driver;


public class JdbcUtil {

  private String USERNAME = "root";

  private String PASSWORD = "sustc2018";

  private String DRIVER = "com.mysql.cj.jdbc.Driver";

  private String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=CTT";

  private Connection connectWithDb;

  private PreparedStatement preparedStatement;

  private ResultSet resultSet;

  public void setUSERNAME(String USERNAME) {
    this.USERNAME = USERNAME;
  }

  public void setPASSWORD(String PASSWORD) {
    this.PASSWORD = PASSWORD;
  }

  public void setURL(String URL) {
    this.URL = URL;
  }

  public PreparedStatement getPreparedStatement() {
    return preparedStatement;
  }

  /**
   * load driver
   */
  public JdbcUtil() {
    try {
      Class.forName(DRIVER);
      System.out.println("Database Driver Loaded success");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * connect with DB with the {@code URL, USERNAME, PASSWORD}
   *
   * @return the connection with DB
   */
  public Connection getConnection() {
    try {
      connectWithDb = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      System.out.println("Connect success");
    } catch (SQLException e) {
      System.out.println("Connection error");
      e.printStackTrace();
    }
    return connectWithDb;
  }

  /**
   * use to update, insert, delete data in DB
   *
   * @param sql    SQL statement
   * @param params the parameters in sql statement
   * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
   * or (2) 0 for SQL statements that return nothing
   * @throws SQLException if a database access error occurs
   */
  public boolean dbUpdate(String sql, List<Object> params) throws SQLException {
    boolean flag;
    int result = -1;
    preparedStatement = connectWithDb.prepareStatement(sql);
    int index = 1;
    if (params != null && !params.isEmpty()) {
      for (Object param : params) {
        preparedStatement.setObject(index++, param);
      }
    }
    result = preparedStatement.executeUpdate();
//    System.out.println(result);
    flag = result > 0;
//    System.out.println(flag);
    return flag;
  }

  /**
   * used to insert many data from excel file
   *
   * @param table_name the name of table in db to insert
   * @param data the data from import a file
   * @param cols_name the columns' name -> the first row in excel file
   * @throws SQLException if a database access error occurs
   */
  public void dbInsertBatch(String table_name, List<List<Object>> data, List<String> cols_name) throws SQLException {
    final int BATCH_SIZE = 500; // execute 500 insert each times

    // generate insert statement
    boolean flag = false;
    int len = cols_name.size();
    int size = data.size();
    int cnt = 0;
    StringBuilder sb = new StringBuilder();
    sb.append("insert into ");
    sb.append(table_name);
    sb.append("(");
    for (int i = 0; i < len; i++) {
      sb.append(cols_name.get(i));
      if (i != len - 1)
        sb.append(",");
    }
    sb.append(") values (");
    for (int i = 0; i < len; i++) {
      sb.append("?");
      if (i != len - 1)
        sb.append(",");
    }
    sb.append(");");
      preparedStatement = connectWithDb.prepareStatement(sb.toString());

//    System.out.println(sb.toString());
    for (List<Object> datum : data) {
      for (int i = 1; i <= len; i++) {
        preparedStatement.setObject(i, datum.get(i - 1));
      }
      preparedStatement.addBatch();
      cnt++;
      if (cnt % BATCH_SIZE == 0) {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
      }
    }
    preparedStatement.executeBatch();
  }

//  public static void main(String[] args) throws SQLException {
//    JdbcUtil jdbcUtil = new JdbcUtil();
//
//  }

  /**
   * use to find single-line data in DB
   * 返回值中map的key是列名 value是查询到的值
   * 当查询不到信息的时候返回一个空的map, 可以用<code>map.isEmpty()</code>检查
   * 注意返回值永远不会为null
   *
   * @param sql    SQL statement
   * @param params the parameters in com.sql statement
   * @return query result expressed in <code>Map<String, Object></code>
   * @throws SQLException if a database access error occurs
   */
  public Map<String, Object> dbFindSingleResult(String sql, List<Object> params) throws SQLException {
    Map<String, Object> map = new HashMap<>();
    int index = 1;
    preparedStatement = connectWithDb.prepareStatement(sql);
    if (params != null && !params.isEmpty()) {
      for (Object param : params) {
        preparedStatement.setObject(index++, param);
      }
    }
    resultSet = preparedStatement.executeQuery();
    ResultSetMetaData metaData = resultSet.getMetaData();
    int col_len = metaData.getColumnCount();
    if (resultSet.next()) {
      for (int i = 0; i < col_len; i++) {
        String cols_name = metaData.getColumnName(i + 1);
        Object cols_value = resultSet.getObject(cols_name);
        if (cols_value == null) {
          cols_value = "";
        }
        map.put(cols_name, cols_value);
      }
    }
    return map;
  }

  /**
   * use to find multi-lines data in DB
   *
   * @param sql    SQL statement
   * @param params the parameters in com.sql statement
   * @return query result expressed in <code>List<Map<String, Object>></code>, never return null
   * @throws SQLException if a database access error occurs
   */
  public List<Map<String, Object>> dbFindMultiResult(String sql, List<Object> params) throws SQLException {
    List<Map<String, Object>> list = new ArrayList<>();
    int index = 1;
    preparedStatement = connectWithDb.prepareStatement(sql);
    if (params != null && !params.isEmpty()) {
      for (Object param : params) {
        preparedStatement.setObject(index++, param);
      }
    }
//    System.out.println(preparedStatement.toString());
    resultSet = preparedStatement.executeQuery();
    ResultSetMetaData metaData = resultSet.getMetaData();
    // System.out.println(metaData.getColumnName(6));
    int cols_len = metaData.getColumnCount();
    while (resultSet.next()) {
      Map<String, Object> map = new HashMap<>();
      for (int i = 0; i < cols_len; i++) {
        String cols_name = metaData.getColumnName(i + 1);
        Object cols_value = resultSet.getObject(cols_name);
        if (cols_value == null) {
          cols_value = "";
        }
        map.put(cols_name, cols_value);
      }
      list.add(map);
    }

    return list;
  }



  public <T> T dbFindSingleObjectResult(String sql, List<Object> params, Class<T> cls) throws Exception {
    T resultObject = null;
    int index = 1;
    preparedStatement = connectWithDb.prepareStatement(sql);
    if (params != null && !params.isEmpty()) {
      for (Object param : params) {
        preparedStatement.setObject(index++, param);
      }
    }
    resultSet = preparedStatement.executeQuery();
    ResultSetMetaData metaData = resultSet.getMetaData();
    int cols_len = metaData.getColumnCount();
    if (resultSet.next()) {
      resultObject = cls.newInstance();
      for (int i = 0; i < cols_len; i++) {
        String cols_name = metaData.getColumnName(i + 1);
        Object cols_value = resultSet.getObject(cols_name);
        if (cols_value == null) {
          cols_value = "";
        }
        Field field = cls.getDeclaredField(cols_name);
        if (field.getType() == int.class) {
          if (cols_value == "") {
            cols_value = 0;
          } else {
            cols_value = Integer.parseInt(cols_value.toString());
          }
        }
        field.setAccessible(true);
        field.set(resultObject, cols_value);
      }
    }
    return resultObject;
  }

  public <T> List<T> dbFindMultiObjectResult(String sql, List<Object> params, Class<T> cls) throws Exception {
    List<T> list = new ArrayList<T>();
    int index = 1;
    preparedStatement = connectWithDb.prepareStatement(sql);
    if (params != null && !params.isEmpty()) {
      for (Object param : params) {
        preparedStatement.setObject(index++, param);
      }
    }
    resultSet = preparedStatement.executeQuery();
    ResultSetMetaData metaData = resultSet.getMetaData();
    int cols_len = metaData.getColumnCount();
    while (resultSet.next()) {
      T resultObject = cls.newInstance();
      for (int i = 0; i < cols_len; i++) {
        String cols_name = metaData.getColumnName(i + 1);
        Object cols_value = resultSet.getObject(cols_name);
        if (cols_value == null) {
          cols_value = "";
        }
        Field field = cls.getDeclaredField(cols_name);
        if (field.getType() == int.class) {
          if (cols_value == "") {
            cols_value = 0;
          } else {
            cols_value = Integer.parseInt(cols_value.toString());
          }
        }
        field.setAccessible(true);
        field.set(resultObject, cols_value);
      }
      list.add(resultObject);
    }
    return list;
  }


  /**
   * release the connection with current DB
   */
  public void releaseConn() {

    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        resultSet = null;
      }
    }

    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        preparedStatement = null;
      }
    }

    if (connectWithDb != null) {
      try {
        connectWithDb.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        connectWithDb = null;
      }
    }
    System.out.println("Release Connection success");
  }

//  public static void main(String[] args) throws SQLException {
//    JdbcUtil db = new JdbcUtil();
//    db.getConnection();
//    String sql = "select * from admins;";
//    System.out.println(db.dbFindMultiResult(sql, null));
//    db.releaseConn();
//
//  }
}

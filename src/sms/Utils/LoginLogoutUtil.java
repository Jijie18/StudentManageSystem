package sms.Utils;

import sms.Session;
import sms.beans.*;
import sms.beans.Admin;
import sms.beans.Instructor;
import sms.beans.Student;

import java.util.ArrayList;
import java.util.List;

public class LoginLogoutUtil {
  private JdbcUtil db;

  public LoginLogoutUtil() {
    db = new JdbcUtil();
    db.getConnection();
  }

  /**
   * login with id and password
   *
   * @param object 类型在Login的界面通过单选框指定, 学生/ 老师/ 管理员
   * @return 返回四种不同的String (1)"No Such ID" 数据库中找不到这个ID; (2)"Unknown Error" 未知问题;
   * (3)"Wrong Password" 密码错误; (4)"Login Success" 登录成功
   */
  public String Login(Object object) {
    String resInfo = "未知错误";
    int id;
    String password = null;
    String sql = null;

    if (Admin.class == object.getClass()) {
      Admin admin = (Admin) object;
      id = admin.getAdmin_id();
      password = admin.getPassword();
      sql = "select * from Admin where admin_id = ?";
      List<Object> params = new ArrayList<>();
      params.add(id);

      try {
//        Map<String, Object> dbAdmin = db.dbFindSingleResult(sql, params);
        Admin dbAdmin = db.dbFindSingleObjectResult(sql, params, Admin.class);
        if (dbAdmin == null) {
          resInfo = "没有这个ID";
        } else {
          String truePassword = (String) dbAdmin.getPassword();
          if (truePassword.equals(password)) {
            resInfo = "Success";
            Session.userInfo = dbAdmin;
          } else {
            resInfo = "密码错误";
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (Instructor.class == object.getClass()) {
      Instructor instructor = (Instructor) object;
      id = instructor.getInstructor_id();
      password = instructor.getPassword();
      sql = "select * from Instructor where instructor_id = ?";
      List<Object> params = new ArrayList<>();
      params.add(id);

      try {
        Instructor dbInstructor = db.dbFindSingleObjectResult(sql, params, Instructor.class);
        if (dbInstructor == null) {
          resInfo = "No such ID";
        } else {
          String truePassword = dbInstructor.getPassword();
          if (truePassword.equals(password)) {
            resInfo = "Success";
            Session.userInfo = dbInstructor;
          } else {
            resInfo = "Wrong password";
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      Student student = (Student) object;
      id = student.getStudent_id();
      password = student.getPassword();
      sql = "select * from Student where student_id = ?";
      List<Object> params = new ArrayList<>();
      params.add(id);

      try {
        Student dbStudent = db.dbFindSingleObjectResult(sql, params, Student.class);
        if (dbStudent == null) {
          resInfo = "No such ID";
        } else {
          String truePassword = dbStudent.getPassword();
          if (truePassword.equals(password)) {
            resInfo = "Success";
            Session.userInfo = dbStudent;
          } else {
            resInfo = "Wrong password";
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    //test
//    System.out.println(resInfo);
    return resInfo;
  }


  public void Logout() {
    Session.userInfo = null;
    if (db != null) {
      db.releaseConn();
      db = null;
    }
  }
  public static void main(String[] args) {
    LoginLogoutUtil loginLogoutUtil = new LoginLogoutUtil();
    Admin admin = new Admin();
    admin.setAdmin_id(1);
    admin.setPassword("1234");
    System.out.println(loginLogoutUtil.Login(admin));


  }
}

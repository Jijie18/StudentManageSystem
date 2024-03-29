package sms.beans;

public class Admin {
  private int admin_id;
  private String admin_name;
  private String password;

  public Admin() {
  }

  public Admin(int admin_id, String password) {
    this.admin_id = admin_id;
    this.password = password;
  }

  public Admin(int admin_id, String username, String password) {
    this.admin_id = admin_id;
    this.admin_name = username;
    this.password = password;
  }
  public String getName() {
    return admin_name;
  }

  public void setName(String name) {
    this.admin_name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAdmin_id() {
    return admin_id;
  }

  public void setAdmin_id(int admin_id) {
    this.admin_id = admin_id;
  }

  @Override
  public String toString() {
    return "Admin{" +
            "admin_id=" + admin_id +
            ", admin_name='" + admin_name + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}

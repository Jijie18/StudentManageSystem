package sms.beans;

import java.util.Date;

public class Instructor {
  private int instructor_id;
  private int major_id;
  private String password;
  private String instructor_name;
  private int age;
  private String sex;
  private Date joined_date;


  public int getInstructor_id() {
    return instructor_id;
  }

  public void setInstructor_id(int instructor_id) {
    this.instructor_id = instructor_id;
  }

  public int getMajor_id() {
    return major_id;
  }

  public void setMajor_id(int major_id) {
    this.major_id = major_id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return instructor_name;
  }

  public void setName(String name) {
    this.instructor_name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getJoined_date() {
    return joined_date;
  }

  public void setJoined_date(Date joined_date) {
    this.joined_date = joined_date;
  }

  public Instructor() {
  }

  public Instructor(int instructor_id, String password) {
    this.instructor_id = instructor_id;
    this.password = password;
  }

  public Instructor(int instructor_id, int major_id, String password, String name, int age, String sex, Date joined_date) {
    this.instructor_id = instructor_id;
    this.major_id = major_id;
    this.password = password;
    this.instructor_name = name;
    this.age = age;
    this.sex = sex;
    this.joined_date = joined_date;
  }

  @Override
  public String toString() {
    return "Instructor{" +
            "instructor_id=" + instructor_id +
            ", major_id=" + major_id +
            ", password='" + password + '\'' +
            ", name='" + instructor_name + '\'' +
            ", age=" + age +
            ", sex='" + sex + '\'' +
            ", joined_date=" + joined_date +
            '}';
  }
}

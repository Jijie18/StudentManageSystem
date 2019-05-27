package sms.beans;

public class Course {
  private int course_id;
  private int major_id; // 开课院系
  private String name;
  private String acronym;
  private int total_hour;
  private int credit;

  public Course() {
  }

  public Course(int course_id, int major_id, String name, String acronym, int total_hour, int credit) {
    this.course_id = course_id;
    this.major_id = major_id;
    this.name = name;
    this.acronym = acronym;
    this.total_hour = total_hour;
    this.credit = credit;
  }

  public int getCourse_id() {
    return course_id;
  }

  public void setCourse_id(int course_id) {
    this.course_id = course_id;
  }

  public int getMajor_id() {
    return major_id;
  }

  public void setMajor_id(int major_id) {
    this.major_id = major_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAcronym() {
    return acronym;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public int getTotal_hour() {
    return total_hour;
  }

  public void setTotal_hour(int total_hour) {
    this.total_hour = total_hour;
  }

  public int getCredit() {
    return credit;
  }

  public void setCredit(int credit) {
    this.credit = credit;
  }

  @Override
  public String toString() {
    return "Course{" +
            "course_id=" + course_id +
            ", major_id=" + major_id +
            ", name='" + name + '\'' +
            ", acronym='" + acronym + '\'' +
            ", total_hour=" + total_hour +
            ", credit=" + credit +
            '}';
  }
}

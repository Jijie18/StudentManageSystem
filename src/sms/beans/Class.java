package sms.beans;

public class Class {
  private int class_id;
  private int course_id;
  private int schedule_id;

  public Class() {
  }

  public Class(int class_id, int course_id, int schedule_id) {
    this.class_id = class_id;
    this.course_id = course_id;
    this.schedule_id = schedule_id;
  }

  public int getClass_id() {
    return class_id;
  }

  public void setClass_id(int class_id) {
    this.class_id = class_id;
  }

  public int getCourse_id() {
    return course_id;
  }

  public void setCourse_id(int course_id) {
    this.course_id = course_id;
  }

  public int getSchedule_id() {
    return schedule_id;
  }

  public void setSchedule_id(int schedule_id) {
    this.schedule_id = schedule_id;
  }

  @Override
  public String toString() {
    return "Class{" +
            "class_id=" + class_id +
            ", course_id=" + course_id +
            ", schedule_id=" + schedule_id +
            '}';
  }
}


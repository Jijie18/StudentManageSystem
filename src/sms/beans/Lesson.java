package sms.beans;

public class Lesson {
  private int lesson_id;
  private int class_id;
  private int room_id;
  private int instructor_id;
  private int time_id;

  public Lesson() {
  }

  public Lesson(int lesson_id, int class_id, int room_id, int instructor_id, int time_id) {
    this.lesson_id = lesson_id;
    this.class_id = class_id;
    this.room_id = room_id;
    this.instructor_id = instructor_id;
    this.time_id = time_id;
  }

  public int getLesson_id() {
    return lesson_id;
  }

  public void setLesson_id(int lesson_id) {
    this.lesson_id = lesson_id;
  }

  public int getClass_id() {
    return class_id;
  }

  public void setClass_id(int class_id) {
    this.class_id = class_id;
  }

  public int getRoom_id() {
    return room_id;
  }

  public void setRoom_id(int room_id) {
    this.room_id = room_id;
  }

  public int getInstructor_id() {
    return instructor_id;
  }

  public void setInstructor_id(int instructor_id) {
    this.instructor_id = instructor_id;
  }

  public int getTime_id() {
    return time_id;
  }

  public void setTime_id(int time_id) {
    this.time_id = time_id;
  }

  @Override
  public String toString() {
    return "Lesson{" +
            "lesson_id=" + lesson_id +
            ", class_id=" + class_id +
            ", room_id=" + room_id +
            ", instructor_id=" + instructor_id +
            ", time_id=" + time_id +
            '}';
  }
}

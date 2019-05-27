package sms.beans;

public class Student {
  private int student_id;
  private String student_name;
  private String password;
  private int age;
  private String sex;
  private int admission_year;
  private int administrative_class;
  private int grade;
  private int instructor_id;
  private int major_id;
  private int left_wage;


  public int getStudent_id() {
    return student_id;
  }

  public void setStudent_id(int student_id) {
    this.student_id = student_id;
  }

  public String getStudent_name() {
    return student_name;
  }

  public void setStudent_name(String student_name) {
    this.student_name = student_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public int getAdmission_year() {
    return admission_year;
  }

  public void setAdmission_year(int admission_year) {
    this.admission_year = admission_year;
  }

  public int getAdministrative_class() {
    return administrative_class;
  }

  public void setAdministrative_class(int administrative_class) {
    this.administrative_class = administrative_class;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public int getInstructor_id() {
    return instructor_id;
  }

  public void setInstructor_id(int instructor_id) {
    this.instructor_id = instructor_id;
  }

  public Student() {
  }

  public Student(int student_id, String password) {
    this.student_id = student_id;
    this.password = password;
  }

  public Student(String student_name, String password, int age, String sex, int addmission_year, int addmission_class, int grade, int instructor_id) {
    this.student_name = student_name;
    this.password = password;
    this.age = age;
    this.sex = sex;
    this.admission_year = addmission_year;
    this.administrative_class = addmission_class;
    this.grade = grade;
    this.instructor_id = instructor_id;
  }

  @Override
  public String toString() {
    return "Student{" +
            "student_id=" + student_id +
            ", student_name='" + student_name + '\'' +
            ", password='" + password + '\'' +
            ", age=" + age +
            ", sex=" + sex +
            ", admission_year=" + admission_year +
            ", administrative_class=" + administrative_class +
            ", grade=" + grade +
            ", instructor_id=" + instructor_id +
            '}';
  }

  public int getMajor_id() {
    return major_id;
  }

  public void setMajor_id(int major_id) {
    this.major_id = major_id;
  }

  public int getLeft_wage() {
    return left_wage;
  }

  public void setLeft_wage(int left_wage) {
    this.left_wage = left_wage;
  }
}

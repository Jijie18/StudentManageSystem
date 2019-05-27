package sms.beans;

public class Major {
  private int major_id;
  private String major_name;
  private int department_id;
  private int optional_credits;

  public Major() {
  }

  public Major(int major_id, String major_name, int department_id, int optional_credits) {
    this.major_id = major_id;
    this.major_name = major_name;
    this.department_id = department_id;
    this.optional_credits = optional_credits;
  }

  public int getMajor_id() {
    return major_id;
  }

  public void setMajor_id(int major_id) {
    this.major_id = major_id;
  }

  public String getName() {
    return major_name;
  }

  public void setName(String major_name) {
    this.major_name = major_name;
  }

  public int getDepartment_id() {
    return department_id;
  }

  public void setDepartment_id(int department_id) {
    this.department_id = department_id;
  }

  public int getOptional_credits() {
    return optional_credits;
  }

  public void setOptional_credits(int optional_credits) {
    this.optional_credits = optional_credits;
  }

  @Override
  public String toString() {
    return "Major{" +
            "major_id=" + major_id +
            ", major_name='" + major_name + '\'' +
            ", department_id=" + department_id +
            ", optional_credits=" + optional_credits +
            '}';
  }
}

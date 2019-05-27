package sms.beans;

import java.sql.Timestamp;

public class Notice {
  private int notice_id;
  private String text;
  private Timestamp publish_time;

  public Notice() {
  }

  public Notice(int notice_id, String text, Timestamp publish_time) {
    this.notice_id = notice_id;
    this.text = text;
    this.publish_time = publish_time;
  }

  public int getNotice_id() {
    return notice_id;
  }

  public void setNotice_id(int notice_id) {
    this.notice_id = notice_id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getPublish_time() {
    return publish_time;
  }

  public void setPublish_time(Timestamp publish_time) {
    this.publish_time = publish_time;
  }

  @Override
  public String toString() {
    return "Notice{" +
            "notice_id=" + notice_id +
            ", text='" + text + '\'' +
            ", publish_time=" + publish_time +
            '}';
  }
}

package sms.GUI;

import sms.Session;
import sms.Utils.*;
import sms.beans.Student;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class StudentHome {


  private JPanel homeStudent;
  private JButton exitButton;
  private JTabbedPane tabbedPane1;
  private JTextField student_id;
  private JTextField name;
  private JTextField sex;
  private JTextField admission_year;
  private JTextField administrative_class;
  private JTextField grade;
  private JTextField instructor;
  private JTextField major;
  private JLabel nameLabel;
  private JTextField age;
  private JTable scoreTable;
  private JTable nowCourseTable;
  private JTable noticeTable;
  private JTable otherMajorCourseTable;
  private JTable Ztable;
  private JTable table7;
  private JTable chooseClassTable;
  private JTabbedPane tabbedPane3;
  private JTable BXtable;
  private JTable Xtable;
  private JPasswordField passwordField1;
  private JPasswordField passwordField2;
  private JPasswordField passwordField3;
  private JButton changePasswordButton;
  private JLabel leftWageLabel;
  private JTable timeTableBX;
  private JFrame frame;

  private CourseModel courseModel;
  private ScoreModel scoreModel;
  private NoticeModel noticeModel;
  private CourseTypeModel courseTypeModelBX;
  private CourseTypeModel courseTypeModelX;
  private CourseTypeModel courseTypeModelZ;
  private OtherMajorCourseModel otherMajorCourseModel;
  private ChooseClassModel chooseClassModel;
  private Student currentStudent;

  public StudentHome() {

    StudentUtil studentutil = new StudentUtil();
    currentStudent = (Student) Session.userInfo;
    Map<String, Object> databaseStudent = studentutil.findStudentByID(currentStudent.getStudent_id());
    updateStudentInfo(databaseStudent);

    frame = new JFrame("StudentHome");
    frame.setContentPane(homeStudent);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    UICommonUtil.makeFrameToCenter(frame);
    frame.setVisible(true);

    courseModel = new CourseModel();
    scoreModel = new ScoreModel();
    noticeModel = new NoticeModel();
    courseTypeModelBX = new CourseTypeModel("BX");
    courseTypeModelX = new CourseTypeModel("X");
    courseTypeModelZ = new CourseTypeModel("Z");
    otherMajorCourseModel = new OtherMajorCourseModel();
    chooseClassModel = new ChooseClassModel();

    nowCourseTable.setModel(courseModel);
    scoreTable.setModel(scoreModel);
    noticeTable.setModel(noticeModel);
    BXtable.setModel(courseTypeModelBX);
    Xtable.setModel(courseTypeModelX);
    Ztable.setModel(courseTypeModelZ);
    otherMajorCourseTable.setModel(otherMajorCourseModel);
    chooseClassTable.setModel(chooseClassModel);

    BXtable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
          int col = 0;
          JTable timeTableBX = new JTable();
          timeTableBX.setModel(new ClassTimeModel(Integer.parseInt(BXtable.getValueAt(row, col).toString().trim())));
          ClassTimeChooseDialog classTimeDialog = new ClassTimeChooseDialog(frame, timeTableBX);
          classTimeDialog.setVisible(true);
        }
      }
    });

    Xtable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
          int col = 0;
          JTable timeTableX = new JTable();
          timeTableX.setModel(new ClassTimeModel(Integer.parseInt(Xtable.getValueAt(row, col).toString().trim())));
          ClassTimeChooseDialog classTimeDialog = new ClassTimeChooseDialog(frame, timeTableX);
          classTimeDialog.setVisible(true);
        }
      }
    });

    Ztable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
          int col = 0;
          JTable timeTableZ = new JTable();
          timeTableZ.setModel(new ClassTimeModel(Integer.parseInt(Ztable.getValueAt(row, col).toString().trim())));
          ClassTimeChooseDialog classTimeDialog = new ClassTimeChooseDialog(frame, timeTableZ);
          classTimeDialog.setVisible(true);
        }
      }
    });


    otherMajorCourseTable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
          int col = 0;
          JTable otherMajorClassTable = new JTable();
          otherMajorClassTable.setModel(new ClassTimeModel(Integer.parseInt(otherMajorCourseTable.getValueAt(row, col).toString().trim())));
          ClassTimeChooseDialog classTimeDialog = new ClassTimeChooseDialog(frame, otherMajorClassTable);
          classTimeDialog.setVisible(true);
        }
      }
    });
    chooseClassTable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
          int col = 0;
          JTable chooseClassTable = new JTable();
          chooseClassTable.setModel(new ClassTimeModel(Integer.parseInt(StudentHome.this.chooseClassTable.getValueAt(row, col).toString().trim())));
          ClassTimeCancelDialog classTimeCancelDialog = new ClassTimeCancelDialog(frame, chooseClassTable);
          classTimeCancelDialog.setVisible(true);
        }
      }
    });

    noticeTable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
          int col = 2;
          JOptionPane.showMessageDialog(frame, noticeTable.getValueAt(row, col), "提示", JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });


    exitButton.addActionListener(e -> {
      new LoginHome();
      frame.dispose();
      LoginLogoutUtil loginLogoutUtils = new LoginLogoutUtil();
      loginLogoutUtils.Logout();
    });


    changePasswordButton.addActionListener(e -> {
      String usedPassword = String.valueOf(passwordField1.getPassword());
      String newPassword1 = String.valueOf(passwordField2.getPassword());
      String newPassword2 = String.valueOf(passwordField3.getPassword());

      if (usedPassword.equals(currentStudent.getPassword().trim())) {
        if (newPassword1.equals(newPassword2)) {
          int res = JOptionPane.showConfirmDialog(frame, "确认更改密码？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
          if (res == JOptionPane.YES_OPTION) {
            studentutil.changeStudentPassword(currentStudent.getStudent_id(), newPassword1);
            currentStudent.setPassword(newPassword1);
          }
        } else {
          JOptionPane.showMessageDialog(frame, "新密码两次输入不一致", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(frame, "当前密码错误", "提示", JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }

  private class ClassTimeChooseDialog extends JDialog {
    private ClassTimeChooseDialog(JFrame owner, JTable table) {
      super(owner, "Class Information", true);
//      add(table);
      add(new JScrollPane(table));
      int DIALOG_WHITE = 600;
      int DIALOG_HEIGHT = 300;
      Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
      this.setBounds(point.x - DIALOG_WHITE / 2, point.y - DIALOG_HEIGHT / 2, DIALOG_WHITE, DIALOG_HEIGHT);
      JPanel panel = new JPanel();
      JLabel label = new JLabel("选择这门课？");
      JButton yes = new JButton("Yes");
      JButton no = new JButton("No");
      panel.add(label);
      panel.add(yes);
      panel.add(no);
      yes.addActionListener(e -> {
        int wage_number = 0;
        String ret;
        while (true) {
            ret = JOptionPane.showInputDialog(frame, "请输入投入的积分",
                    "提示", JOptionPane.QUESTION_MESSAGE);

            if (ret != null) {
              try {
                wage_number = Integer.parseInt(ret);
                break;
              }
              catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "请输入数字");
                ex.printStackTrace();
              }
            }
            else {
              break;
            }
        }

        if (wage_number <= 0 && ret != null) {
          JOptionPane.showMessageDialog(frame, "请输入大于0的数字",
                  "提示", JOptionPane.INFORMATION_MESSAGE);
        } else if (ret != null){
          ChooseClassUtil chooseClassUtil = new ChooseClassUtil();
//          System.out.println(table.getValueAt(0, 0).toString().trim());
          int judge = chooseClassUtil.chooseClass(currentStudent.getStudent_id(),
                  Integer.parseInt(table.getValueAt(0, 0).toString().trim()),
                  wage_number);
          if (judge == 0) {
            JOptionPane.showMessageDialog(frame, "选课成功");
            courseTypeModelBX = new CourseTypeModel("BX");
            courseTypeModelX = new CourseTypeModel("X");
            courseTypeModelZ = new CourseTypeModel("Z");
            otherMajorCourseModel = new OtherMajorCourseModel();
            chooseClassModel = new ChooseClassModel();

            BXtable.setModel(courseTypeModelBX);
            Xtable.setModel(courseTypeModelX);
            Ztable.setModel(courseTypeModelZ);
            otherMajorCourseTable.setModel(otherMajorCourseModel);
            chooseClassTable.setModel(chooseClassModel);

            leftWageLabel.setText(String.valueOf(chooseClassUtil.
                    getStudentLeftWage(currentStudent.getStudent_id())));
            setVisible(false);
          } else if (judge == -1) {
            JOptionPane.showMessageDialog(frame, "选课失败，当前课程已选");
            setVisible(false);
          } else if (judge == -2) {
            JOptionPane.showMessageDialog(frame, "选课失败，当前课程时间冲突");
            setVisible(false);
          } else if (judge == -3) {
            JOptionPane.showMessageDialog(frame, "选课失败，剩余积分不足");
            setVisible(false);
          } else if (judge == -4) {
            JOptionPane.showMessageDialog(frame, "选课失败，先修条件不满足");
            setVisible(false);
          }
        } else {

        }
      });

      no.addActionListener(e -> setVisible(false));


      add(panel, BorderLayout.SOUTH);
    }
  }

  private class ClassTimeCancelDialog extends JDialog {
    private ClassTimeCancelDialog(JFrame owner, JTable table) {
      super(owner, "Class Information", true);

      add(new JScrollPane(table));
      int DIALOG_WHITE = 600;
      int DIALOG_HEIGHT = 300;
      Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
      this.setBounds(point.x - DIALOG_WHITE / 2, point.y - DIALOG_HEIGHT / 2, DIALOG_WHITE, DIALOG_HEIGHT);
      JPanel panel = new JPanel();
      JLabel label = new JLabel("取消选择此课程？");
      JButton yes = new JButton("Yes");
      JButton no = new JButton("No");
      yes.addActionListener(e -> {
        ChooseClassUtil chooseClassUtil = new ChooseClassUtil();
        chooseClassUtil.deleteClassSelection(currentStudent.getStudent_id(),
                Integer.parseInt(table.getValueAt(0, 0).toString().trim().trim()));
//        System.out.println(Integer.parseInt(table.getValueAt(1, 0).toString().trim().trim()));
//        chooseClassModel.fireTableDataChanged();
        courseTypeModelBX = new CourseTypeModel("BX");
        courseTypeModelX = new CourseTypeModel("X");
        courseTypeModelZ = new CourseTypeModel("Z");
        otherMajorCourseModel = new OtherMajorCourseModel();
        chooseClassModel = new ChooseClassModel();

        BXtable.setModel(courseTypeModelBX);
        Xtable.setModel(courseTypeModelX);
        Ztable.setModel(courseTypeModelZ);
        otherMajorCourseTable.setModel(otherMajorCourseModel);
        chooseClassTable.setModel(chooseClassModel);

        leftWageLabel.setText(String.valueOf(chooseClassUtil.
                getStudentLeftWage(currentStudent.getStudent_id())));
        setVisible(false);
      });
      no.addActionListener(e -> setVisible(false));

      panel.add(label);
      panel.add(yes);
      panel.add(no);

      add(panel, BorderLayout.SOUTH);
    }
  }

  private void updateStudentInfo(Map<String, Object> map) {
    student_id.setText(map.get("student_id").toString().trim());
    name.setText(map.get("student_name").toString().trim());
    age.setText(map.get("age").toString().trim());
    sex.setText(map.get("sex").toString().trim());
    admission_year.setText(map.get("admission_year").toString().trim());
    administrative_class.setText(map.get("administrative_class").toString().trim());
    grade.setText(map.get("grade").toString().trim());
    instructor.setText(map.get("instructor_name").toString().trim());
    major.setText(map.get("major_name").toString().trim());
    nameLabel.setText(map.get("student_name").toString().trim());
    leftWageLabel.setText(map.get("left_wage").toString().trim());
  }

  private class CourseModel extends AbstractTableModel {
    CourseUtil courseUtil = new CourseUtil();
    List<Map<String, Object>> list =
            courseUtil.findCourseByStudentID(currentStudent.getStudent_id());

    String[] cols_name = {"year", "semester", "course_id", "acronym", "course_name", "class_id"};

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
      return cols_name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return list.get(rowIndex).get(cols_name[columnIndex]);
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

  private class ScoreModel extends AbstractTableModel {
    ScoreUtil scoreUtil = new ScoreUtil();
    List<Map<String, Object>> list =
            scoreUtil.getScoreByStudentID(currentStudent.getStudent_id());

    String[] cols_name = {"year", "semester", "student_id", "student_name", "course_name", "score"};

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
      return cols_name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return list.get(rowIndex).get(cols_name[columnIndex]);
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

  private class NoticeModel extends AbstractTableModel {
    ClassNoticeUtil classNoticeUtil = new ClassNoticeUtil();
    List<Map<String, Object>> list =
            classNoticeUtil.findNoticeByStudentId(currentStudent.getStudent_id());

    String[] cols_name = {"class_id", "course_name", "text", "publish_time"};

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
      return cols_name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return list.get(rowIndex).get(cols_name[columnIndex]);
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

  //  private class CourseChooseSuggestModel extends AbstractTableModel{
//    CourseUtil courseUtil = new CourseUtil();
//
//    List<Map<String, Object>> list =
//            courseUtil.
//
//    String[] cols_name = {"class_id", "course_name", "text", "publish_time"};
//  }
  private class CourseTypeModel extends AbstractTableModel {
    CourseUtil courseUtil = new CourseUtil();
    List<Map<String, Object>> list;

    private CourseTypeModel(String type) {
      list = courseUtil.findGraduNeedCourse(currentStudent.getStudent_id(), currentStudent.getMajor_id(), type);
    }

    String[] cols_name = {"class_id", "course_id", "acronym", "course_name", "capacity", "type_require"};

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
      return cols_name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return list.get(rowIndex).get(cols_name[columnIndex]);
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

  private class OtherMajorCourseModel extends AbstractTableModel {
    String[] cols_name = {"class_id", "course_id", "acronym", "course_name", "capacity", "type_require", "major_name"};

    CourseUtil courseUtil = new CourseUtil();
    List<Map<String, Object>> list = courseUtil.findOtherMajorCourse(currentStudent.getStudent_id(), currentStudent.getMajor_id());

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
      return cols_name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return list.get(rowIndex).get(cols_name[columnIndex]);
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

  private class ClassTimeModel extends AbstractTableModel {
    List<Map<String, Object>> list;

    private ClassTimeModel(int class_id) {
      list = lessonUtil.findLessonByClassID(class_id);
    }

    String[] cols_name = {"class_id", "instructor_name", "place", "floor", "room_number", "weekday", "week", "time"};

    LessonUtil lessonUtil = new LessonUtil();

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
      return cols_name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return list.get(rowIndex).get(cols_name[columnIndex]);
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

  private class ChooseClassModel extends AbstractTableModel {
    String[] cols_name = {"class_id", "course_id", "acronym", "course_name", "capacity", "major_name", "wage_number"};

    ChooseClassUtil chooseClassUtil = new ChooseClassUtil();
    List<Map<String, Object>> list = chooseClassUtil.findChooseClassByStudentID(currentStudent.getStudent_id());

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
      return cols_name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return list.get(rowIndex).get(cols_name[columnIndex]);
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

}


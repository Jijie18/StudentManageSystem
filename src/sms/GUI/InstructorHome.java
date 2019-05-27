package sms.GUI;

import org.apache.poi.ss.formula.functions.NumericFunction;
import sms.Session;
import sms.Utils.*;
import sms.beans.Instructor;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorHome {

  private JFrame frame;
  private JPanel instructorHome;
  private JButton exitButton;
  private JTabbedPane tabbedPane1;
  private JTextField instructor_id;
  private JTextField name;
  private JTextField sex;
  private JTextField major;
  private JTextField age;
  private JTable classTable;
  private JTable studentGPATable;
  private JTable noticeTable;
  private JButton insertNoticeButton;
  private JPasswordField passwordField1;
  private JPasswordField passwordField2;
  private JPasswordField passwordField3;
  private JButton changePasswordButton;
  private JTable teachClassTable;
  private JTable classScoreTable;
  private JButton deleteScoreButton;
  private JButton insertScoreButton;
  private JLabel nameLabel;
  private JTable teachClassTable2;
  private JButton deleteNoticeButton;
  private Instructor currentInstructor;

  private ClassScoreModel classScoreModel;
  private TeachClassModel teachClassModel;
  private NoticeModel noticeModel;
  private int class_id;

  public InstructorHome() {
    currentInstructor = (Instructor) Session.userInfo;
    InstructorUtil instructorUtil = new InstructorUtil();
    Map<String, Object> databaseInstructor = instructorUtil.
            findInstructorByID(currentInstructor.getInstructor_id());

    frame = new JFrame("InstructorHome");
    frame.setContentPane(instructorHome);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    UICommonUtil.makeFrameToCenter(frame);
    frame.setVisible(true);
    updateInstructorInfo(databaseInstructor);

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

      if (usedPassword.equals(currentInstructor.getPassword().trim())) {
        if (newPassword1.equals(newPassword2)) {
          int res = JOptionPane.showConfirmDialog(frame, "确认更改密码？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
          if (res == JOptionPane.YES_OPTION) {
            instructorUtil.changeInstructorPassword(currentInstructor.getInstructor_id(), newPassword1);
            currentInstructor.setPassword(newPassword1);
          }
        } else {
          JOptionPane.showMessageDialog(frame, "新密码两次输入不一致", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(frame, "当前密码错误", "提示", JOptionPane.INFORMATION_MESSAGE);
      }
    });

    classTable.setModel(new ClassInfoModel());
    studentGPATable.setModel(new StudentGPAModel());
    teachClassModel = new TeachClassModel();
    teachClassTable.setModel(teachClassModel);
    teachClassTable2.setModel(teachClassModel);

    teachClassTable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
          classScoreModel = new ClassScoreModel(Integer.parseInt(teachClassTable.
                  getValueAt(row, 4).toString().trim()));
          class_id = Integer.parseInt(teachClassTable.getValueAt(teachClassTable.
                  getSelectedRow(), 4).toString().trim());
          classScoreTable.setModel(classScoreModel);
        }
      }
    });

    teachClassTable2.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
          noticeModel = new NoticeModel(Integer.parseInt(teachClassTable.
                  getValueAt(row, 4).toString().trim()));
          class_id = Integer.parseInt(teachClassTable2.getValueAt(teachClassTable2.
                  getSelectedRow(), 4).toString().trim());
          noticeTable.setModel(noticeModel);
        }
      }
    });

    noticeTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {

        }
      }
    });

    deleteScoreButton.addActionListener(e -> {
      if (classScoreTable.getRowCount() > 0) {
        if (classScoreTable.getSelectedRow() > -1) {
          int res = JOptionPane.showConfirmDialog(null, "确认删除？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
          if (res == JOptionPane.YES_OPTION) {
            classScoreModel.remove(classScoreTable.getSelectedRow());
          } else {

          }
        } else {
          JOptionPane.showMessageDialog(null, "请选中成绩表中的一行", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(null, "请选择一个班级", "提示", JOptionPane.INFORMATION_MESSAGE);
      }
    });
    insertScoreButton.addActionListener(e -> {
      if (teachClassTable.getSelectedRow() > -1) {
        Map<String, Object> map = new HashMap<>();
        try {
          map.put("class_id", class_id);
          classScoreModel.addRow(map);
          classScoreTable.editCellAt(classScoreTable.getRowCount() - 1, 0);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      } else {
        JOptionPane.showMessageDialog(null, "请选择一个班级", "提示", JOptionPane.INFORMATION_MESSAGE);
      }
    });

//    classScoreTable.addMouseListener(new MouseAdapter() {
//      public void mouseClicked(MouseEvent e) {
//        if (e.getClickCount() == 2) {
//          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
//          classScoreModel = new ClassScoreModel(Integer.parseInt(teachClassTable.
//                  getValueAt(row, 4).toString().trim()));
//          classScoreTable.setModel(classScoreModel);
//        }
//      }
//    });
    classScoreTable.addKeyListener(new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//          classScoreTable.getCellEditor().stopCellEditing();

          if (classScoreTable.getValueAt(classScoreTable.getRowCount() - 1, 1) == null) {
            JOptionPane.showMessageDialog(null, "请输入学生id");
          } else if (classScoreTable.getValueAt(classScoreTable.getRowCount() - 1, 4) == null) {
            JOptionPane.showMessageDialog(null, "请输入成绩");
          } else {
            if (classScoreModel.saveOneRowToDB()) {
              classScoreModel = new ClassScoreModel(Integer.parseInt(
                      classScoreTable.getValueAt(0, 0).toString().trim()));
              classScoreTable.setModel(classScoreModel);
            } else {
              JOptionPane.showMessageDialog(null, "信息错误，请重新输入");
            }
          }
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });

    noticeTable.addKeyListener(new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//          classScoreTable.getCellEditor().stopCellEditing();

          if (noticeTable.getValueAt(noticeTable.getRowCount() - 1, 1) == null) {
            JOptionPane.showMessageDialog(null, "请输入消息");
          } else {
            noticeModel.saveOneRowToDB();
            noticeModel = new NoticeModel(Integer.parseInt(noticeTable.
                    getValueAt(0, 0).toString().trim()));
            noticeTable.setModel(noticeModel);
          }
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });

    deleteNoticeButton.addActionListener(e -> {
      if (noticeTable.getRowCount() > 0) {
        if (noticeTable.getSelectedRow() > -1) {
          int res = JOptionPane.showConfirmDialog(null, "确认删除？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
          if (res == JOptionPane.YES_OPTION) {
            int row = noticeTable.getSelectedRow();
            noticeModel.removeRow(row);
          } else {

          }
        } else {
          JOptionPane.showMessageDialog(null, "请选中一行", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(null, "请选择一条数据", "提示", JOptionPane.INFORMATION_MESSAGE);
      }
    });

    insertNoticeButton.addActionListener(e -> {
      if (teachClassTable2.getSelectedRow() > -1) {

        Map<String, Object> map = new HashMap<>();
        map.put("class_id", class_id);
        noticeModel.addRow(map);
      } else {
        JOptionPane.showMessageDialog(null, "请选择一个班级", "提示", JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }


  private void updateInstructorInfo(Map<String, Object> map) {
    instructor_id.setText(map.get("instructor_id").toString().trim());
    name.setText(map.get("instructor_name").toString().trim());
    age.setText(map.get("age").toString().trim());
    sex.setText(map.get("sex").toString().trim());
    major.setText(map.get("major_name").toString().trim());
    nameLabel.setText(map.get("instructor_name").toString().trim());
  }

  private class ClassInfoModel extends AbstractTableModel {
    ClassUtil classUtil = new ClassUtil();
    List<Map<String, Object>> list =
            classUtil.findCurrentSemesterClassByInstructorID(currentInstructor.getInstructor_id());

    String[] cols_name = {"year", "semester", "course_id", "course_name", "class_id"};

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

  private class StudentGPAModel extends AbstractTableModel {
    ScoreUtil scoreUtil = new ScoreUtil();
    List<Map<String, Object>> list =
            scoreUtil.getStudentGPAByInstructorID(currentInstructor.getInstructor_id());

    String[] cols_name = {"student_id", "student_name", "administrative_class",
            "admission_year", "GPA"};

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

  // TODO: 2019/5/21 发布通知
  private class NoticeModel extends AbstractTableModel {
    int class_id;
    ClassNoticeUtil classNoticeUtil;
    List<Map<String, Object>> list;

    public NoticeModel(int class_id) {
      this.class_id = class_id;
      classNoticeUtil = new ClassNoticeUtil();
      list = classNoticeUtil.findNoticeByClassId(class_id);
    }

    String[] cols_name = {"class_id", "text", "publish_time"};

    public void addRow(Map<String, Object> map) {
      list.add(map);
      fireTableDataChanged();
    }

    public void saveOneRowToDB() {
      int row = noticeTable.getSelectedRow();
//      System.out.println(row);
      Map<String, Object> map = list.get(row);
//      System.out.println(map);
      classNoticeUtil.publishNoticeToClass(
              Integer.parseInt(map.get("class_id").toString().trim()),
              map.get("text").toString().trim());
    }


    public void removeRow(int rowIndex) {
      Map<String, Object> map = list.get(rowIndex);
      if (map.containsKey("notice_id")) {
        classNoticeUtil.deleteNotice(Integer.parseInt(map.get("notice_id").toString().trim()));
      }
      list.remove(rowIndex);
      fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
      return list.size();
    }

    @Override
    public int getColumnCount() {
      return cols_name.length;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      list.get(rowIndex).put(cols_name[columnIndex], aValue);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return list.get(rowIndex).get(cols_name[columnIndex]);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return columnIndex == 1;
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

  private class TeachClassModel extends AbstractTableModel {
    ClassUtil classUtil = new ClassUtil();
    List<Map<String, Object>> list =
            classUtil.findCurrentSemesterClassByInstructorID(currentInstructor.getInstructor_id());

    String[] cols_name = {"year", "semester", "course_id", "course_name", "class_id"};

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

  private class ClassScoreModel extends AbstractTableModel {
    int class_id;
    List<Map<String, Object>> list;
    ScoreUtil scoreUtil;

    ClassScoreModel(int class_id) {
      scoreUtil = new ScoreUtil();

      this.class_id = class_id;
      list = scoreUtil.getScoreByClassID(class_id);
    }

    String[] cols_name = {"class_id", "student_id", "student_name", "grade", "score"};

    public void addRow(Map<String, Object> map) {
      list.add(map);
      fireTableDataChanged();
    }

    public boolean saveOneRowToDB() {
      boolean flag = false;
      int row = classScoreTable.getSelectedRow();
      System.out.println(row);
      Map<String, Object> map = list.get(row);
      System.out.println(map);
      try {
        flag = scoreUtil.saveScore(
                Integer.parseInt(map.get("student_id").toString().trim()),
                Integer.parseInt(map.get("class_id").toString().trim()),
                Integer.parseInt(map.get("score").toString().trim()));
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      return flag;
    }

    public void remove(int rowIndex) {
      Map<String, Object> map = list.get(rowIndex);
      if (map.containsKey("student_id") && map.containsKey("class_id")) {
        scoreUtil.deleteScore(Integer.parseInt(map.get("student_id").toString().trim()),
                Integer.parseInt(map.get("class_id").toString().trim()));
      }
      list.remove(rowIndex);
      fireTableDataChanged();
    }

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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      super.setValueAt(aValue, rowIndex, columnIndex);
      list.get(rowIndex).put(cols_name[columnIndex], aValue);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return columnIndex == 1 || columnIndex == 4;
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }
}

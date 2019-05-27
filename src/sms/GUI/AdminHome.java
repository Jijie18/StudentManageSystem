package sms.GUI;

import sms.Session;
import sms.Utils.*;
import sms.beans.Admin;
import sms.beans.Class;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminHome {
  private JPanel adminHome;
  private JButton exitButton;
  private JTabbedPane tabbedPane1;
  private JTable studentTable;
  private JButton deleteStudentButton;
  private JButton insertStudentButton;
  private JTable instructorTable;
  private JButton endChooseClassButton;
  private JTable chooseClassTable;
  private JButton deleteInstructorButton;
  private JButton insertInstructorButton;
  private JTable scoreInfoTable;
  private JTable classTable;
  private JLabel currentPageLabel1;
  private JButton nextPageButton1;
  private JButton lastPageButton1;
  private JButton firstPageButton1;
  private JButton nextPageButton2;
  private JButton lastPageButton2;
  private JButton firstPageButton2;
  private JLabel currentPageLabel2;
  private JTextField filePathTextField;
  private JButton importFileButton;
  private JButton chooseFileButton;
  private JLabel currentPageLabel3;
  private JButton nextPageButton3;
  private JButton lastPageButton3;
  private JButton firstPageButton3;
  private JPasswordField passwordField1;
  private JPasswordField passwordField2;
  private JPasswordField passwordField3;
  private JButton changePasswordButton;
  private JButton nextPageButton4;
  private JButton lastPageButton4;
  private JButton firstPageButton4;
  private JLabel currentPageLabel4;
  private JLabel nameLabel;
  private JTextField textField1;
  private JFrame frame;

  private StudentModel studentModel;
  private InstructorModel instructorModel;
  private ChooseClassModel chooseClassModel;
  private ClassModel classModel;
  private ScoreInfoModel scoreInfoModel;

  private Admin currentAdmin;

  private int student_count;
  private int instructor_count;
  private int class_choose_count;
  private int class_count;

  private int currentPage1 = 1;
  private int currentPage2 = 1;
  private int currentPage3 = 1;
  private int currentPage4 = 1;


  private final int singlePageRowsNum = 20;


  public AdminHome() {
    currentAdmin = (Admin) Session.userInfo;
    nameLabel.setText(currentAdmin.getName());
    frame = new JFrame("AdminHome");
    frame.setContentPane(adminHome);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    UICommonUtil.makeFrameToCenter(frame);
    frame.setVisible(true);
    exitButton.addActionListener(e -> {
      new LoginHome();
      frame.dispose();
      LoginLogoutUtil loginLogoutUtils = new LoginLogoutUtil();
      loginLogoutUtils.Logout();
    });
    studentModel = new StudentModel(1);
    studentTable.setModel(studentModel);
    firstPageButton4.addActionListener(
            e -> {
              studentModel.toPage(1);
              currentPageLabel4.setText(1 + "");
              currentPage4 = 1;
            });

    nextPageButton4.addActionListener(e -> {
      int total_page;
      if (student_count % singlePageRowsNum == 0)
        total_page = student_count / singlePageRowsNum;
      else
        total_page = student_count / singlePageRowsNum + 1;


      if (currentPage4 == total_page)
        JOptionPane.showMessageDialog(null, "当前为末页");
      else {
        studentModel.toPage(++currentPage4);
        currentPageLabel4.setText(String.valueOf(currentPage4));
      }
    });

    lastPageButton4.addActionListener(e -> {
      if (currentPage4 == 1) {
        JOptionPane.showMessageDialog(null, "当前为首页");
      } else {
        studentModel.toPage(--currentPage4);
        currentPageLabel4.setText(String.valueOf(currentPage4));
      }
    });
    insertStudentButton.addActionListener(e -> {
      Map<String, Object> map = new HashMap<>();
      int rowIndex = studentTable.getSelectedRow() + 1;
      if (rowIndex > 0) {
        studentModel.addRow(map, rowIndex);
      } else {
        JOptionPane.showMessageDialog(null, "请选中一行");
      }
    });

    studentTable.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (studentModel.saveOneRowToDB(studentTable.getSelectedRow())) {
            JOptionPane.showMessageDialog(null, "更新数据成功");
          } else {
            JOptionPane.showMessageDialog(null, "更新数据失败，请输入完整数据");
            studentTable.updateUI();
          }
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
      }
    });

    deleteStudentButton.addActionListener(e -> {
      if (studentTable.getSelectedRow() > -1) {
        int res = JOptionPane.showConfirmDialog(null, "确认删除？",
                "提示", JOptionPane.YES_NO_CANCEL_OPTION);
        if (res == JOptionPane.YES_OPTION) {
          studentModel.removeRow(studentTable.getSelectedRow());
        } else {

        }
      }
    });

    instructorModel = new InstructorModel(1);
    instructorTable.setModel(instructorModel);
    firstPageButton3.addActionListener(
            e -> {
              instructorModel.toPage(1);
              currentPageLabel3.setText(1 + "");
              currentPage3 = 1;
            });
    nextPageButton3.addActionListener(e -> {
      int total_page;
      if (instructor_count % singlePageRowsNum == 0)
        total_page = instructor_count / singlePageRowsNum;
      else
        total_page = instructor_count / singlePageRowsNum + 1;


      if (currentPage3 == total_page)
        JOptionPane.showMessageDialog(null, "当前为末页");
      else {
        instructorModel.toPage(++currentPage3);
        currentPageLabel3.setText(String.valueOf(currentPage3));
      }
    });

    lastPageButton3.addActionListener(e -> {
      if (currentPage3 == 1) {
        JOptionPane.showMessageDialog(null, "当前为首页");
      } else {
        instructorModel.toPage(--currentPage3);
        currentPageLabel3.setText(String.valueOf(currentPage3));
      }
    });

    insertInstructorButton.addActionListener(e -> {
      Map<String, Object> map = new HashMap<>();
      int rowIndex = instructorTable.getSelectedRow() + 1;
      if (rowIndex > 0) {
        instructorModel.addRow(map, rowIndex);

      } else {
        JOptionPane.showMessageDialog(null, "请选中一行");
      }
    });

    instructorTable.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (instructorModel.saveOneRowToDB(instructorTable.getSelectedRow())) {
            JOptionPane.showMessageDialog(null, "更新数据成功");
          } else {
            JOptionPane.showMessageDialog(null, "更新数据失败，请检查数据");
          }
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
      }
    });

    deleteInstructorButton.addActionListener(e -> {
      if (instructorTable.getSelectedRow() > -1) {
        int res = JOptionPane.showConfirmDialog(null, "确认删除？",
                "提示", JOptionPane.YES_NO_CANCEL_OPTION);
        if (res == JOptionPane.YES_OPTION)
          instructorModel.removeRow(instructorTable.getSelectedRow());
        else {

        }
      }
    });
    chooseClassModel = new ChooseClassModel(1);
    chooseClassTable.setModel(chooseClassModel);

    firstPageButton1.addActionListener(
            e -> {
              chooseClassModel.toPage(1);
              currentPageLabel1.setText(1 + "");
              currentPage1 = 1;
            });
    nextPageButton1.addActionListener(e -> {
      int total_page;
      if (class_choose_count % singlePageRowsNum == 0)
        total_page = class_choose_count / singlePageRowsNum;
      else
        total_page = class_choose_count / singlePageRowsNum + 1;


      if (currentPage1 == total_page)
        JOptionPane.showMessageDialog(null, "当前为末页");
      else {
        chooseClassModel.toPage(++currentPage1);
        currentPageLabel1.setText(String.valueOf(currentPage1));
      }
    });

    lastPageButton1.addActionListener(e -> {
      if (currentPage1 == 1) {
        JOptionPane.showMessageDialog(null, "当前为首页");
      } else {
        chooseClassModel.toPage(--currentPage1);
        currentPageLabel1.setText(String.valueOf(currentPage1));
      }
    });

    endChooseClassButton.addActionListener(e -> {
      int res = JOptionPane.showConfirmDialog(null,
              "确定现在终止学生选课？\n将会清空所有学生选课数据并导入学生课表", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
      if (res == JOptionPane.YES_OPTION) {
        chooseClassModel.endChooseClass();
      } else {

      }
    });

    classModel = new ClassModel(1);
    classTable.setModel(classModel);

    firstPageButton2.addActionListener(
            e -> {
              classModel.toPage(1);
              currentPageLabel2.setText(1 + "");
              currentPage2 = 1;
            });
    nextPageButton2.addActionListener(e -> {
      int total_page;
      if (class_count % singlePageRowsNum == 0)
        total_page = class_count / singlePageRowsNum;
      else
        total_page = class_count / singlePageRowsNum + 1;
      if (currentPage2 == total_page)
        JOptionPane.showMessageDialog(null, "当前为末页");
      else {
        classModel.toPage(++currentPage2);
        currentPageLabel2.setText(String.valueOf(currentPage2));
      }
    });

    lastPageButton2.addActionListener(e -> {
      if (currentPage2 == 1) {
        JOptionPane.showMessageDialog(null, "当前为首页");
      } else {
        classModel.toPage(--currentPage2);
        currentPageLabel2.setText(String.valueOf(currentPage2));
      }
    });

    classTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());

        int class_id = Integer.parseInt(classTable.getValueAt(row, 5).toString().trim());
        scoreInfoModel = new ScoreInfoModel(class_id);
        scoreInfoTable.setModel(scoreInfoModel);
      }
    });

    scoreInfoTable.addKeyListener(new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (scoreInfoModel.saveOneRowToDB(scoreInfoTable.getSelectedRow())) {
            JOptionPane.showMessageDialog(null, "更新数据成功");
          } else {
            JOptionPane.showMessageDialog(null, "更新数据失败，请检查数据");
            scoreInfoModel.fireTableDataChanged();
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

    changePasswordButton.addActionListener(e -> {
      String usedPassword = String.valueOf(passwordField1.getPassword());
      String newPassword1 = String.valueOf(passwordField2.getPassword());
      String newPassword2 = String.valueOf(passwordField3.getPassword());

      if (usedPassword.equals(currentAdmin.getPassword().trim())) {
        if (newPassword1.equals(newPassword2)) {
          int res = JOptionPane.showConfirmDialog(frame, "确认更改密码？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
          if (res == JOptionPane.YES_OPTION) {
            AdminUtil adminUtil = new AdminUtil();
            adminUtil.changeAdminPassword(currentAdmin.getAdmin_id(), newPassword1);
            currentAdmin.setPassword(newPassword1);
          }
        } else {
          JOptionPane.showMessageDialog(frame, "新密码两次输入不一致", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
      } else {
        JOptionPane.showMessageDialog(frame, "当前密码错误", "提示", JOptionPane.INFORMATION_MESSAGE);
      }
    });

    chooseFileButton.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File file) {
          if (file.isDirectory())
            return true;
          else {
            String fileName = file.getName();
            return fileName.endsWith(".xlsx") || fileName.endsWith(".xls");
          }
        }

        @Override
        public String getDescription() {
          return "Excel(.xlsx, .xls)";
        }
      };

      fileChooser.setFileFilter(fileFilter);
      int ret = fileChooser.showOpenDialog(fileChooser);
      if (ret == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        filePathTextField.setText(filePath);
      }
    });

    importFileButton.addActionListener(e -> {
      int resConfim = JOptionPane.showConfirmDialog(null, "确认导入文件？", "提示", JOptionPane.YES_NO_OPTION);
      if (resConfim == JOptionPane.YES_OPTION) {
        String filePath = filePathTextField.getText();
        if (filePath != null && !filePath.isEmpty()) {
          int res = FileImportUtil.fileImport(filePath);
          if (res == -1) {
            JOptionPane.showMessageDialog(null, "读取文件失败");
          } else if (res == -2) {
            JOptionPane.showMessageDialog(null, "选取文件不是excel文件");
          } else if (res == -3) {

          } else if (res == 0) {
            JOptionPane.showMessageDialog(null, "导入数据库错误，请检查数据/文件名/列名");
          } else {
            JOptionPane.showMessageDialog(null, "导入数据成功");
          }
        } else {
          JOptionPane.showMessageDialog(null, "文件路径不为空");
        }
      } else {

      }

    });


  }

  private class StudentModel extends AbstractTableModel {
    List<Map<String, Object>> list;
    StudentUtil studentUtil;
    String[] cols_name = {"student_id", "student_name", "age", "sex",
            "admission_year", "administrative_class", "grade", "instructor_id",
            "instructor_name", "major_id", "major_name"};

    public StudentModel(int page) {
      studentUtil = new StudentUtil();
      list = studentUtil.findAllStudentsPage(page, singlePageRowsNum);
      student_count = studentUtil.getStudentCount();
    }

    public void toPage(int page_num) {
      list = studentUtil.findAllStudentsPage(page_num, singlePageRowsNum);
      fireTableDataChanged();
    }

    public void addRow(Map<String, Object> map, int rowIndex) {
      list.add(rowIndex, map);
      fireTableDataChanged();
      student_count++;
    }

    public boolean saveOneRowToDB(int rowIndex) {
      Map<String, Object> map = list.get(rowIndex);
      boolean flag = false;
      if (!map.containsKey("student_id")) {
        flag = studentUtil.addStudent(map);
        fireTableDataChanged();
      } else {
        flag = studentUtil.updateStudent(map);
      }
      return flag;
    }

    public void removeRow(int rowIndex) {
      Map<String, Object> map = list.get(rowIndex);
      if (map.containsKey("student_id")) {
        studentUtil.deleteStudent(Integer.parseInt(map.get("student_id").toString().trim()));
      }
      list.remove(rowIndex);
      fireTableDataChanged();
      student_count--;
    }

    public void update() {
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
      return columnIndex != 0 && columnIndex != 8 && columnIndex != 10;
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

  private class InstructorModel extends AbstractTableModel {
    List<Map<String, Object>> list;
    InstructorUtil instructorUtil;
    String[] cols_name = {"instructor_id", "instructor_name", "age", "sex",
            "joined_date", "department_name", "major_id", "major_name"};

    public void update() {
      fireTableDataChanged();
    }

    public InstructorModel(int page) {
      instructorUtil = new InstructorUtil();
      list = instructorUtil.findAllInstructorsPages(page, singlePageRowsNum);
      instructor_count = instructorUtil.getInstructorCount();
    }

    public void toPage(int page_num) {
      list = instructorUtil.findAllInstructorsPages(page_num, singlePageRowsNum);
      fireTableDataChanged();
    }

    public void addRow(Map<String, Object> map, int rowIndex) {
      list.add(rowIndex, map);
      fireTableDataChanged();
    }

    public boolean saveOneRowToDB(int rowIndex) {
      Map<String, Object> map = list.get(rowIndex);
      boolean flag = false;
      if (!map.containsKey("student_id")) {
        flag = instructorUtil.addInstructor(map);
        fireTableDataChanged();
        instructor_count++;
      } else {
        flag = instructorUtil.updateInstructor(map);
      }
      return flag;
    }

    public void removeRow(int rowIndex) {
      Map<String, Object> map = list.get(rowIndex);
      if (map.containsKey("instructor_id")) {
        instructorUtil.deleteInstructor(Integer.parseInt(map.get("instructor_id").toString().trim()));
      }
      list.remove(rowIndex);
      fireTableDataChanged();
      instructor_count--;
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
      return columnIndex != 0 && columnIndex != 5 && columnIndex != 7;
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }


  private class ChooseClassModel extends AbstractTableModel {
    List<Map<String, Object>> list;
    ChooseClassUtil chooseClassUtil;
    String[] cols_name = {"student_id", "class_id", "course_id", "acronym",
            "course_name", "capacity", "major_name", "wage_number"};

    public void update() {
      fireTableDataChanged();
    }

    public ChooseClassModel(int page) {
      chooseClassUtil = new ChooseClassUtil();
      list = chooseClassUtil.findAllClassChoosePage(page, singlePageRowsNum);
      class_choose_count = chooseClassUtil.getChooseClassCount();
    }

    public void toPage(int page_num) {
      list = chooseClassUtil.findAllClassChoosePage(page_num, singlePageRowsNum);
      fireTableDataChanged();
    }

    public void endChooseClass() {
      chooseClassUtil.importClassSelecltionResult();
      chooseClassUtil.clearClassSelection();
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return false;
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

  private class ClassModel extends AbstractTableModel {
    List<Map<String, Object>> list;
    ClassUtil classUtil;
    String[] cols_name = {"year", "semester", "major_name", "course_id", "course_name",
            "class_id", "capacity"};

    public ClassModel(int page) {
      classUtil = new ClassUtil();
      list = classUtil.findAllClassPage(page, singlePageRowsNum);
      class_count = classUtil.getClassCount();
    }

    public void update() {
      fireTableDataChanged();
    }

    public void toPage(int page_num) {
      list = classUtil.findAllClassPage(page_num, singlePageRowsNum);
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return false;
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

  private class ScoreInfoModel extends AbstractTableModel {
    List<Map<String, Object>> list;
    ScoreUtil scoreUtil;
    String[] cols_name = {"class_id", "student_id", "student_name", "grade", "score"};

    public ScoreInfoModel(int class_id) {
      scoreUtil = new ScoreUtil();
      list = scoreUtil.getScoreByClassID(class_id);
    }

    public void update() {
      fireTableDataChanged();
    }

    public boolean saveOneRowToDB(int rowIndex) {
      Map<String, Object> map = list.get(rowIndex);
      boolean flag = false;
      try {
        flag = scoreUtil.updateScore(Integer.parseInt(map.get("student_id").toString().trim()),
                Integer.parseInt(map.get("class_id").toString().trim()),
                Integer.parseInt(map.get("score").toString().trim()));
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      fireTableDataChanged();

      return flag;
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
      return columnIndex == 4;
    }

    @Override
    public String getColumnName(int column) {
      return cols_name[column];
    }
  }

}

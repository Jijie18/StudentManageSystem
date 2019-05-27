package sms.GUI;

import sms.Utils.LoginLogoutUtil;
import sms.Utils.UICommonUtil;
import sms.beans.*;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHome {
  private JTextField usernameTextField;
  private JTextField passwordTextField;
  private JRadioButton studentRadioButton;
  private JRadioButton instructorRadioButton;
  private JRadioButton adminRadioButton;
  private JButton LoginButton;
  private JPanel LoginPanel;
  private static JFrame frame;

  public LoginHome() {
    PlainDocument doc = new PlainDocument();
    doc.setDocumentFilter(new DocumentFilter() {
      @Override
      public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
              throws BadLocationException {
        fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
      }

      @Override
      public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
              throws BadLocationException {
        fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
      }
    });

    usernameTextField.setDocument(doc);

    LoginButton.addActionListener(e -> {
      if (usernameTextField.getText() == null || usernameTextField.getText().length() <= 0) {
        JOptionPane.showMessageDialog(frame, "请输入用户名", "提示", JOptionPane.WARNING_MESSAGE);
        return;
      }
      if (passwordTextField.getText() == null || passwordTextField.getText().length() <= 0) {
        JOptionPane.showMessageDialog(frame, "请输入密码", "提示", JOptionPane.WARNING_MESSAGE);
        return;
      }


      LoginLogoutUtil loginLogoutUtils = new LoginLogoutUtil();
      int id = Integer.parseInt(usernameTextField.getText().trim());
      String password = passwordTextField.getText().trim();
      Object userinfo = null;

      if (studentRadioButton.isSelected()) {
        userinfo = new Student(id, password);
      } else if (adminRadioButton.isSelected()) {
        userinfo = new Admin(id, password);
      } else {
        userinfo = new Instructor(id, password);
      }
//

      String result = loginLogoutUtils.Login(userinfo);
      // System.out.println(userinfo);

      if (!result.equals("Success")) {
        JOptionPane.showMessageDialog(frame, result, "提示", JOptionPane.WARNING_MESSAGE);
        return;
      } else {
        if (studentRadioButton.isSelected()) {
          new StudentHome();
        } else if (adminRadioButton.isSelected()) {
          new AdminHome();
        } else {
          new InstructorHome();
        }

        frame.dispose();
      }
    });


    frame = new JFrame("Login");
    frame.setContentPane(LoginPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    UICommonUtil.makeFrameToCenter(frame);
    frame.setSize(400, 300);

    frame.setVisible(true);


    usernameTextField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
          passwordTextField.requestFocus();
        }
        super.keyPressed(e);
      }
    });
    passwordTextField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
          LoginButton.doClick();
        }
        super.keyPressed(e);
      }
    });
  }
}


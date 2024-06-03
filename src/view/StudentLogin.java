package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.studao;
import model.Student;
import util.DbUtil;
import util.StrUtil;

public class StudentLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField userNameTxt;
    private JPasswordField passwordTxt;
    private JPanel contentPane;
    private DbUtil dbUtil = new DbUtil();
    private studao userDao = new studao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentLogin frame = new StudentLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public StudentLogin() {
        setResizable(false);
        setTitle("学生登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 810, 639);

        JLabel lblNewLabel = new JLabel("图书使用系统");
        lblNewLabel.setIcon(new ImageIcon(StudentLogin.class.getResource("/images/7755372_hipster_lifestyle_book_reading_library_icon.png")));
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 22));

        JLabel lblNewLabel_1 = new JLabel("学号:");
        lblNewLabel_1.setIcon(new ImageIcon(StudentLogin.class.getResource("/images/309035_user_account_human_person_icon.png")));
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));

        JLabel lblNewLabel_2 = new JLabel("密码：");
        lblNewLabel_2.setIcon(new ImageIcon(StudentLogin.class.getResource("/images/2538749_key_open_password_security_icon.png")));
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));

        userNameTxt = new JTextField();
        userNameTxt.setColumns(10);

        passwordTxt = new JPasswordField();
        passwordTxt.setColumns(10);

        JButton btnNewButton = new JButton("登录");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logInActionPerformed(e);
            }
        });

        JButton btnNewButton_1 = new JButton("重置");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(141)
                    .addComponent(btnNewButton)
                    .addPreferredGap(ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                    .addComponent(btnNewButton_1)
                    .addGap(227))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(118)
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                            .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18)
                                .addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED))
                            .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(263, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(82)
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                            .addGap(73)
                            .addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                    .addGap(78)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
                    .addGap(75)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnNewButton)
                        .addComponent(btnNewButton_1))
                    .addContainerGap(82, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);
        
        this.setLocationRelativeTo(null); // 居中
    }

    /**
     * 重置事件处理
     */
    private void resetValueActionPerformed(ActionEvent evt) {
        this.userNameTxt.setText("");
        this.passwordTxt.setText("");
    }

    /**
     * 登陆处理
     */
    private void logInActionPerformed(ActionEvent evt) {
        String idStr = this.userNameTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if (StrUtil.isEmpty(idStr)) {
            JOptionPane.showMessageDialog(null, "学号不能为空");
            return;
        }
        if (StrUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空");
            return;
        }

        int id = Integer.parseInt(idStr);  // 确保将学号转换为整数
        Student user = new Student(id, password);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            Student curUser = userDao.login(con, user);
            if (curUser != null) {
                JOptionPane.showMessageDialog(null, "登陆成功");
                dispose();
                new StuMain(curUser).setVisible(true);  // 传递 Student 对象给 StuMain
            } else {
                JOptionPane.showMessageDialog(null, "学号或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "登录失败");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
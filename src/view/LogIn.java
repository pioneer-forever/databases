package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import util.DbUtil;
import util.StrUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class LogIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField UserNameTxt;
    private JPasswordField PasswordTxt;
    
    private DbUtil dbUtil=new DbUtil();
    private UserDao userDao=new UserDao();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogIn frame = new LogIn();
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
    public LogIn() {
        setResizable(false);
        setTitle("管理员登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 810, 639);

        JLabel lblNewLabel = new JLabel("图书管理系统");
        lblNewLabel.setIcon(new ImageIcon(LogIn.class.getResource("/images/7755372_hipster_lifestyle_book_reading_library_icon.png")));
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 22));

        JLabel lblNewLabel_1 = new JLabel("用户名:");
        lblNewLabel_1.setIcon(new ImageIcon(LogIn.class.getResource("/images/309035_user_account_human_person_icon.png")));
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));

        JLabel lblNewLabel_2 = new JLabel("密  码：");
        lblNewLabel_2.setIcon(new ImageIcon(LogIn.class.getResource("/images/2538749_key_open_password_security_icon.png")));
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));

        UserNameTxt = new JTextField();
        UserNameTxt.setColumns(10);

        PasswordTxt = new JPasswordField();
        PasswordTxt.setColumns(10);

        JButton btnNewButton = new JButton("登录");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogInActionPerformed(e);
            }
        });

        JButton btnNewButton_1 = new JButton("重置");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResetValueActionPerformed(e);
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
                                .addComponent(PasswordTxt, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED))
                            .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(UserNameTxt, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))))
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
                            .addComponent(UserNameTxt, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                    .addGap(78)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(PasswordTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
                    .addGap(75)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnNewButton)
                        .addComponent(btnNewButton_1))
                    .addContainerGap(82, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);
        
        this.setLocationRelativeTo(null);//juzhong
        
    }

    /**
     * 重置事件处理
     * @param evt
     */
    private void ResetValueActionPerformed(ActionEvent evt) {
        this.UserNameTxt.setText("");
        this.PasswordTxt.setText("");
    }

    /**
     * 登陆处理
     * @param evt
     */
    private void LogInActionPerformed(ActionEvent evt) {
        String userName = this.UserNameTxt.getText();
        String password = new String(this.PasswordTxt.getPassword());
        // 这里可以添加你的登陆处理逻辑
        if (StrUtil.isEmpty(userName)) {
        	JOptionPane.showMessageDialog(null, "用户名不能为空");
        }
        if (StrUtil.isEmpty(password)) {
        	JOptionPane.showMessageDialog(null, "密码不能为空");
        }
        User user=new User(userName,password);
        Connection con=null;
        try {
			con=dbUtil.getCon();
			User curUser=userDao.login(con, user);
			if (curUser !=null) {
	        	JOptionPane.showMessageDialog(null, "登陆成功");
	        	dispose();
	        	new Main().setVisible(true);
	        }
			else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误");
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}

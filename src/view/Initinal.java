package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Panel;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

public class Initinal extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Initinal frame = new Initinal();
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
	public Initinal() {
		setIconifiable(true);
		setClosable(true);
		setTitle("关于我们");
		setBounds(100, 100, 837, 639);
		
		JLabel lblNewLabel = new JLabel("欢迎各位管理员登录系统。");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JPanel 管理员近日工作安排 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(97)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
							.addGap(127))
						.addComponent(管理员近日工作安排, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(201, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(管理员近日工作安排, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("端午节假期图书馆闭关安排");
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1_2 = new JLabel("新增中文书库");
		lblNewLabel_1_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("新书录入通知");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout gl_管理员近日工作安排 = new GroupLayout(管理员近日工作安排);
		gl_管理员近日工作安排.setHorizontalGroup(
			gl_管理员近日工作安排.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_管理员近日工作安排.createSequentialGroup()
					.addGap(86)
					.addGroup(gl_管理员近日工作安排.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_1_1_1)
						.addComponent(lblNewLabel_1_2))
					.addContainerGap(156, Short.MAX_VALUE))
		);
		gl_管理员近日工作安排.setVerticalGroup(
			gl_管理员近日工作安排.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_管理员近日工作安排.createSequentialGroup()
					.addGap(64)
					.addComponent(lblNewLabel_1_2)
					.addGap(49)
					.addComponent(lblNewLabel_1_1_1)
					.addGap(64)
					.addComponent(lblNewLabel_1)
					.addContainerGap(98, Short.MAX_VALUE))
		);
		管理员近日工作安排.setLayout(gl_管理员近日工作安排);
		getContentPane().setLayout(groupLayout);

	}
}

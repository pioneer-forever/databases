
package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JDesktopPane table; // 使用类成员变量

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
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
    public Main() {
        setTitle("图书管理系统主界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 991, 678);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("基本数据维护");
        mnNewMenu.setFont(new Font("宋体", Font.PLAIN, 20));
        mnNewMenu.setForeground(new Color(0, 0, 0));
        menuBar.add(mnNewMenu);
        
        JMenu mnNewMenu_2 = new JMenu("图书类别管理");
        mnNewMenu_2.setIcon(new ImageIcon(Main.class.getResource("/images/4634455_article_content_interface_search_icon.png")));
        mnNewMenu_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        mnNewMenu.add(mnNewMenu_2);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("类别索引添加");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AreaAdder areaAdder = new AreaAdder();
                areaAdder.setVisible(true);
                table.add(areaAdder);
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_1);
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("类别索引维护");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AreaMangement areaMangement = new AreaMangement();
                areaMangement.setVisible(true);
                table.add(areaMangement);
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_2);
        
        JMenu mnNewMenu_3 = new JMenu("图书管理");
        mnNewMenu_3.setIcon(new ImageIcon(Main.class.getResource("/images/3994420_draw_edit_new_pen_write_icon.png")));
        mnNewMenu_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        mnNewMenu.add(mnNewMenu_3);
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("图书添加");
        mntmNewMenuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        mntmNewMenuItem_3.setIcon(new ImageIcon(Main.class.getResource("/images/6590489_add_and_book_education_learning_icon.png")));
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		BookAdder bookAddInterFrm  =new BookAdder();
				bookAddInterFrm.setVisible(true);
				table.add(bookAddInterFrm);
        		
        	}
        });
        mnNewMenu_3.add(mntmNewMenuItem_3);
        
        JMenuItem mntmNewMenuItem_4 = new JMenuItem("图书维护");
        mntmNewMenuItem_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        mntmNewMenuItem_4.setBackground(new Color(240, 240, 240));
        mntmNewMenuItem_4.setIcon(new ImageIcon(Main.class.getResource("/images/6590489_add_and_book_education_learning_icon.png")));
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  BookMangement bookMangement = new BookMangement();
                  bookMangement.setVisible(true);
                  table.add(bookMangement);
        		
        		
        	}
        });
        mnNewMenu_3.add(mntmNewMenuItem_4);
        
        JMenuItem mntmNewMenuItem_5 = new JMenuItem("安全退出");
        mntmNewMenuItem_5.setIcon(new ImageIcon(Main.class.getResource("/images/9165684_login_arrow_icon.png")));
        mntmNewMenuItem_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        mnNewMenu.add(mntmNewMenuItem_5);
        
        JMenu mnNewMenu_1 = new JMenu("关于我们");
        mnNewMenu_1.setForeground(new Color(0, 0, 0));
        mnNewMenu_1.setFont(new Font("宋体", Font.PLAIN, 20));
        menuBar.add(mnNewMenu_1);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("关于此图书管理系统系统");
        mntmNewMenuItem.setIcon(new ImageIcon(Main.class.getResource("/images/309035_user_account_human_person_icon.png")));
        mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Initinal initinal = new Initinal();
                initinal.setVisible(true);
                table.add(initinal); // 使用成员变量 table
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem);
        
        table = new JDesktopPane(); // 初始化成员变量 table
        getContentPane().add(table, BorderLayout.CENTER); // 修改为 CENTER
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置最大化
        this.setLocationRelativeTo(null); // 居中
    }
}

package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import model.Area;
import model.Student;
import javax.swing.ImageIcon;

public class StuMain extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Student student;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StuMain frame = new StuMain();
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
    public StuMain(Student student) {
        this.student = student;
        initialize();
    }

	
	  public StuMain() 
	  { initialize(); }
	
    private void initialize() {
        setTitle("图书使用系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 827, 664);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem mntmNewMenuItem = new JMenuItem("查询与借阅");
        mntmNewMenuItem.setIcon(new ImageIcon(StuMain.class.getResource("/images/4634455_article_content_interface_search_icon.png")));
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SAB sabFrame = new SAB(student);
                sabFrame.setVisible(true);
                contentPane.add(sabFrame);
                sabFrame.toFront();
            }
        });
        mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        menuBar.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("我的借阅");
        mntmNewMenuItem_1.setIcon(new ImageIcon(StuMain.class.getResource("/images/7351045_edit_user_profile_avatar_icon.png")));
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myrecord myRecordFrame = new myrecord(student);
                myRecordFrame.setVisible(true);
                contentPane.add(myRecordFrame);
                myRecordFrame.toFront();
            }
        });
        mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        menuBar.add(mntmNewMenuItem_1);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置最大化
    }
   
}

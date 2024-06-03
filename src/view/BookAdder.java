package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import dao.Areadao;
import dao.Bookdao;
import model.Area;
import model.Book;
import util.DbUtil;
import util.StrUtil;


public class BookAdder extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField nameTxt;
    private JTextField authorTxt;
    private JTextField sourcrTxt;
    private JComboBox<Area> categoryjcb;

    private DbUtil dbUtil = new DbUtil();
    private Areadao areadao = new Areadao();
    private Bookdao bookdao = new Bookdao();

    /**
     * 启动应用程序。
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookAdder frame = new BookAdder();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建框架。
     */
    public BookAdder() {
        setBackground(new Color(240, 240, 240));
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setTitle("图书添加");
        setBounds(100, 100, 772, 694);

        JLabel lblNewLabel = new JLabel("图书名称：");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));

        nameTxt = new JTextField();
        nameTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("作者：");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));

        authorTxt = new JTextField();
        authorTxt.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("馆藏资源：");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));

        sourcrTxt = new JTextField();
        sourcrTxt.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("图书类别：");
        lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
        lblNewLabel_3.setForeground(new Color(0, 0, 0));

        categoryjcb = new JComboBox<>();

        JButton btnNewButton = new JButton("添加");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookAddActionPerformed(e);
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(122)
        					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblNewLabel_2)
        						.addComponent(lblNewLabel_3)
        						.addComponent(lblNewLabel_1)
        						.addComponent(lblNewLabel))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(categoryjcb, 0, 216, Short.MAX_VALUE)
        						.addComponent(authorTxt)
        						.addComponent(nameTxt, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
        						.addComponent(sourcrTxt, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(289)
        					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(293, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(62)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel)
        				.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
        			.addGap(45)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_1))
        			.addGap(39)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_3)
        				.addComponent(categoryjcb, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
        			.addGap(47)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel_2)
        				.addComponent(sourcrTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
        			.addGap(86)
        			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(135, Short.MAX_VALUE))
        );
        getContentPane().setLayout(groupLayout);
        fillBookType();
    }

    protected void bookAddActionPerformed(ActionEvent evt) {
        String bookName = this.nameTxt.getText();
        String author = this.authorTxt.getText();
        String sourcr = this.sourcrTxt.getText();
        //String area = this.AreaTxt.getText();

        if (StrUtil.isEmpty(bookName)) {
            JOptionPane.showMessageDialog(null, "图书名称不能为空！");
            return;
        }

        if (StrUtil.isEmpty(author)) {
            JOptionPane.showMessageDialog(null, "作者不能为空！");
            return;
        }

        if (StrUtil.isEmpty(sourcr)) {
            JOptionPane.showMessageDialog(null, "馆藏资源不能为空！");
            return;
        }

        int sourcr1 = 0;
        try {
            sourcr1 = Integer.parseInt(sourcr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "馆藏资源必须是整数！");
            return;
        }

        Area selectedArea = (Area) categoryjcb.getSelectedItem();
        String category = selectedArea != null ? selectedArea.getCategory() : "";

        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        book.setSource(sourcr1);
        book.setCategory(category);
       // book.setArea(area);

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int addNum = bookdao.add1(con, book);
            if (addNum == 1) {
                JOptionPane.showMessageDialog(null, "图书添加成功！");
               // resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "图书添加失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "图书添加失败！");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fillBookType() {
        Connection con = null;
        Area area = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = areadao.list(con, new Area());
            while (rs.next()) {
                area = new Area();
                area.setId(rs.getInt("id"));
                area.setCategory(rs.getString("category"));
                area.setLocation(rs.getString("location"));
                this.categoryjcb.addItem(area);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
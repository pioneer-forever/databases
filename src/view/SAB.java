package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.Areadao;
import dao.Bookdao;
import model.Area;
import model.Book;
import model.Student;
import util.DbUtil;
import util.StrUtil;

public class SAB extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JComboBox<Area> searchcategoryjcb;
    private JComboBox<Area> categoryjcb;

    private DbUtil dbUtil = new DbUtil();
    private Areadao areadao = new Areadao();
    private Bookdao bookdao = new Bookdao();
    private int studentId;
	private Student student;

    public SAB(Student student) {
        this.student = student;
        initialize();
    }
    /**
     * @wbp.parser.constructor
     */
    public SAB(int studentId) {
    	setTitle("查询与借阅");
    	 this.studentId = studentId;
        initialize();
    }
    public void initialize() {
       
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 818, 757);

        JPanel panel = new JPanel();
        panel.setForeground(Color.BLACK);
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "搜索条件", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));

        JLabel lblNewLabel = new JLabel("图书名称：");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));

        textField = new JTextField();
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("图书类别：");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));

        searchcategoryjcb = new JComboBox<Area>();

        JLabel lblNewLabel_1 = new JLabel("图书作者：");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));

        JButton btnNewButton = new JButton("查询");
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchBooksActionPerformed(evt);
            }
        });

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGap(0, 712, Short.MAX_VALUE)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblNewLabel)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblNewLabel_2)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(searchcategoryjcb, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)))
                    .addGap(41)
                    .addComponent(lblNewLabel_1)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(21)
                            .addComponent(btnNewButton))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(36)
                            .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(76, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGap(0, 175, Short.MAX_VALUE)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(21)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_1))
                    .addGap(40)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_2)
                        .addComponent(searchcategoryjcb, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNewButton))
                    .addGap(19))
        );
        panel.setLayout(gl_panel);

        JScrollPane JScorllpane = new JScrollPane();

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JLabel lblNewLabel_3 = new JLabel("编号：");
        lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));

        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("图书名称：");
        lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));

        JButton btnNewButton_1 = new JButton("借阅");
        btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                borrowBookActionPerformed(evt);
            }
        });

        JLabel lblNewLabel_6 = new JLabel("馆藏资源:");
        lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));

        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("图书类别");
        lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));

        categoryjcb = new JComboBox<Area>();

        textField_4 = new JTextField();
        textField_4.setEditable(false);
        textField_4.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("图书作者:");
        lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));

        textField_5 = new JTextField();
        textField_5.setEditable(false);
        textField_5.setColumns(10);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 712, Short.MAX_VALUE)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(37)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addComponent(lblNewLabel_3)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblNewLabel_4))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblNewLabel_6)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
                    .addGap(12)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnNewButton_1)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addComponent(lblNewLabel_7)
                            .addGap(18)
                            .addComponent(categoryjcb, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(lblNewLabel_5)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(24, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGap(19)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblNewLabel_3)
                                .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNewLabel_5)
                                .addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNewLabel_4)
                                .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.RELATED, 103, Short.MAX_VALUE))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGap(91)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                .addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                .addComponent(lblNewLabel_6)
                                .addComponent(lblNewLabel_7)
                                .addComponent(categoryjcb, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                            .addGap(31)))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnNewButton_1)
                    .addGap(21))
        );
        panel_1.setLayout(gl_panel_1);
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(46)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
        				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
        				.addComponent(JScorllpane))
        			.addContainerGap(44, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(31)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(JScorllpane, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(63, Short.MAX_VALUE))
        );

        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u4F5C\u8005", "\u9986\u85CF\u8D44\u6E90", "\u56FE\u4E66\u7C7B\u522B", "\u533A\u57DF"
        	}
        ));
        table.setFont(new Font("宋体", Font.PLAIN, 13));
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                bookTableMousePressed(evt);
            }
        });
        JScorllpane.setViewportView(table);
        getContentPane().setLayout(groupLayout);

        this.fillBookType("search");
        this.fillBookType("modify");
        this.fillTable(new Book());
    }

    private void searchBooksActionPerformed(ActionEvent evt) {
        String bookName = textField.getText();
        String bookAuthor = textField_1.getText();
        Area selectedCategory = (Area) searchcategoryjcb.getSelectedItem();

        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(bookAuthor);
        if (selectedCategory != null && selectedCategory.getId() != -1) {
            book.setCategory(selectedCategory.getCategory());
        }

        fillTable(book);
    }

    private void fillTable(Book book) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0); // 清空现有行
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = bookdao.list1(con, book);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("author"));
                v.add(rs.getInt("source"));
                v.add(rs.getString("category"));
                v.add(rs.getString("location"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void bookTableMousePressed(MouseEvent evt) {
        int row = this.table.getSelectedRow();
        this.textField_2.setText((String) table.getValueAt(row, 0));
        this.textField_4.setText((String) table.getValueAt(row, 1));
        this.textField_5.setText((String) table.getValueAt(row, 2));
        this.textField_3.setText((Integer) table.getValueAt(row, 3) + "");
        String category = (String) this.table.getValueAt(row, 4);
        int n = this.categoryjcb.getItemCount();
        for (int i = 0; i < n; i++) {
            Area item = (Area) this.categoryjcb.getItemAt(i);
            if (item.getCategory().equals(category)) {
                this.categoryjcb.setSelectedIndex(i);
                break;
            }
        }
    }

    private void borrowBookActionPerformed(ActionEvent evt) {
        String id = this.textField_2.getText();
        if (StrUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要借阅的图书");
            return;
        }

        int source = Integer.parseInt(this.textField_3.getText());
        if (source <= 0) {
            JOptionPane.showMessageDialog(null, "该图书库存不足");
            return;
        }

        int n = JOptionPane.showConfirmDialog(null, "确认要借阅这本书吗？");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                // 开始事务
                con.setAutoCommit(false);

                Book book = new Book();
                book.setId(Integer.parseInt(id));
                book.setSource(source - 1);

                int updateNum = bookdao.update1(con, book, this.student.getId());
                if (updateNum == 1) {
                    con.commit(); // 提交事务
                    JOptionPane.showMessageDialog(null, "借阅成功");
                    this.fillTable(new Book());
                } else {
                    con.rollback(); // 回滚事务
                    JOptionPane.showMessageDialog(null, "借阅失败，超过最大借阅数量或其他原因");
                }
            } catch (Exception e) {
                try {
                    if (con != null) {
                        con.rollback(); // 回滚事务
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "借阅失败，发生异常");
            } finally {
                try {
                    if (con != null) {
                        con.setAutoCommit(true); // 恢复默认提交行为
                        dbUtil.closeCon(con);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void fillBookType(String type) {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            List<Area> areaList = areadao.list(con);
            if ("search".equals(type)) {
                this.searchcategoryjcb.removeAllItems();
                Area defaultArea = new Area();
                defaultArea.setCategory("请选择......");
                defaultArea.setId(-1);
                this.searchcategoryjcb.addItem(defaultArea);
                for (Area area : areaList) {
                    this.searchcategoryjcb.addItem(area);
                }
            } else if ("modify".equals(type)) {
                this.categoryjcb.removeAllItems();
                for (Area area : areaList) {
                    this.categoryjcb.addItem(area);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

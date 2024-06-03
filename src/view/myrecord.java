package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.Bookdao;
import dao.Recorddao;
import model.Area;
import model.Book;
import model.Student;
import util.DbUtil;
import util.StrUtil;

public class myrecord extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private DbUtil dbUtil = new DbUtil();
    private Bookdao bookdao = new Bookdao();
    private Recorddao recorddao = new Recorddao();
    private Student student;
    private JComboBox<Area> categoryjcb;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    myrecord frame = new myrecord(null); // 传入null或者你需要的Student对象
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
    public myrecord(Student student) {
    	setMaximizable(true);
    	setIconifiable(true);
    	setClosable(true);
        this.student = student;
        setTitle("我的借阅");
        setBounds(100, 100, 791, 637);

        JScrollPane JScorllpane = new JScrollPane();

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JLabel lblNewLabel_3 = new JLabel("编号：");
        lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));

        textField = new JTextField();
        textField.setEditable(false);
        textField.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("图书名称：");
        lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));

        JButton btnNewButton_1 = new JButton("归还");
        btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                returnBookActionPerformed(evt);
            }
        });

        JLabel lblNewLabel_6 = new JLabel("馆藏资源:");
        lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));

        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("图书类别");
        lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));

        categoryjcb = new JComboBox<Area>();

        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("图书作者:");
        lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));

        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGap(0, 712, Short.MAX_VALUE)
                .addGap(0, 712, Short.MAX_VALUE)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(37)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addComponent(lblNewLabel_3)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblNewLabel_4))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblNewLabel_6)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
                    .addGap(12)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnNewButton_1)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addComponent(lblNewLabel_7)
                            .addGap(18)
                            .addComponent(categoryjcb, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                            .addGap(18)
                            .addComponent(lblNewLabel_5)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(24, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.TRAILING)
                .addGap(0, 231, Short.MAX_VALUE)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGap(19)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblNewLabel_3)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNewLabel_5)
                                .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNewLabel_4)
                                .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(ComponentPlacement.RELATED, 103, Short.MAX_VALUE))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGap(91)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                .addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
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
                    .addGap(24)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 712, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                    .addContainerGap(36, Short.MAX_VALUE)
                    .addComponent(JScorllpane, GroupLayout.PREFERRED_SIZE, 712, GroupLayout.PREFERRED_SIZE)
                    .addGap(27))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(54)
                    .addComponent(JScorllpane, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                    .addGap(46)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(85, Short.MAX_VALUE))
        );

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "编号", "图书名称", "作者", "馆藏资源", "类别" }
        ));
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                bookTableMousePressed(evt);
            }
        });
        JScorllpane.setViewportView(table);
        getContentPane().setLayout(groupLayout);

        this.fillTable();
    }

    private void fillTable() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0); // 清空现有行
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = recorddao.list(con, student.getId());
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("bookid"));
                v.add(rs.getString("name"));
                v.add(rs.getString("author"));
                v.add(rs.getInt("source"));
                v.add(rs.getString("category"));
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
        this.textField.setText((String) table.getValueAt(row, 0));
        this.textField_2.setText((String) table.getValueAt(row, 1));
        this.textField_3.setText((String) table.getValueAt(row, 2));
        this.textField_1.setText((Integer) table.getValueAt(row, 3) + "");
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

    private void returnBookActionPerformed(ActionEvent evt) {
        String id = this.textField.getText();
        if (StrUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要归还的图书");
            return;
        }

        int source = Integer.parseInt(this.textField_1.getText());
        int n = JOptionPane.showConfirmDialog(null, "确认要归还这本书吗？");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                con.setAutoCommit(false); // 开始事务

                Book book = new Book();
                book.setId(Integer.parseInt(id));
                book.setSource(source + 1);

                // 更新书籍馆藏资源
                int updateNum = bookdao.update2(con, book);

                if (updateNum == 1) {
                    // 更新学生借阅数量
                    String sql = "UPDATE student SET borrow = borrow - 1 WHERE id = ? AND borrow > 0";
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, student.getId());
                    int studentUpdateNum = pstmt.executeUpdate();

                    // 删除记录表中的借阅记录
                    String deleteSql = "DELETE FROM record WHERE stuid = ? AND bookid = ?";
                    PreparedStatement deletePstmt = con.prepareStatement(deleteSql);
                    deletePstmt.setInt(1, student.getId());
                    deletePstmt.setInt(2, book.getId());
                    int deleteNum = deletePstmt.executeUpdate();

                    if (studentUpdateNum == 1 && deleteNum == 1) {
                        con.commit(); // 提交事务
                        JOptionPane.showMessageDialog(null, "归还成功");
                        this.fillTable();
                    } else {
                        con.rollback(); // 回滚事务
                        JOptionPane.showMessageDialog(null, "归还失败");
                    }
                } else {
                    con.rollback(); // 回滚事务
                    JOptionPane.showMessageDialog(null, "归还失败");
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
                JOptionPane.showMessageDialog(null, "归还失败，发生异常");
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

    
    }

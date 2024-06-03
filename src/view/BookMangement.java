package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
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
import util.DbUtil;
import util.StrUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookMangement extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTable booktable;
    private JTextField searchbooknameTxt;
    private JTextField searchbookauthorTxt;
    private JComboBox<Area> searchcategoryjcb;
    private JScrollPane JScorllpane;
    private JComboBox<Area> categoryjcb = new JComboBox<>();

    private DbUtil dbUtil = new DbUtil();
    private Areadao areadao = new Areadao();
    private Bookdao bookdao = new Bookdao();
    private JTextField idTxt;
    private JTextField nameTxt;
    private JTextField authorTxt;
    private JTextField sourceTxt;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookMangement frame = new BookMangement();
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
    public BookMangement() {
        setIconifiable(true);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setTitle("图书管理");
        setBounds(100, 100, 822, 747);

        JScorllpane = new JScrollPane();

        JPanel panel = new JPanel();
        panel.setForeground(new Color(0, 0, 0));
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "搜索条件", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "表单操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap(52, Short.MAX_VALUE)
                    .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(JScorllpane, Alignment.LEADING)
                        .addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(56))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(30)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(JScorllpane, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                    .addGap(22)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(62, Short.MAX_VALUE))
        );
        
        JLabel lblNewLabel_3 = new JLabel("编号：");
        lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
        
        idTxt = new JTextField();
        idTxt.setEditable(false);
        idTxt.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("图书名称：");
        lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
        
        nameTxt = new JTextField();
        nameTxt.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("图书作者:");
        lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
        
        authorTxt = new JTextField();
        authorTxt.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("馆藏资源:");
        lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 20));
        
        sourceTxt = new JTextField();
        sourceTxt.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("图书类别");
        lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 20));
        
        JButton btnNewButton_1 = new JButton("修改");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bookUpdateActionPerformed(evt);
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
        
        JButton btnNewButton_2 = new JButton("删除");
        btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bookDeleteActionPerformed(evt);
            }
        });
        
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(37)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addComponent(lblNewLabel_3)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblNewLabel_4))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGap(84)
                            .addComponent(btnNewButton_1))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblNewLabel_6)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(sourceTxt, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
                    .addGap(8)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGap(140)
                            .addComponent(btnNewButton_2))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGap(4)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_panel_1.createSequentialGroup()
                                    .addComponent(lblNewLabel_7)
                                    .addGap(18)
                                    .addComponent(categoryjcb, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_panel_1.createSequentialGroup()
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18)
                                    .addComponent(lblNewLabel_5)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(24, Short.MAX_VALUE))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(19)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_3)
                        .addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_5)
                        .addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_4)
                        .addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnNewButton_1)
                        .addComponent(btnNewButton_2))
                    .addGap(18))
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(91)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(sourceTxt, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addComponent(lblNewLabel_6)
                        .addComponent(lblNewLabel_7)
                        .addComponent(categoryjcb, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                    .addGap(85))
        );
        panel_1.setLayout(gl_panel_1);

        JLabel lblNewLabel = new JLabel("图书名称：");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));

        searchbooknameTxt = new JTextField();
        searchbooknameTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("图书作者：");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));

        searchbookauthorTxt = new JTextField();
        searchbookauthorTxt.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("图书类别：");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));

        searchcategoryjcb = new JComboBox<>();

        JButton btnNewButton = new JButton("查询");
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(lblNewLabel)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(searchbooknameTxt, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(searchbookauthorTxt, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(62, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(21)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(searchbooknameTxt, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchbookauthorTxt, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_1))
                    .addGap(40)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel_2)
                        .addComponent(searchcategoryjcb, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNewButton))
                    .addGap(19))
        );
        panel.setLayout(gl_panel);

        booktable = new JTable();
        booktable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent met) {
                bookTableMousePressed(met);
            }
        });
        booktable.setFont(new Font("宋体", Font.PLAIN, 13));
        booktable.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"编号", "图书名称", "图书作者", "馆藏资源", "图书类别"}
        ));
        JScorllpane.setViewportView(booktable);
        getContentPane().setLayout(groupLayout);
        
        this.fillBookType("search");
        this.fillBookType("modify");
        this.fillTable(new Book());

        btnNewButton.addActionListener(e -> searchBooks());
    }

    private void bookUpdateActionPerformed(ActionEvent evt) {
        String id = this.idTxt.getText();
        if (StrUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要修改的字段");
            return;
        }

        String name = this.nameTxt.getText();
        String author = this.authorTxt.getText();
        String source = this.sourceTxt.getText();

        if (StrUtil.isEmpty(name)) {
            JOptionPane.showMessageDialog(null, "图书名称不能为空");
            return;
        }

        if (StrUtil.isEmpty(author)) {
            JOptionPane.showMessageDialog(null, "图书作者不能为空");
            return;
        }

        if (StrUtil.isEmpty(source)) {
            JOptionPane.showMessageDialog(null, "馆藏资源不能为空");
            return;
        }

        Area area = (Area) categoryjcb.getSelectedItem();
        String category = area.getCategory();

        Book book = new Book(Integer.parseInt(id), name, author, Integer.parseInt(source), category);

        Connection con = null;
        try {
            con = dbUtil.getCon();
            int updateNum = bookdao.update(con, book);
            if (updateNum == 1) {
                JOptionPane.showMessageDialog(null, "图书修改成功");
                resetValue();
                this.fillTable(new Book());
            } else {
                JOptionPane.showMessageDialog(null, "图书修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "图书修改失败");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void bookDeleteActionPerformed(ActionEvent evt) {
        String id = idTxt.getText();
        if (StrUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "请选择要删除的记录");
            return;
        }
        int n = JOptionPane.showConfirmDialog(null, "确定要删除这条记录吗？");
        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                int deleteNum = bookdao.delete(con, id);
                if (deleteNum == 1) {
                    JOptionPane.showMessageDialog(null, "删除成功");
                    resetValue();
                    this.fillTable(new Book());
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "删除失败");
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 重置表单字段
     */
    private void resetValue() {
        this.idTxt.setText("");
        this.nameTxt.setText("");
        this.authorTxt.setText("");
        this.sourceTxt.setText("");
        if (this.categoryjcb.getItemCount() > 0) {
            this.categoryjcb.setSelectedIndex(0);
        }
    }

    private void bookTableMousePressed(MouseEvent met) {
        int row = this.booktable.getSelectedRow();
        this.idTxt.setText((String) booktable.getValueAt(row, 0));
        this.nameTxt.setText((String) booktable.getValueAt(row, 1));
        this.authorTxt.setText((String) booktable.getValueAt(row, 2));
        this.sourceTxt.setText((Integer) booktable.getValueAt(row, 3) + "");
        String category = (String) this.booktable.getValueAt(row, 4);
        int n = this.categoryjcb.getItemCount();
        for (int i = 0; i < n; i++) {
            Area item = (Area) this.categoryjcb.getItemAt(i);
            if (item.getCategory().equals(category)) {
                this.categoryjcb.setSelectedIndex(i);
                break;  // 找到匹配的项目后，立即退出循环
            }
        }
    }

    private void fillBookType(String type) {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            List<Area> areaList = areadao.list(con);
            if ("search".equals(type)) {
                this.searchcategoryjcb.removeAllItems(); // 清空现有项目
                Area defaultArea = new Area();
                defaultArea.setCategory("请选择......");
                defaultArea.setId(-1);
                this.searchcategoryjcb.addItem(defaultArea);
                for (Area area : areaList) {
                    this.searchcategoryjcb.addItem(area);
                }
            } else if ("modify".equals(type)) {
                this.categoryjcb.removeAllItems(); // 清空现有项目
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

    private void searchBooks() {
        String bookName = searchbooknameTxt.getText();
        String bookAuthor = searchbookauthorTxt.getText();
        Area selectedCategory = (Area) searchcategoryjcb.getSelectedItem();

        model.Book book = new model.Book();
        book.setName(bookName);
        book.setAuthor(bookAuthor);
        if (selectedCategory != null && selectedCategory.getId() != -1) {
            book.setCategory(selectedCategory.getCategory());
        }

        fillTable(book);
    }

    private void fillTable(model.Book book) {
        DefaultTableModel dtm = (DefaultTableModel) booktable.getModel();
        dtm.setRowCount(0); // 清空现有行
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = bookdao.list(con, book);
            while (rs.next()) {
                Vector<Object> v = new Vector<>();
                v.add(rs.getString("id"));
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
}

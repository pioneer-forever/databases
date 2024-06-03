package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.Areadao;
import util.DbUtil;
import util.StrUtil;
import java.awt.Font;
import java.awt.Color;
import java.awt.Checkbox;

public class AreaAdder extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField categoryTxt;
    private JTextArea locationTxt;
    private DbUtil dbUtil = new DbUtil();
    private Areadao areadao = new Areadao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AreaAdder frame = new AreaAdder();
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
    public AreaAdder() {
    	setIconifiable(true);
    	setClosable(true);
    	setResizable(true);
    	setMaximizable(true);
    	setToolTipText("");
        initialize();
    }

    public AreaAdder(String category, String location) {
        this();
        this.categoryTxt.setText(category);
        this.locationTxt.setText(location);
    }

    private void initialize() {
        setBackground(new Color(240, 240, 240));
        setTitle("图书类别索引");
        setBounds(100, 100, 778, 613);

        JLabel lblNewLabel = new JLabel("图书类别：");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));

        JLabel lblNewLabel_1 = new JLabel("所在区域：");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));

        categoryTxt = new JTextField();
        categoryTxt.setColumns(10);

        locationTxt = new JTextArea();
        locationTxt.setRows(4);
        locationTxt.setColumns(20);

        JButton btnNewButton = new JButton("添加");
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookcategoryAddActionPerformed(e);
            }
        });

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap(188, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewLabel)
        						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
        					.addGap(47)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(categoryTxt, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
        						.addComponent(locationTxt, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE))
        					.addGap(88))
        				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        					.addGap(334))))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap(128, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel)
        				.addComponent(categoryTxt, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(84)
        					.addComponent(locationTxt, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(92)
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
        			.addGap(101)
        			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addGap(117))
        );
        getContentPane().setLayout(groupLayout);
    }

    private void bookcategoryAddActionPerformed(ActionEvent evt) {
        String category = this.categoryTxt.getText();
        String location = this.locationTxt.getText();
        if (StrUtil.isEmpty(category)) {
            JOptionPane.showMessageDialog(null, "图书类别名称不能为空");
            return;
        }
        model.Area area = new model.Area(category, location);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int n = areadao.add(con, area);
            if (n == 1) {
                JOptionPane.showMessageDialog(null, "添加成功");
                resetValue();
            } else {
                JOptionPane.showMessageDialog(null, "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "添加失败");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void resetValue() {
        this.categoryTxt.setText("");
        this.locationTxt.setText("");
    }
}

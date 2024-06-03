package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Areadao;
import model.Area;
import util.DbUtil;
import util.StrUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AreaMangement extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTable areaTable;
    private DbUtil dbUtil = new DbUtil();
    private Areadao areadao = new Areadao();
    private JTextField cate_searchTxt;
    private JTextField idTxt;
    private JTextField categoryTxt;
    private JTextArea areaTxt; // 将 areaTxt 声明为类成员变量

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AreaMangement frame = new AreaMangement();
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
    public AreaMangement() {
        setTitle("图书类别管理");
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 754, 675);
        
        JScrollPane scrollPane = new JScrollPane();
        
        JLabel lblNewLabel = new JLabel("请输入图书类别：");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        
        cate_searchTxt = new JTextField();
        cate_searchTxt.setColumns(10);
        
        JButton btnNewButton = new JButton("查询");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aeraSearchActionPerformed(e);
        	}
        });
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "修改删除", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setToolTipText("");
        panel.setBackground(new Color(240, 240, 240));
        panel.setForeground(new Color(0, 0, 0));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(116, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 517, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(lblNewLabel)
        					.addGap(18)
        					.addComponent(cate_searchTxt, 158, 158, 158)
        					.addGap(30)
        					.addComponent(btnNewButton)))
        			.addGap(105))
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addGap(71)
        			.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(44))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(40)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cate_searchTxt, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNewButton)
        				.addComponent(lblNewLabel))
        			.addGap(43)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
        			.addGap(32)
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(96, Short.MAX_VALUE))
        );
        
        JLabel lblNewLabel_1 = new JLabel("编号：");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
        
        idTxt = new JTextField();
        idTxt.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("图书类别：");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
        
        categoryTxt = new JTextField();
        categoryTxt.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("馆藏区域：");
        lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
        
        areaTxt = new JTextArea(); // 初始化 JTextArea
        areaTxt.setRows(5); // 设置 JTextArea 行数
        areaTxt.setColumns(20); // 设置 JTextArea 列数
        
        JButton btnNewButton_1 = new JButton("修改");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 修改按钮的逻辑
        		AreaUpdateActionEvent(e);
        		
        		
        	}
        });
        btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
        
        JButton btnNewButton_2 = new JButton("删除");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            		AreaDeleteActionEvent(e);
        	}
        });
        btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(18)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel.createSequentialGroup()
        							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
        							.addGap(51)
        							.addComponent(lblNewLabel_2)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(categoryTxt, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
        						.addGroup(gl_panel.createSequentialGroup()
        							.addComponent(lblNewLabel_3)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(areaTxt, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)))
        					.addContainerGap(58, Short.MAX_VALUE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(66)
        					.addComponent(btnNewButton_1)
        					.addPreferredGap(ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
        					.addComponent(btnNewButton_2)
        					.addGap(71))))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGap(23)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(categoryTxt, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
        				.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel_2)
        				.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(37)
        					.addComponent(lblNewLabel_3))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(27)
        					.addComponent(areaTxt, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnNewButton_1)
        				.addComponent(btnNewButton_2))
        			.addGap(20))
        );
        panel.setLayout(gl_panel);
        
        areaTable = new JTable();
        areaTable.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
				areaTableMousePressed(e);
			}
        });
        scrollPane.setViewportView(areaTable);
        areaTable.setColumnSelectionAllowed(true);
        areaTable.setCellSelectionEnabled(true);
        areaTable.setFillsViewportHeight(true);
        areaTable.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "编号", "图书类别", "所在区域"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                true, true, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        getContentPane().setLayout(groupLayout);
        areaTxt.setBorder(new LineBorder(new java.awt.Color(127,157,185), 1, false));
        
        this.fillTable(new Area());
    }

    private void AreaDeleteActionEvent(ActionEvent evt) {
		// TODO 自动生成的方法存根
    	String id=idTxt.getText();
		if(StrUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "请选择要删除的编号");
			return;
		}
		int n=JOptionPane.showConfirmDialog(null, "确定要删除这部分内容吗");
		if(n==0){
			Connection con=null;
			try{
				con=dbUtil.getCon();
				int deleteNum=areadao.delete(con, id);
				if(deleteNum==1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new Area());
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void AreaUpdateActionEvent(ActionEvent evt) {
		// TODO 自动生成的方法存根
    	String id=idTxt.getText();
		String category=categoryTxt.getText();
		String location=areaTxt.getText();
		if(StrUtil.isEmpty(id)){
			JOptionPane.showMessageDialog(null, "请选择要修改的编号");
			return;
		}
		if(StrUtil.isEmpty(category)){
			JOptionPane.showMessageDialog(null, "请选择要修改的类别");
			return;
		}
		Area area=new Area(Integer.parseInt(id),category,location);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int modifyNum = areadao.update(con, area);
			//int modifyNum=Areadao.update(con, area);
			if(modifyNum==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new Area());
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void resetValue() {
		// TODO 自动生成的方法存根
		this.idTxt.setText("");
		this.categoryTxt.setText("");
		this.areaTxt.setText("");
	}

	/**search
     * 
     * @param e
     */
    private void aeraSearchActionPerformed(ActionEvent e) {
    	String cate_search = this.cate_searchTxt.getText();
		Area area = new Area();
		area.setCategory(cate_search); // 修改为 set 方法
		this.fillTable(area);
	}
    
    /**
     * 初始化表格
     * @param area
     */
    private void fillTable(Area area) {
        DefaultTableModel dtm = (DefaultTableModel) areaTable.getModel();
        dtm.setRowCount(0); // 设置为0行
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = areadao.list(con, area);
            while (rs.next()) {
                Vector<Object> v = new Vector<Object>();
                v.add(rs.getString("id"));
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
        areaTable.repaint();
    }
    
    /**
     * 鼠标点击事件
     */
    private void areaTableMousePressed(MouseEvent evt) {
    	int row = areaTable.getSelectedRow();
		idTxt.setText((String) areaTable.getValueAt(row, 0));
		categoryTxt.setText((String) areaTable.getValueAt(row, 1));
		areaTxt.setText((String) areaTable.getValueAt(row, 2));
	}
}

package com.xnx3.writecode.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.xnx3.swing.DialogUtil;
import com.xnx3.writecode.DataSource;
import com.xnx3.writecode.bean.FieldBean;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.interfaces.SelectTableInterface;

import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 选择数据表
 * @author 管雷鸣
 *
 */
public class SelectTableJframe extends JFrame {
	public DataSource dataSource;	//数据源
	private JPanel contentPane;
	public JTable table;
	public SelectTableInterface selectTable;


	/**
	 * Create the frame.
	 */
	public SelectTableJframe(DataSource dataSource) {
		this.dataSource = dataSource;
		
		setTitle("writecode 自动写代码");
		setBounds(100, 100, 579, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("确  定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < table.getRowCount(); i++) {
					Boolean selected = (Boolean) table.getValueAt(i, 0);
					if(selected) {
						String tableName = (String) table.getValueAt(i, 1);
						list.add(tableName);
					}
				}
				System.out.println(list);
				selectTable.selectFinish(list);
				disable();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(141)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addGap(125))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println(table.getSelectedRow()+","+table.getSelectedColumn());
				int currentRow = table.getSelectedRow();
				Object s = table.getValueAt(currentRow, 1);
				System.out.println(s.toString());
				//选的是哪个数据表，数据表的命名
				String tableName = s.toString();
				
				if(table.getSelectedColumn() == 2) {
					//列表页设置
					//DialogUtil.showMessageDialog("完善中...");
					
					//UI相关
					ListJframe listJframe = new ListJframe(dataSource);
					listJframe.setVisible(true);
					DefaultTableModel tableModel=(DefaultTableModel) listJframe.table.getModel();
					tableModel.getDataVector().clear();		//清空所有
					
					
				}else if(table.getSelectedColumn() == 3) {
					//编辑页设置
					//DialogUtil.showMessageDialog("完善中...");
					
					//UI相关
					EditJframe editJframe = new EditJframe(dataSource, tableName);
					editJframe.setVisible(true);
					editJframe.tableFill(new HashMap<String, Boolean>());
					
					
				}
				
				
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"\u9009\u62E9", "\u6570\u636E\u8868\u540D\u5B57", "\u5217\u8868\u9875\u8BBE\u7F6E", "\u7F16\u8F91\u9875\u8BBE\u7F6E", "\u6570\u636E\u8868\u5907\u6CE8"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class, Object.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(400);
		table.getColumnModel().getColumn(4).setPreferredWidth(300);
		table.getColumnModel().getColumn(4).setMaxWidth(500);
		
	}
	

	/**
	 * 不显示窗体
	 */
	public void disable() {
		this.setVisible(false);
	}
}

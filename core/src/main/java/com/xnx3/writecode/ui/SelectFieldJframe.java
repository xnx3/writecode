package com.xnx3.writecode.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.xnx3.writecode.util.JTableUtil;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

/**
 * 选择字段
 * @author 管雷鸣
 *
 */
public class SelectFieldJframe extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JButton btnNewButton;
	public SelectFieldJframeInterface selectFieldJframeInterface;


	/**
	 * Create the frame.
	 */
	public SelectFieldJframe(SelectFieldJframeInterface selectFieldJframeInterface) {
		this.selectFieldJframeInterface = selectFieldJframeInterface;
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnNewButton = new JButton("完  成");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String, Boolean> map = new HashMap<String, Boolean>();
				Map<String, Object[]> tableMap = JTableUtil.getTableData(table, 1);
				for (Map.Entry<String, Object[]> entry : tableMap.entrySet()) {
					Object[] objs = entry.getValue();
					if(objs != null && objs[0] != null) {
						map.put(entry.getKey(), (Boolean)objs[0]);
						System.out.println(entry.getKey()+ "= " + (Boolean)objs[0]);
					}
				}
				
				selectFieldJframeInterface.selectFinishCallback(map);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(96)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"\u4F7F\u7528", "\u5B57\u6BB5", "\u8BF4\u660E"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
	}
}

/**
 * 选择完成的回调
 * @author 管雷鸣
 *
 */
interface SelectFieldJframeInterface{
	
	/**
	 * 选择完成的回调
	 * @param map key:字段名， value:是否选中，true是选中，false是未选中
	 */
	void selectFinishCallback(Map<String, Boolean> map);
}
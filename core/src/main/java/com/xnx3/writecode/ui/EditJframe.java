package com.xnx3.writecode.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.xnx3.swing.DialogUtil;
import com.xnx3.writecode.DataSource;
import com.xnx3.writecode.bean.FieldBean;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.util.JTableUtil;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 编辑选项，增加、编辑时要操作哪些字段的设置
 * @author 管雷鸣
 *
 */
public class EditJframe extends JFrame {
	private JPanel contentPane;
	public JTable table;
	public String tableName;	//当前所操作的数据表的名字

	/**
	 * @param tableName 当前所操作的数据表的名字
	 */
	public EditJframe(String tableName) {
		setTitle("用户编辑信息时，form表单所提交的字段");
		this.tableName = tableName;
		
		setBounds(100, 100, 450, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("调整字段");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectField(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("保存设置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
				if(tableBean == null) {
					DialogUtil.showMessageDialog("异常，table:"+tableName+", 的数据信息 tableBean 为 null");
					return;
				}
				
				//当前table中所有的filed
				List<FieldBean> fieldList = tableBean.getFieldList();
				
				//当前有的字段
				Map<String, Boolean> currentFieldMap = new HashMap<String, Boolean>();
				Map<String, Object[]> tableMap = JTableUtil.getTableData(table, 0);
				for (Map.Entry<String, Object[]> entry : tableMap.entrySet()) {
					String key = entry.getKey();
					if(key == null || key.trim().length() < 1) {
						continue;
					}
					currentFieldMap.put(key, true);
				}
				
				//过滤出选中的来
				List<FieldBean> selectedFieldList = new ArrayList<FieldBean>();
				for (int i = 0; i < fieldList.size(); i++) {
					FieldBean fieldBean = fieldList.get(i);
					if(currentFieldMap.get(fieldBean.getName()) != null) {
						selectedFieldList.add(fieldBean);
					}
				}
				tableBean.setFieldEditList(selectedFieldList);
				
				MainJframe.tableBeanMap.put(tableName, tableBean);
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(275, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\u70B9\u51FB\u53F3\u4FA7\u6309\u94AE\u6DFB\u52A0", ""},
			},
			new String[] {
				"\u5B57\u6BB5\u540D\u5B57", "\u8BF4\u660E"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * 传入table 表的名字，进行填充数据
	 * @param selectMap map.key 数据表的字段名，value如果是false，那这个字段则不在这里显示 
	 */
	public void tableFill(Map<String, Boolean> selectMap) {
		DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
		tableModel.getDataVector().clear();		//清空所有
		
		//选的是哪个字段，字段的命名
		
//		String fieldName = table.getValueAt(currentRow, 1).toString();
		
		//查出这个表中有多少字段
		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
		
		for (int i = 0; i < tableBean.getFieldList().size(); i++) {
			FieldBean fieldBean = tableBean.getFieldList().get(i);
			if(selectMap.get(fieldBean.getName()) != null && selectMap.get(fieldBean.getName()).toString().equalsIgnoreCase("false")) {
				continue;
			}
			
			Vector rowData = new Vector();
			rowData.add(fieldBean.getName());
			//rowData.add("");
			rowData.add(fieldBean.getComment());
			tableModel.insertRow(tableModel.getRowCount(), rowData);
		}
	}
	
	/**
	 * 选择、调整字段
	 */
	public void selectField(ActionEvent e) {
		int x = this.getX() + 30;
		int y = this.getY() + 30;
		
		//当前table显示的
		Map<String, Object[]> tableMap = JTableUtil.getTableData(table, 0);
		
		//查出这个表中有多少字段
		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
		
		//绘制UI
		SelectFieldJframe fieldJframe = new SelectFieldJframe(new SelectFieldJframeInterface() {
			public void selectFinishCallback(Map<String, Boolean> map) {
				//点确定后的回调,重新绘制数据
				tableFill(map);
			}
		});
		fieldJframe.setBounds(x, y, 350, 520);
		fieldJframe.setVisible(true);
		
		DefaultTableModel tableModel=(DefaultTableModel) fieldJframe.table.getModel();
		tableModel.getDataVector().clear();		//清空所有
		
		
		for (int i = 0; i < tableBean.getFieldList().size(); i++) {
			FieldBean fieldBean = tableBean.getFieldList().get(i);
			
			Vector rowData = new Vector();
			rowData.add(tableMap.get(fieldBean.getName()) != null);
			rowData.add(fieldBean.getName());
			rowData.add(fieldBean.getComment());
			tableModel.insertRow(i, rowData);
		}
		
		
	}
	
	
}

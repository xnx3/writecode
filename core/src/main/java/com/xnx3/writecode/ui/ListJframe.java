package com.xnx3.writecode.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.xnx3.writecode.DataSource;
import com.xnx3.writecode.bean.FieldBean;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.util.JTableUtil;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 列表页面的可视化设置
 * @author 管雷鸣
 *
 */
public class ListJframe extends JFrame {
//	public DataSource dataSource;	//数据源
	private JPanel contentPane;
	public JTable table;
	public String tableName;	//当前所操作的数据表的名字
	private JLabel searchFieldLabel;


	/**
	 * Create the frame.
	 */
	public ListJframe(String tableName) {
//		this.dataSource = dataSource;
		this.tableName = tableName;
		setBounds(100, 100, 842, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u641C\u7D22\u5B57\u6BB5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "\u6570\u636E\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel = new JLabel("完善中。。。还不能用");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addGap(73)
					.addComponent(lblNewLabel)
					.addContainerGap(112, Short.MAX_VALUE))
		);
		
		JButton btnNewButton = new JButton("添加搜索字段");
		
		searchFieldLabel = new JLabel("New label");
		searchFieldLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectSearchField();
			}
		});
		searchFieldLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchFieldLabel, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addGap(40))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(searchFieldLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		searchFieldFill();
	}
	

	/**
	 * 传入table 表的名字，进行填充数据
	 * @param tableName table表的名字，如 user_role
	 * @param selectMap map.key 数据表的字段名，value如果是false，那这个字段则不在这里显示 
	 */
//	public void tableFill(Map<String, Boolean> selectMap) {
//		DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
//		tableModel.getDataVector().clear();		//清空所有
//		
//		//查出这个表中有多少字段
//		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
//		
//		for (int i = 0; i < tableBean.getFieldList().size(); i++) {
//			FieldBean fieldBean = tableBean.getFieldList().get(i);
//			if(selectMap.get(fieldBean.getName()) != null && selectMap.get(fieldBean.getName()).toString().equalsIgnoreCase("false")) {
//				continue;
//			}
//			
//			Vector rowData = new Vector();
//			rowData.add(fieldBean.getName());
//			//rowData.add("");
//			rowData.add(fieldBean.getComment());
//			tableModel.insertRow(tableModel.getRowCount(), rowData);
//		}
//	}
	
	/**
	 * 选择、调整字段
	 */
//	public void selectField(ActionEvent e) {
//		//当前table显示的
//		Map<String, Object[]> tableMap = JTableUtil.getTableData(table, 0);
//		
//		//查出这个表中有多少字段
//		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
//		
//		//绘制UI
//		SelectFieldJframe fieldJframe = new SelectFieldJframe(new SelectFieldJframeInterface() {
//			public void selectFinishCallback(Map<String, Boolean> map) {
//				//点确定后的回调,重新绘制数据
//				tableFill(map);
//			}
//		});
//		fieldJframe.setVisible(true);
//		
//		DefaultTableModel tableModel=(DefaultTableModel) fieldJframe.table.getModel();
//		tableModel.getDataVector().clear();		//清空所有
//		
//		
//		for (int i = 0; i < tableBean.getFieldList().size(); i++) {
//			FieldBean fieldBean = tableBean.getFieldList().get(i);
//			
//			Vector rowData = new Vector();
//			rowData.add(tableMap.get(fieldBean.getName()) != null);
//			rowData.add(fieldBean.getName());
//			rowData.add(fieldBean.getComment());
//			tableModel.insertRow(i, rowData);
//		}
//		
//		
//	}
	
	/**
	 * 绘制填充搜索field的数据
	 */
	public void searchFieldFill() {
		//查出这个表中有多少字段
		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
		
		List<FieldBean> searchFieldList = tableBean.getFieldListSearchList();
		StringBuffer sb = new StringBuffer();
		sb.append("");
		for (int i = 0; i < searchFieldList.size(); i++) {
			if(sb.length() > 0) {
				sb.append(",");
			}
			sb.append(searchFieldList.get(i).getName());
		}
		getSearchFieldLabel().setText("<html>"+sb.toString());
	}
	

	/**
	 * 选择、调整搜索的字段
	 */
	public void selectSearchField() {
		//查出这个表当前的信息
		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
		
		//当前显示的,选中的
		List<FieldBean> currentSelectedFieldList = tableBean.getFieldListSearchList();
		//转为map形式. key:field.name
		Map<String, FieldBean> currentSelectedFieldMap = new HashMap<String, FieldBean>();
		for (int i = 0; i < currentSelectedFieldList.size(); i++) {
			FieldBean field = currentSelectedFieldList.get(i);
			currentSelectedFieldMap.put(field.getName(), field);
		}
		
		//绘制UI
		SelectFieldJframe fieldJframe = new SelectFieldJframe(new SelectFieldJframeInterface() {
			public void selectFinishCallback(Map<String, Boolean> map) {
				//点确定后的回调,保存缓存数据
				
				//重新组合选择的field
				List<FieldBean> currentSelectedFieldList = new ArrayList<FieldBean>();
				for (int i = 0; i < tableBean.getFieldList().size(); i++) {
					FieldBean fieldBean = tableBean.getFieldList().get(i);
					if(map.get(fieldBean.getName()) != null && map.get(fieldBean.getName()).toString().equalsIgnoreCase("false")) {
						continue;
					}
					currentSelectedFieldList.add(fieldBean);
				}
				tableBean.setFieldListSearchList(currentSelectedFieldList);
				MainJframe.tableBeanMap.put(tableName, tableBean);
				
				//重新加载数据
				searchFieldFill();
			}
		});
		fieldJframe.setVisible(true);
		
		DefaultTableModel tableModel=(DefaultTableModel) fieldJframe.table.getModel();
		tableModel.getDataVector().clear();		//清空所有
		
		for (int i = 0; i < tableBean.getFieldList().size(); i++) {
			FieldBean fieldBean = tableBean.getFieldList().get(i);
			
			Vector rowData = new Vector();
			rowData.add(currentSelectedFieldMap.get(fieldBean.getName()) != null);
			rowData.add(fieldBean.getName());
			rowData.add(fieldBean.getComment());
			tableModel.insertRow(i, rowData);
		}
	}
	
	
	public JLabel getSearchFieldLabel() {
		return searchFieldLabel;
	}
}

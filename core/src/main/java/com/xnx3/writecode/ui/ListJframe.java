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
import java.awt.event.ActionListener;

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
	private JButton addSearchFieldButton;
	private JButton addSearchFieldButton_1;


	/**
	 * Create the frame.
	 */
	public ListJframe(String tableName) {
		setTitle("list数据列表分页的页面相关设置");
//		this.dataSource = dataSource;
		this.tableName = tableName;
		setBounds(100, 100, 802, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u641C\u7D22\u5B57\u6BB5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "\u6570\u636E\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		addSearchFieldButton = new JButton("编辑");
		addSearchFieldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectSearchField();
			}
		});
		
		addSearchFieldButton_1 = new JButton("编辑");
		addSearchFieldButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectTableField();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(addSearchFieldButton, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addComponent(addSearchFieldButton_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(addSearchFieldButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(addSearchFieldButton_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(191, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))))
		);
		
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
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchFieldLabel, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(searchFieldLabel, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectTableField();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Loading..."},
			},
			new String[] {
				"Loading..."
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	

	/**
	 * 传入table 表的名字，进行填充数据
	 */
	public void tableFill() {
		//查出这个表中有多少字段
		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
		
		List<String> seledtedFieldList = new ArrayList<String>();
		for (int i = 0; i < tableBean.getFieldListTableList().size(); i++) {
			FieldBean fieldBean = tableBean.getFieldListTableList().get(i);
			seledtedFieldList.add(fieldBean.getName());
		}
		
		//构造table填充一行的数据
		String[] rowData = new String[seledtedFieldList.size()];
		for (int i = 0; i < seledtedFieldList.size(); i++) {
			rowData[i] = "xxx";
		}
		//绘制table
		table.setModel(new DefaultTableModel(
			new Object[][] {
				rowData,rowData,rowData,rowData,rowData,rowData,rowData,rowData,rowData,rowData
			},
			seledtedFieldList.toArray()
		));
		
	}
	
	/**
	 * 选择、调整字段
	 */
	public void selectTableField() {
		//查出这个表当前的信息
		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
		
		//当前显示的,选中的
		List<FieldBean> currentSelectedFieldList = tableBean.getFieldListTableList();
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
				
				//查出这个表当前的信息
				TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
				
				//重新组合选择的field
				List<FieldBean> currentSelectedFieldList = new ArrayList<FieldBean>();
				for (int i = 0; i < tableBean.getFieldList().size(); i++) {
					FieldBean fieldBean = tableBean.getFieldList().get(i);
					if(map.get(fieldBean.getName()) != null && map.get(fieldBean.getName()).toString().equalsIgnoreCase("false")) {
						continue;
					}
					currentSelectedFieldList.add(fieldBean);
				}
				tableBean.setFieldListTableList(currentSelectedFieldList);
				MainJframe.tableBeanMap.put(tableName, tableBean);
				
				//重新加载数据
				tableFill();
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
	
	/**
	 * 绘制填充搜索field的数据
	 */
	public void searchFieldFill() {
		//查出这个表中有多少字段
		TableBean tableBean = MainJframe.tableBeanMap.get(tableName);
		
		//当前搜索使用的
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
	public JButton getAddSearchFieldButton() {
		return addSearchFieldButton;
	}
}

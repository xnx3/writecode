package com.xnx3.autowritecode.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.xnx3.autowritecode.bean.TableBean;
import com.xnx3.autowritecode.interfaces.SelectTableInterface;

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

/**
 * 选择数据表
 * @author 管雷鸣
 *
 */
public class SelectTableJframe extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JTextField packageTextField;
	public SelectTableInterface selectTable;


	/**
	 * Create the frame.
	 */
	public SelectTableJframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("生成代码");
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
			}
		});
		
		JLabel lblNewLabel = new JLabel("包名：");
		
		packageTextField = new JTextField();
		packageTextField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(115)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
					.addGap(159))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(packageTextField, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(packageTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"选择", "数据表名字", "数据表备注"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		
	}
	public JTextField getPackageTextField() {
		return packageTextField;
	}
}

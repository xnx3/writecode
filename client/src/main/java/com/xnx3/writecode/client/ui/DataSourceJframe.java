package com.xnx3.writecode.client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.xnx3.swing.DialogUtil;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.client.Global;
import com.xnx3.writecode.datasource.Mysql;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * 数据源-mysql链接设置
 * @author 管雷鸣
 *
 */
public class DataSourceJframe extends JFrame {

	private JPanel contentPane;
	private JTextField hostTextField;
	private JTextField databaseNameTextField;
	private JLabel lblUsername;
	private JTextField UsernameTextField;
	private JLabel lblDatabasename_2;
	private JTextField PasswordTextField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataSourceJframe frame = new DataSourceJframe();
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
	public DataSourceJframe() {
		setTitle("mysql数据库连接设置");
		setBounds(100, 100, 324, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("主机(host)");
		
		hostTextField = new JTextField();
		hostTextField.setText("local.mysql.leimingyun.com");
		hostTextField.setColumns(10);
		
		JLabel lblDatabasename = new JLabel("数据库名字");
		
		databaseNameTextField = new JTextField();
		databaseNameTextField.setText("wangmarket");
		databaseNameTextField.setColumns(10);
		
		lblUsername = new JLabel("登录用户名");
		
		UsernameTextField = new JTextField();
		UsernameTextField.setText("root");
		UsernameTextField.setColumns(10);
		
		lblDatabasename_2 = new JLabel("登录的密码");
		
		PasswordTextField = new JTextField();
		PasswordTextField.setText("111111");
		PasswordTextField.setColumns(10);
		
		btnNewButton = new JButton("保存");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.dataSource = new Mysql(hostTextField.getText(), 3306, databaseNameTextField.getText(),UsernameTextField.getText(), PasswordTextField.getText());
				Global.dataSource.connect();//建立连接
				
				//随便执行一个查询判断是否参数填写正常
				try {
					List<TableBean> list = Global.dataSource.getTableList();
				} catch (Exception e2) {
					DialogUtil.showMessageDialog("连接失败，请检查信息是否填写正确");
					Global.dataSource = null;
					e2.printStackTrace();
					return;
				}
				
				DialogUtil.showMessageDialog("设置成功");
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(hostTextField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDatabasename, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(databaseNameTextField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(UsernameTextField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDatabasename_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(PasswordTextField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(hostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatabasename)
						.addComponent(databaseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(UsernameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatabasename_2)
						.addComponent(PasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public JTextField getHostTextField() {
		return hostTextField;
	}
	public JTextField getDatabaseNameTextField() {
		return databaseNameTextField;
	}
	
	/**
	 * 不显示窗体
	 */
	public void disable() {
		this.setVisible(false);
	}
}

package com.xnx3.writecode.client.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.xnx3.SystemUtil;
import com.xnx3.swing.DialogUtil;
import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.client.Global;
import com.xnx3.writecode.interfaces.SelectTableInterface;
import com.xnx3.writecode.template.wm.controller.ControllerTemplate;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * 主界面
 * @author 管雷鸣
 *
 */
public class MainJframe extends JFrame {

	private JPanel contentPane;
	private JButton SelectTableButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJframe frame = new MainJframe();
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
	public MainJframe() {
		setTitle("WriteCode 代码生成器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("1. 请选择数据源");
		
		JLabel lblNewLabel_1 = new JLabel("2. 选择生成模板");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mysql"}));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"template-wm"}));
		
		JButton btnNewButton = new JButton("配置连接信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSourceJframe ds = new DataSourceJframe();
				ds.setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("生 成 代 码");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/**** 进行自检 ****/
				//数据源
				if(Global.dataSource == null) {
					DialogUtil.showMessageDialog("请先设置数据源及连接信息");
					return;
				}
				//模板
				//数据表
				if(Global.tableList == null || Global.tableList.size() == 0) {
					DialogUtil.showMessageDialog("请选择要生成的数据表有哪些");
					return;
				}
				
				Template template = new ControllerTemplate();
				template.setTemplateFileAbsolutePath("{project.path.absolute}/src/main/java/com/xnx3/writecode/template/wm/controller/");
				template.setTemplateFileName("template");
//				
				for (int i = 0; i < Global.tableList.size(); i++) {
					System.out.print(Global.tableList.get(i));
					WriteCode code = new WriteCode(Global.dataSource, template);
					code.writeCode(Global.tableList.get(i));
				}
				DialogUtil.showMessageDialog("写出java文件完毕！");
			}
		});
		
		JLabel lblNewLabel_1_1 = new JLabel("3. 生成的数据表");
		
		SelectTableButton = new JButton("选择数据表");
		SelectTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteCode wc = new WriteCode(Global.dataSource, new Template());
				wc.selectTable(new SelectTableInterface() {
					public void selectFinish(List<TableBean> list) {
						System.out.println(list);
						Global.tableList = list;
					}
				});
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("源代码开源： https://github.com/xnx3/writecode");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SystemUtil.openUrl("https://github.com/xnx3/writecode");
			}
		});
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(SelectTableButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton))
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(33, Short.MAX_VALUE))
				.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(SelectTableButton))
					.addGap(52)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public JButton getSelectTableButton() {
		return SelectTableButton;
	}
}

package com.xnx3.writecode;

import java.awt.Button;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.NebulaSkin;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.SystemUtil;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.interfaces.DataSourceInterface;
import com.xnx3.writecode.interfaces.SelectTableInterface;
import com.xnx3.writecode.ui.MainJframe;
import com.xnx3.writecode.util.TemplateUtil;
import com.xnx3.ClassUtil;
import com.xnx3.swing.DialogUtil;

/**
 * 自动写代码。如写实体类、增删查改了
 * @author 管雷鸣
 *
 */
public class WriteCode {
	public DataSource dataSource;	//数据源
	public Template template;	//生成的是啥
	
	public WriteCode(DataSourceInterface dataSource, Template template) {
		this.template = template.clone();
		
		//判断包名是否有设置
		if(this.template.javaPackage == null) {
			StackTraceElement st = Thread.currentThread().getStackTrace()[2];
			this.template.javaPackage = StringUtil.subString(st.getClassName(), null, ".", 1); //得到如 com.xnx3.j2ee
		}
		if(this.template.writeFileAbsolutePath == null) {
			this.template.setWriteFileAbsolutePath(packageToFilePath(this.template.javaPackage));
		}
		if(this.template.templateFileAbsolutePath == null) {
			this.template.setTemplateFileAbsolutePath(this.template.getWriteFileAbsolutePath());
		}
		
		this.dataSource = new DataSource(dataSource);
	}
	
	/**
	 * 获取某个表自动生成的代码. 建议使用 {@link #getCode(TableBean)}
	 * @param tableName 数据表的名字
	 * @return 实体类的java代码
	 */
	public String getCode(String tableName) {
		TableBean tableBean = this.dataSource.table(tableName);
		return getCode(tableBean);
	}
	

	/**
	 * 获取某个表自动生成的代码
	 * @param tableName 数据表的名字
	 * @return 实体类的java代码
	 */
	public String getCode(TableBean tableBean) {
		TemplateUtil templateUtil = new TemplateUtil();
		templateUtil.setTemplate(this.template);
		
		//替换当前this.template中的一些参数的标签
		if(this.template.getWriteFileAbsolutePath().indexOf("{") > -1) {
			String absPath = new TemplateUtil(this.template.getWriteFileAbsolutePath(), this.template).template(tableBean);
			this.template.setWriteFileAbsolutePath(absPath);
		}
		if(this.template.getWriteFileName().indexOf("{") > -1) {
			String fileName = new TemplateUtil(this.template.getWriteFileName(), this.template).template(tableBean);
			this.template.setWriteFileName(fileName);
		}
		if(this.template.getTemplateFileAbsolutePath() != null && this.template.getTemplateFileAbsolutePath().indexOf("{") > -1) {
			String path = new TemplateUtil(this.template.getTemplateFileAbsolutePath(), this.template).template(tableBean);
			this.template.setTemplateFileAbsolutePath(path);
		}
		if(this.template.getProjectUrlPath() != null && this.template.getProjectUrlPath().indexOf("{") > -1) {
			String text = new TemplateUtil(this.template.getProjectUrlPath(), this.template).template(tableBean);
			this.template.setProjectUrlPath(text);
		}
		
		
		/*
		 * 设置模板，加载顺序为：
		 * 	1. 优先加载跟当前生成的java同路径下的 entity.template 模板文件
		 *  2. 从网络中拉取 entity.template 模板文件
		 * 
		 */
		//加载跟当前生成的java同路径下的 entity.template 模板文件
		File file = new File(this.template.getTemplateFileAbsolutePath()+this.template.templateFileName);
		if(file.exists()) {
			templateUtil.setTemplateText(FileUtil.read(file.getPath()));
		}
		
		//使用包内默认的模板文件
		if(templateUtil.getTemplateText() == null) {
			templateUtil.setTemplateText(this.template.getDefaultTemplateText());
		}
		
		
		if(templateUtil.getTemplateText() == null || templateUtil.getTemplateText().length() == 0) {
			System.err.println("模板内容为空！路径："+this.template.writeFileAbsolutePath+this.template.templateFileName);
			return "";
		}
		
		String code = templateUtil.template(tableBean);
		
		return code;
	}
	
	
	/**
	 * 根据数据库中某个表的信息，写出代码文件.
	 * 建议使用 {@link #writeCode(TableBean)}
	 * @param tableName 数据表的名字
	 * @deprecated
	 */
	public void writeCode(String tableName) {
//		String fileName = this.template.getWriteFileName();
		
//		//对其进行替换
//		if(fileName.indexOf("{") > -1) {
//			TableBean tableBean = this.dataSource.table(tableName);
//			fileName = new TemplateUtil(fileName, this.template).template(tableBean);
//		}
		String codeText = getCode(tableName);
		
		//判断文件夹是否存在，不存在，则创建
		File file = new File(this.template.getWriteFileAbsolutePath());
		if(!file.exists()) {
			file.mkdirs();
		}
		
		System.out.println("生成: "+this.template.getWriteFileAbsolutePath()+this.template.getWriteFileName());
		FileUtil.write(this.template.getWriteFileAbsolutePath()+this.template.getWriteFileName(), codeText);
	}
	

	/**
	 * 根据数据库中某个表的信息，写出代码文件。
	 * @param tableName 数据表的名字
	 */
	public void writeCode(TableBean tableBean) {
		String codeText = getCode(tableBean);
		
		//判断文件夹是否存在，不存在，则创建
		File file = new File(this.template.getWriteFileAbsolutePath());
		if(!file.exists()) {
			file.mkdirs();
		}
		
		System.out.println("生成: "+this.template.getWriteFileAbsolutePath()+this.template.getWriteFileName());
		FileUtil.write(this.template.getWriteFileAbsolutePath()+this.template.getWriteFileName(), codeText);
	}
	
	
	/**
	 * 出现一个UI界面，通过界面选择数据表，然后进行生成
	 * @param selectTable 选择数据表后，点击生成按钮，所执行的操作实现
	 */
	public void selectTable(SelectTableInterface selectTable) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		JDialog.setDefaultLookAndFeelDecorated(true);
//		SwingUtilities.invokeLater(new Runnable() { 
//			public void run() {
//				try{
//					SubstanceLookAndFeel.setSkin(new NebulaSkin());
//				}catch(Exception e){
//					e.printStackTrace();
//				}
				MainJframe selectTableJframe = new MainJframe();
				selectTableJframe.selectTable = selectTable;
				DefaultTableModel tableModel=(DefaultTableModel) selectTableJframe.table.getModel();
				tableModel.getDataVector().clear();		//清空所有
				
				if(!dataSource.dataSourceInterface.isconnect()) {
					dataSource.dataSourceInterface.connect();
				}
				List<TableBean> list = dataSource.dataSourceInterface.getTableList();
				for (int i = 0; i < list.size(); i++) {
					TableBean tableBean = list.get(i);
					JCheckBox chckbxNewCheckBox = new JCheckBox(tableBean.getName()+" - "+tableBean.getComment());
					selectTableJframe.add(chckbxNewCheckBox);
					selectTableJframe.getContentPane().add(chckbxNewCheckBox);
					
					Vector rowData = new Vector();
					rowData.add(false);
					rowData.add(tableBean.getName());
					rowData.add("<html><img src=\"https://img0.baidu.com/it/u=1308554881,3945382158&fm=253&fmt=auto&app=138&f=JPEG?w=488&h=500\" width=\"15\" height=\"15\" />");
					rowData.add("<html><center><img src=\"https://img0.baidu.com/it/u=1308554881,3945382158&fm=253&fmt=auto&app=138&f=JPEG?w=488&h=500\" width=\"15\" height=\"15\" /></center>");
					rowData.add(tableBean.getComment());
					tableModel.insertRow(i, rowData);
					
					//获取这个表的详细信息,进行持久化存储
					TableBean tb = dataSource.dataSourceInterface.getTable(tableBean.getName());
					selectTableJframe.tableBeanMap.put(tableBean.getName(), tb);
				}
				
				selectTableJframe.setVisible(true);
//			}
//		});
	}
	
	/**
	 * 写出实体类的代码，通过选择数据表的UI界面
	 */
	public void writeEntityCodeBySelectTableUI() {
		selectTable(new SelectTableInterface() {
			@Override
			public void selectFinish(List<TableBean> list) {
				for (int i = 0; i < list.size(); i++) {
					writeCode(list.get(i));
				}
				DialogUtil.showMessageDialog("写出java文件完毕！");
				SystemUtil.openLocalFolder(template.getWriteFileAbsolutePath());
			}
		});
	}
	

	/**
	 * 传入当前项目的一个包名，返回这个包所在的项目的路径
	 * @param packageName 传入如 com.xnx3.j2ee.entity
	 * @return 返回这个包所在的绝对路径，如 /Users/apple/git/autowritecode/src/main/java/com/xnx3/j2ee/entity/
	 */
	public static String packageToFilePath(String packageName) {
		String fileSeparator = File.separator;
		// 如果时windows系统，这个是\，所以要再加个\\，避免转义字符
		if(fileSeparator.equals("\\")) {
			fileSeparator = "\\" + fileSeparator;
		}
		
		String projectPath = ".src.main.java."+packageName+".";	//得到如 src.main.java.com.xnx3.j2ee.entity.
		projectPath = projectPath.replaceAll("\\.", fileSeparator);	//换为文件路径形式，如 /Users/apple/git/wm/main/java/com/xnx3/j2ee/entity/
		projectPath = SystemUtil.getCurrentDir()+projectPath; 
		return projectPath;
	} 
	
}

package com.xnx3.autowritecode;

import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.SystemUtil;
import com.xnx3.autowritecode.entity.Entity;
import com.xnx3.autowritecode.bean.TableBean;
import com.xnx3.autowritecode.entity.util.HumpUtil;
import com.xnx3.autowritecode.interfaces.DataSourceInterface;
import com.xnx3.autowritecode.interfaces.SelectTableInterface;
import com.xnx3.autowritecode.ui.SelectTableJframe;
import com.xnx3.autowritecode.util.ClassUtil;

/**
 * 自动写代码。如写实体类、增删查改了
 * @author 管雷鸣
 *
 */
public class WriteCode {
	public String javaPackage = "xxxx"; //生成存放的包，格式如 com.xnx3.j2ee
	public DataSource dataSource;	//数据源
	
	public WriteCode(DataSourceInterface dataSourceImpl) {
		StackTraceElement st = Thread.currentThread().getStackTrace()[2];
		javaPackage = StringUtil.subString(st.getClassName(), null, ".", 1); //得到如 com.xnx3.j2ee
		
		this.dataSource = new DataSource(dataSourceImpl);
	}
	
	/**
	 * 要生成的Java类的包。在new对象时，便会在构造方法中会自动获取你运行时的java文件的包名赋予
	 * @return 格式如 com.xnx3.entity
	 */
	public String getJavaPackage() {
		return javaPackage;
	}

	public void setJavaPackage(String javaPackage) {
		this.javaPackage = javaPackage;
	}
	
	/**
	 * 获取当前操作的数据源
	 * @return
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 获取某个表的实体类的java代码
	 * @param tableName 数据表的名字
	 * @return 实体类的java代码
	 */
	public String getEntityCode(String tableName) {
		TableBean tableBean = this.dataSource.table("user");
		
		Entity entity = new Entity();
		entity.packageName = this.javaPackage;
		String code = entity.template(tableBean);
		
		return code;
	}
	
	/**
	 * 写出某个表的实体类的java文件
	 * @param tableName 数据表的名字
	 */
	public void writeEntityCode(String tableName) {
		System.out.println(ClassUtil.packageToFilePath(this.javaPackage)+HumpUtil.upper(tableName)+".java");
		FileUtil.write(ClassUtil.packageToFilePath(this.javaPackage)+HumpUtil.upper(tableName)+".java", getEntityCode(tableName));
	}
	
	public void selectTable(SelectTableInterface selectTable) {
		SelectTableJframe selectTableJframe = new SelectTableJframe();
		selectTableJframe.selectTable = selectTable;
		DefaultTableModel tableModel=(DefaultTableModel) selectTableJframe.table.getModel();
		tableModel.getDataVector().clear();		//清空所有
		
		if(!this.dataSource.dataSourceInterface.isconnect()) {
			this.dataSource.dataSourceInterface.connect();
		}
		List<TableBean> list = this.dataSource.dataSourceInterface.getTableList();
		for (int i = 0; i < list.size(); i++) {
			TableBean tableBean = list.get(i);
			JCheckBox chckbxNewCheckBox = new JCheckBox(tableBean.getName()+" - "+tableBean.getComment());
			System.out.println(tableBean);
			selectTableJframe.add(chckbxNewCheckBox);
			selectTableJframe.getContentPane().add(chckbxNewCheckBox);
			
			Vector rowData = new Vector();
			rowData.add(false);
			rowData.add(tableBean.getName());
			rowData.add(tableBean.getComment());
			tableModel.insertRow(i, rowData);
		}
		
		selectTableJframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		
	}
	
}

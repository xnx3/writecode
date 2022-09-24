package com.xnx3.writecode.template.wm;

import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.datasource.Mysql;
import com.xnx3.writecode.interfaces.DataSourceInterface;
import com.xnx3.writecode.template.wm.controller.ControllerTemplate;
import com.xnx3.writecode.template.wm.entity.EntityTemplate;
import com.xnx3.writecode.template.wm.listJsp.ListJspTemplate;

/**
 * 运行测试
 * @author 管雷鸣
 */
public class Code {
	static String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
	static int port = 3306;			//端口号
	static String databaseName = "wangmarket"; //数据表的名字
	static String username = "root"; 	//数据库登录用户名
	static String password = "111111";	//数据库登录密码
	
	//指定数据源为 Mysql
	static DataSourceInterface dataSource;
	
	//指定生成到哪个包 ,入 com.xnx3.core
	private String packageName;
	private String projectUrlPath;
	
	static {
		 dataSource = new Mysql(host, port, databaseName, username, password);
	}
	
	public Code() {
	}
	
	
	public void write() {

		//指定生成模板为wm框架的实体类
		Template template = new EntityTemplate();
		
		//进行生成代码
//		WriteCode code = new WriteCode(dataSource, new EntityTemplate());
//		code.selectTable(new SelectTableInterface() {
//			@Override
//			public void selectFinish(List<String> list) {
//				for (int i = 0; i < list.size(); i++) {
//					String tableName = list.get(i);
//					writeCodeByTableName(tableName);
//				}
//				DialogUtil.showMessageDialog("写出代码完毕！");
//				//SystemUtil.openLocalFolder(template.getWriteFileAbsolutePath());
//				System.exit(0);
//			}
//		});
		
		writeCodeByTableName("system");
		System.exit(0);
	}
	
	/**
	 * 生成某个数据表的 entity\controller\vo\jsp 等文件
	 * @param tableName
	 */
	public void writeCodeByTableName(String tableName) {
		
		/*** 生成entity实体类 ***/
		Template entityTemplate = new EntityTemplate();
		entityTemplate.setJavaPackage(this.packageName+".entity");
		new WriteCode(dataSource, entityTemplate).writeCode(tableName);

		/*** 生成Controller ***/
		Template controllerTemplate = new ControllerTemplate();
		controllerTemplate.setJavaPackage(this.packageName+".controller");
		controllerTemplate.setProjectUrlPath(this.projectUrlPath);
		new WriteCode(dataSource, controllerTemplate).writeCode(tableName);
		
		/*** 生成 list vo ***/
		
		/*** 生成 edit vo ***/
		
		/*** 生成list.jsp ***/
		Template listJspTemplate = new ListJspTemplate();
		listJspTemplate.setWriteFileAbsolutePath("{project.path.absolute}/src/main/webapp/{database.table.name.hump.lower}/");
		listJspTemplate.setProjectUrlPath(this.projectUrlPath);
		new WriteCode(dataSource, listJspTemplate).writeCode(tableName);
		
		/*** 生成 edit.jsp ***/
		
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setProjectUrlPath(String projectUrlPath) {
		this.projectUrlPath = projectUrlPath;
	}
	
	
}

package com.xnx3.writecode.template.wm;

import java.util.List;
import com.xnx3.ClassUtil;
import com.xnx3.SystemUtil;
import com.xnx3.swing.DialogUtil;
import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.datasource.Mysql;
import com.xnx3.writecode.interfaces.DataSourceInterface;
import com.xnx3.writecode.interfaces.SelectTableInterface;
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
	
	static {
		 dataSource = new Mysql(host, port, databaseName, username, password);
	}
	
	public Code(String packageName) {
		this.packageName = packageName;
	}
	
	public static void main(String[] args) {
		Code code = new Code("com.xnx3.test");
		code.write();
//		System.out.println(ClassUtil.packageToFilePath("com.xnx3.test"));
		
	}
	
	public void write() {

		//指定生成模板为wm框架的实体类
		Template template = new EntityTemplate();
//		Template template = new VoTemplate();
		
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
	
	public void writeCodeByTableName(String tableName) {
		
		//生成entity实体类
		Template entityTemplate = new EntityTemplate();
		
		entityTemplate.setJavaPackage(this.packageName+".entity");
		new WriteCode(dataSource, entityTemplate).writeCode(tableName);
//		
//		//生成Controller
//		Template controllerTemplate = new ControllerTemplate();
//		controllerTemplate.setJavaPackage(this.packageName+".controller");
//		new WriteCode(dataSource, controllerTemplate).writeCode(tableName);
		
		//vo
		
		//生成list.jsp
//		Template listJspTemplate = new ListJspTemplate();
//		listJspTemplate.setWriteFileAbsolutePath("{project.path.absolute}/src/main/webapp/{database.table.name.hump.lower}/");
//		new WriteCode(dataSource, listJspTemplate).writeCode(tableName);
		
		//edit.jsp
	}
}

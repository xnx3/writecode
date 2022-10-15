package com.xnx3.writecode.template.wm;

import java.util.List;
import com.xnx3.j2ee.util.ApplicationPropertiesUtil;
import com.xnx3.swing.DialogUtil;
import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.datasource.Mysql;
import com.xnx3.writecode.interfaces.DataSourceInterface;
import com.xnx3.writecode.interfaces.SelectTableInterface;
import com.xnx3.writecode.template.wm.controller.ControllerTemplate;
import com.xnx3.writecode.template.wm.controller.ControllerTemplateTagExtend;
import com.xnx3.writecode.template.wm.editJsp.EditJspTemplate;
import com.xnx3.writecode.template.wm.editVo.EditVoTemplate;
import com.xnx3.writecode.template.wm.entity.EntityTemplate;
import com.xnx3.writecode.template.wm.listJsp.ListJspTemplate;
import com.xnx3.writecode.template.wm.listVo.ListVoTemplate;
import com.xnx3.writecode.util.ClassUtil;

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
		//读取，加载mysql数据库配置信息
		String ip = ApplicationPropertiesUtil.getProperty("database.ip");
		String username = ApplicationPropertiesUtil.getProperty("spring.datasource.username");
		String password = ApplicationPropertiesUtil.getProperty("spring.datasource.password");
		String databaseName = ApplicationPropertiesUtil.getProperty("database.name");
		
		dataSource = new Mysql(ip, port, databaseName, username, password);
	}
	
	
	public void write() {
		
		
		
		//进行生成代码
		WriteCode code = new WriteCode(dataSource, new EntityTemplate());
		code.selectTable(new SelectTableInterface() {
			
			@Override
			public void selectFinish(List<TableBean> list) {
				for (int i = 0; i < list.size(); i++) {
					writeCodeByTableName(list.get(i));
				}
				DialogUtil.showMessageDialog("写出代码完毕！");
				//SystemUtil.openLocalFolder(template.getWriteFileAbsolutePath());
				System.exit(0);
			}
		});
		
//		dataSource.connect();
//		writeCodeByTableName(dataSource.getTable("system"));
//		System.exit(0);
	}
	
	/**
	 * 生成某个数据表的 entity\controller\vo\jsp 等文件
	 * @param tableBean
	 */
	public void writeCodeByTableName(TableBean tableBean) {
		
		/*** 生成entity实体类 ***/
		Template entityTemplate = new EntityTemplate();
		entityTemplate.setJavaPackage(this.packageName);
		entityTemplate.setWriteFileAbsolutePath("{project.path.absolute}"+ClassUtil.packageToFilePath(this.packageName+".entity"));
		new WriteCode(dataSource, entityTemplate).writeCode(tableBean);
		
		/*** 生成Controller ***/
		Template controllerTemplate = new ControllerTemplate();
		controllerTemplate.setJavaPackage(this.packageName);
		controllerTemplate.setProjectUrlPath(this.projectUrlPath);
		controllerTemplate.setWriteFileAbsolutePath("{project.path.absolute}"+ClassUtil.packageToFilePath(this.packageName+".controller"));
		new WriteCode(dataSource, controllerTemplate).writeCode(tableBean);
		
		/*** 生成 list vo ***/
		Template listVoTemplate = new ListVoTemplate();
		listVoTemplate.setJavaPackage(this.packageName);
		listVoTemplate.setProjectUrlPath(this.projectUrlPath);
		listVoTemplate.setWriteFileAbsolutePath("{project.path.absolute}"+ClassUtil.packageToFilePath(this.packageName+".vo"));
		new WriteCode(dataSource, listVoTemplate).writeCode(tableBean);
		
		/*** 生成 edit vo ***/
		Template editVoTemplate = new EditVoTemplate();
		editVoTemplate.setJavaPackage(this.packageName);
		editVoTemplate.setProjectUrlPath(this.projectUrlPath);
		editVoTemplate.setWriteFileAbsolutePath("{project.path.absolute}"+ClassUtil.packageToFilePath(this.packageName+".vo"));
		new WriteCode(dataSource, editVoTemplate).writeCode(tableBean);
		
		/*** 生成list.jsp ***/
		Template listJspTemplate = new ListJspTemplate();
		listJspTemplate.setWriteFileAbsolutePath("{project.path.absolute}/src/main/webapp{project.url.path}{database.table.name.hump.lower}/");
		listJspTemplate.setProjectUrlPath(this.projectUrlPath);
		new WriteCode(dataSource, listJspTemplate).writeCode(tableBean);
		
		/*** 生成 edit.jsp ***/
		Template editJspTemplate = new EditJspTemplate();
		editJspTemplate.setWriteFileAbsolutePath("{project.path.absolute}/src/main/webapp{project.url.path}{database.table.name.hump.lower}/");
		editJspTemplate.setProjectUrlPath(this.projectUrlPath);
		new WriteCode(dataSource, editJspTemplate).writeCode(tableBean);
		
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setProjectUrlPath(String projectUrlPath) {
		this.projectUrlPath = projectUrlPath;
	}
	
	
}

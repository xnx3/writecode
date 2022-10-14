package com.xnx3.writecode.template.wm.controller;

import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.datasource.Mysql;
import com.xnx3.writecode.interfaces.DataSourceInterface;

public class TestRun {
	public static void main(String[] args) {
		String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
		int port = 3306;			//端口号
		String databaseName = "wangmarket"; //数据表的名字
		String username = "root"; 	//数据库登录用户名
		String password = "111111";	//数据库登录密码
		
		//指定数据源为 Mysql
		DataSourceInterface dataSource = new Mysql(host, port, databaseName, username, password);
		//指定生成模板为wm框架的实体类
		Template template = new ControllerTemplate();
		//template.setTemplateFileAbsolutePath("{project.path.absolute}/src/main/java/com/xnx3/writecode/template/wm/controller/");
		//template.setTemplateFileName("template");
		template.setProjectUrlPath("/admin/user/");
		
//		ControllerTemplateTagExtend tagExtend = new ControllerTemplateTagExtend();
//		tagExtend.setProjectUrlPath("/admin/user/");
//		template.setTemplateTagExtend(tagExtend);
		
		//进行生成代码
		WriteCode code = new WriteCode(dataSource, template);
		code.writeCode("system");	//写出system表
		System.exit(0);
	}
}

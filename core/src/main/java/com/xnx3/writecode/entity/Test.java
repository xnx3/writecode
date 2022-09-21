package com.xnx3.writecode.entity;

import com.xnx3.writecode.WriteCode2;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.interfaces.DataSourceInterface;
import com.xnx3.writecode.interfaces.TemplateInterface;
import com.xnx3.writecode.interfaces.impl.Mysql;

public class Test {
	public static void main(String[] args) {
		String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
		int port = 3306;			//端口号
		String databaseName = "wangmarket"; //数据表的名字
		String username = "root"; 	//数据库登录用户名
		String password = "111111";	//数据库登录密码
		
		DataSourceInterface dataSource = new Mysql(host, port, databaseName, username, password);
		
		Template template = new Template();
//		template.setJavaPackage(null);
		template.setTemplateFileName("entity.template");
		template.setWriteFileAbsolutePath(null);
		template.setWriteFileName("{database.table.name.hump.upper}.java");
		
		WriteCode2 code = new WriteCode2(dataSource, template);
		code.writeCode("system");
	}
}

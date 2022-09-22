package com.xnx3.writecode.demo;

import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.interfaces.DataSourceInterface;
import com.xnx3.writecode.interfaces.impl.Mysql;

/**
 * 最简单的入门使用
 * @author 管雷鸣
 */
public class Simple {
	public static void main(String[] args) {
		String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
		int port = 3306;			//端口号
		String databaseName = "wangmarket"; //数据表的名字
		String username = "root"; 	//数据库登录用户名
		String password = "111111";	//数据库登录密码
		
		DataSourceInterface dataSource = new Mysql(host, port, databaseName, username, password);
//		
//		Template template = new Template();
////		template.setJavaPackage(null);
//		template.setTemplateFileName("entity.template");
//		template.setWriteFileAbsolutePath(null);
//		template.setWriteFileName("{database.table.name.hump.upper}.java");
//		
		Template entityTemplate = new com.xnx3.writecode.entity.EntityTemplate();
		
		WriteCode code = new WriteCode(dataSource, entityTemplate);
		//手动设置当前生成的包名。如果不手动设置，那生成的entity实体类就会在当前包下
		//code.setJavaPackage("com.xnx3.entity");	
//		code.writeEntityCodeBySelectTableUI();
		System.out.println(code.getCode("system"));
		
	}
}

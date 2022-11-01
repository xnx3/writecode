package com.xnx3.writecode.demo.simple;

import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.datasource.Mysql;
import com.xnx3.writecode.interfaces.DataSourceInterface;

/**
 * 最简单的入门使用
 * @author 管雷鸣
 */
public class Demo {
	
	public static void main(String[] args) {
		String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
		int port = 3306;			//端口号
		String databaseName = "wangmarket"; //数据表的名字
		String username = "root"; 	//数据库登录用户名
		String password = "111111";	//数据库登录密码
		
		//指定数据源为 Mysql
		DataSourceInterface dataSource = new Mysql(host, port, databaseName, username, password);
		//指定生成模板
		Template template = new Template();
		template.setTemplateFileName("demo.template");	//生成文件的模板
		template.setWriteFileName("demo.txt"); 		//写出文件的名字
		
		//选择相应数据表-写出代码
		WriteCode code = new WriteCode(dataSource, template);
		code.writeEntityCodeBySelectTableUI();
	}
}

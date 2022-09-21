package com.xnx3.writecode.controller;

import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.interfaces.impl.Mysql;

public class Test {
	public static void main(String[] args) {
		String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
		int port = 3306;			//端口号
		String databaseName = "wangmarket"; //数据表的名字
		String username = "root"; 	//数据库登录用户名
		String password = "111111";	//数据库登录密码
		
		WriteCode code = new WriteCode(new Mysql(host, port, databaseName, username, password));
		code.writeControllerCode("system");
	}
}

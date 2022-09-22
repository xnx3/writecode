package com.xnx3.writecode.springboot;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import com.xnx3.j2ee.util.ApplicationPropertiesUtil;
import com.xnx3.writecode.WriteCode;
import com.xnx3.writecode.interfaces.impl.Mysql;

public class Demo {
	
	public static void main(String[] args) {
		String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
		int port = 3306;			//端口号
		String databaseName = "wangmarket"; //数据表的名字
		String username = "root"; 	//数据库登录用户名
		String password = "111111";	//数据库登录密码
		
//		WriteCode code = new WriteCode(new Mysql(host, port, databaseName, username, password));
		//手动设置当前生成的包名。如果不手动设置，那生成的entity实体类就会在当前包下
		//code.setJavaPackage("com.xnx3.entity");	
//		code.writeEntityCodeBySelectTableUI();
	}
}

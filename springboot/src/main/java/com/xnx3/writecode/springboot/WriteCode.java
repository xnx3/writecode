package com.xnx3.writecode.springboot;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.xnx3.j2ee.util.ApplicationPropertiesUtil;
import com.xnx3.writecode.interfaces.impl.Mysql;

public class WriteCode {
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
            Resource resource = new ClassPathResource("/application.properties");//
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		String host = properties.getProperty("database.ip");	//主机，可以填写域名或ip
		int port = 3306;			//端口号
		String databaseName = properties.getProperty("database.name"); //数据表的名字
		String username = properties.getProperty("spring.datasource.username"); 	//数据库登录用户名
		String password = properties.getProperty("spring.datasource.password");;	//数据库登录密码
		
		com.xnx3.writecode.WriteCode code = new com.xnx3.writecode.WriteCode(new Mysql(host, port, databaseName, username, password));
		code.writeEntityCodeBySelectTableUI();
	}
}

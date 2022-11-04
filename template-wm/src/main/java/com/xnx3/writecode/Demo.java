package com.xnx3.writecode;

import com.xnx3.writecode.datasource.Mysql;
import com.xnx3.writecode.template.wm.Code;

/**
 * DEMO示例，自动生成实体类、controller、vo、jsp页面等
 * @author 管雷鸣
 */
public class Demo {
	public static void main(String[] args) {
		Code code = new Code(); //这行会报出 class path resource [application.properties] cannot be opened because it does not exist  ,不用管，实际使用就没事了
		code.dataSource = new Mysql("local.mysql.leimingyun.com", 3306, "wangmarket", "root", "111111"); //实际使用不用加这个，会自动从application.properties中读取数据库的配置信息
 		code.setPackageName("com.xnx3.test");	//设置生成的entity、controller、vo等java类放到哪个包下
		code.setProjectUrlPath("/admin/"); //设置url请求的路径
		code.write();	//创建相关代码文件
	}
}

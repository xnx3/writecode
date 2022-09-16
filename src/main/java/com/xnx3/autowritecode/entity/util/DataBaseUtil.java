package com.xnx3.autowritecode.entity.util;

import com.xnx3.MysqlUtil;
import com.xnx3.autowritecode.entity.bean.TableBean;

/**
 * 数据库方面的工具类
 * @author 管雷鸣
 *
 */
public class DataBaseUtil {
	
	/**
	 * 获取指定数据表的信息
	 * @param tableName 数据表的实际名称，传入如 user
	 * @return 获取到的该数据表的 {@link TableBean} 信息
	 */
	public static TableBean table(String tableName) {
		//数据表名字：  wangmarket
		String databaseName = "wangmarket";
		
		MysqlUtil mysql = new MysqlUtil("jdbc:mysql://local.mysql.leimingyun.com:3306/wangmarket?useUnicode=true", "root", "111111");
		System.out.println("测试数据库连接"+mysql.select("select count(*) from system"));
		
		/*
		 * 数据库正常连接
		 * mysql.getConn() 获取数据库连接进行操作
		 */
		
		//查询数据表的一些信息
		System.out.println(mysql.select("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'wangmarket'"));
		
		//查询数据
		String sql = "SELECT COLUMN_NAME, COLUMN_TYPE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE, COLUMN_DEFAULT, COLUMN_COMMENT "
				+ "FROM INFORMATION_SCHEMA.COLUMNS "
				+ "WHERE table_schema ='"+databaseName+"' "
				+ "AND table_name  = '"+tableName+"'";
		System.out.println(mysql.select(sql));
		
		TableBean tableBean = new TableBean();
		return tableBean;
	}
	
	public static void main(String[] args) {
		
		
		System.out.println(table("user"));
	}
}

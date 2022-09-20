package com.xnx3.autowritecode.interfaces.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.xnx3.MysqlUtil;
import com.xnx3.autowritecode.entity.bean.FieldBean;
import com.xnx3.autowritecode.entity.bean.TableBean;
import com.xnx3.autowritecode.interfaces.DataSourceInterface;

/**
 * mysql数据源的实现
 * @author 管雷鸣
 */
public class Mysql implements DataSourceInterface{
	MysqlUtil mysql;
	private String host; 		//主机，传入ip，或者域名，如  local.mysql.leimingyun.com
	private int port;			// mysql的端口号，如 3306
	private String databaseName;//数据库的名字，如 wangmarket
	private String username;	//登录用户名，如 root
	private String password;	//登录密码，如 111111
	
	/**
	 * 创建Mysql数据源
	 * @param host 主机，传入ip，或者域名，如  local.mysql.leimingyun.com
	 * @param port mysql的端口号，如 3306
	 * @param databaseName 数据库的名字，如 wangmarket
	 * @param username 登录用户名，如 root
	 * @param password 登录密码，如 111111
	 */
	public Mysql(String host, int port, String databaseName, String username, String password) {
		this.host = host;
		this.port = port;
		this.databaseName = databaseName;
		this.username = username;
		this.password = password;
	}
	
	
	@Override
	public void connect() {
		mysql = new MysqlUtil("jdbc:mysql://"+host+":"+port+"/"+databaseName+"?useUnicode=true", username, password);
	}

	@Override
	public boolean isconnect() {
		if(mysql == null) {
			return false;
		}
		try {
			if(mysql.conn.isClosed()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public TableBean getTable(String name) {
		TableBean tableBean = new TableBean();
		// 查询数据表的一些基本信息
		String queryTable = "SELECT TABLE_NAME, TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES"
				+ " WHERE TABLE_SCHEMA = '" + databaseName + "'"
				+ " AND TABLE_NAME = '" + name + "'";
//		System.out.println(mysql.select(queryTable));
		List<Map<String, Object>> tables = mysql.select(queryTable);
		if (tables.size() - 0 == 0) {
			return tableBean;
		}
		// 记录表的基本信息
		tableBean.setName(tables.get(0).get("TABLE_NAME") + "");
		tableBean.setComment(tables.get(0).get("TABLE_COMMENT") + "");
		
		// 查询表字段数据
		String sql = "SELECT COLUMN_NAME, COLUMN_TYPE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE, COLUMN_DEFAULT, COLUMN_COMMENT, COLLATION_NAME, COLUMN_KEY, EXTRA, NUMERIC_PRECISION"
				+ " FROM INFORMATION_SCHEMA.COLUMNS"
				+ " WHERE TABLE_SCHEMA ='" + databaseName + "'"
				+ " AND TABLE_NAME = '" + name + "'";
		List<Map<String, Object>> fields = mysql.select(sql);
		// 遍历记录所有字段信息
		List<FieldBean> fieldBeanList = new LinkedList<FieldBean>();
		for (Map<String, Object> field : fields) {
			FieldBean fieldBean = new FieldBean();
			fieldBean.setName(field.get("COLUMN_NAME") + "");
			fieldBean.setComment(field.get("COLUMN_COMMENT") + "");
			fieldBean.setDefaultvalue(field.get("COLUMN_DEFAULT") + "");
			fieldBean.setDatatype((String) field.get("DATA_TYPE"));
			// 根据不同的类型记录字段长度
			if (field.get("CHARACTER_MAXIMUM_LENGTH") != null) {
				// 如果通过 CHARACTER_MAXIMUM_LENGTH 能拿到长度，直接记录
				fieldBean.setLength(field.get("CHARACTER_MAXIMUM_LENGTH") + "");
			} else {
				// 未通过 CHARACTER_MAXIMUM_LENGTH 拿到长度，则通过 COLUMN_TYPE 截取实际长度
				String lengthStr = (field.get("COLUMN_TYPE") + "").replace(fieldBean.getDatatype() + "(", "").replace(")", "");
				// 判断是否存在逗号，存在就进行拆分，不存在就直接赋值
				String length = "";
				if (lengthStr.indexOf(",") - (-1) != 0) {
					length = lengthStr.split(",")[0];
				} else {
					length = lengthStr;
				}
				fieldBean.setLength(length);
			}
			fieldBean.setCollate(field.get("COLLATION_NAME") + "");
			// 判断字段是否为主键
			String key = "";
			if (field.get("COLUMN_KEY") != null && "PRI".equals(field.get("COLUMN_KEY") + "")) {
				key = "@Id";
			}
			fieldBean.setIfAnnotationId(key);
			// 判断字段是否为自增属性
			String extra = "";
			if (field.get("EXTRA") != null && "auto_increment".equals(field.get("EXTRA") + "")) {
				extra = "@GeneratedValue(strategy = IDENTITY)";
			}
			fieldBean.setIfAnnotationGeneratedValue(extra);
			fieldBeanList.add(fieldBean);
		}
		tableBean.setFieldList(fieldBeanList);
		
		return tableBean;
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		mysql.closeConnect();
	}


	@Override
	public List<String> getTableList() {
		// TODO Auto-generated method stub
		return null;
	}

}

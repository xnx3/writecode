package com.xnx3.autowritecode.entity.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xnx3.MysqlUtil;
import com.xnx3.autowritecode.entity.bean.FieldBean;
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
		// 数据表名字：  wangmarket
		String databaseName = "wangmarket";
		
		MysqlUtil mysql = new MysqlUtil("jdbc:mysql://local.mysql.leimingyun.com:3306/wangmarket?useUnicode=true", "root", "111111");
		System.out.println("测试数据库连接"+mysql.select("select count(*) from system"));
		
		/*
		 * 数据库正常连接
		 * mysql.getConn() 获取数据库连接进行操作
		 */
		TableBean tableBean = new TableBean();
		// 查询数据表的一些基本信息
		String queryTable = "SELECT TABLE_NAME, TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES"
				+ " WHERE TABLE_SCHEMA = '" + databaseName + "'"
				+ " AND TABLE_NAME = '" + tableName + "'";
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
				+ " AND TABLE_NAME = '" + tableName + "'";
//		System.out.println(mysql.select(sql));
		List<Map<String, Object>> fields = mysql.select(sql);
		// 遍历记录所有字段信息
		List<FieldBean> fieldBeanList = new LinkedList<FieldBean>();
		for (Map<String, Object> field : fields) {
			FieldBean fieldBean = new FieldBean();
			fieldBean.setName(field.get("COLUMN_NAME") + "");
			fieldBean.setComment(field.get("COLUMN_COMMENT") + "");
			fieldBean.setDefaultvalue(field.get("COLUMN_DEFAULT") + "");
			fieldBean.setDatatype(field.get("DATA_TYPE") + "");
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
	
	public static void main(String[] args) {
		
		
		System.out.println(table("user"));
	}
}

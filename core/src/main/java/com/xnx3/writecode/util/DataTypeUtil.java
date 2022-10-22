package com.xnx3.writecode.util;

import java.util.HashMap;
import java.util.Map;

import com.xnx3.writecode.bean.DataTypeBean;

/**
 * 数据类型转换，将数据库的类型转换为Java实体类中的类型
 * @author 管雷鸣
 *
 */
public class DataTypeUtil {
	/**
	 * key: 数据库的类型，如 varchar （mysql、sqlite、oracle等数据库，不仅仅只是局限于一种数据库）
	 * value：java实体类中属性字段的类型，如 Integer
	 */
	public static Map<String, DataTypeBean> map;
	static {
		map = new HashMap<String, DataTypeBean>();
		
		// mysql 的一些类型
		map.put("int", new DataTypeBean("Integer", "int"));
		map.put("tinyint", new DataTypeBean("Short", "short"));
		map.put("float", new DataTypeBean("Float", "float"));
		map.put("double", new DataTypeBean("Double", "double"));
		map.put("smallint", new DataTypeBean("Integer", "int"));
		
		map.put("char", new DataTypeBean("String", "String"));
		map.put("varchar", new DataTypeBean("String", "String"));
		map.put("text", new DataTypeBean("String", "String"));
		map.put("mediumtext", new DataTypeBean("String", "String"));
		
		// sqlserver 中的的一些类型 
		// ...
		
		// Oracle 中的一些类型
		// ...
	}
	
	/**
	 * 传入数据库中的数据类型，返回其对应在java实体类中的数据类型
	 * @param dataType 数据库中当前列的数据类型，传入如 varchar
	 * @return java中的数据类型。如果未发现，则返回数据库本身的类型。
	 */
	public static DataTypeBean databaseToJava(String dataType) {
		DataTypeBean bean = map.get(dataType.toLowerCase());
		if(bean == null) {
			System.err.println("数据类型 "+ dataType +" 未发现！您可以手动加入 DataTypeUtil.map.put(\""+dataType+"\", new DataTypeBean(\"对应的Java数据类型，是Integer、String、还是？\", \"对应的Java数据类型，是int、String、还是？\"));");
			bean = new DataTypeBean(dataType, dataType);
		}
		return bean;
	}
	
	
}

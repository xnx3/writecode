package com.xnx3.autowritecode.entity.util;

import java.util.HashMap;
import java.util.Map;

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
	public static Map<String, String> map;
	static {
		map = new HashMap<String, String>();
		
		// mysql 的一些类型
		map.put("int", "Integer");
		map.put("tinyint", "Short");
		map.put("float", "Float");
		map.put("double", "Double");
		map.put("smallint", "Integer");
		
		map.put("char", "String");
		map.put("varchar", "String");
		map.put("text", "String");
		map.put("mediumtext", "String");
		
		
		
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
	public static String databaseToJava(String dataType) {
		String value = map.get(dataType.toLowerCase());
		if(value == null) {
			System.err.println("数据类型 "+ dataType +" 未发现！您可以手动加入 DataTypeUtil.map.put(\""+dataType+"\", \"对应的Java数据类型，是Integer、String、还是？\");");
			return dataType;
		}
		return value;
	}
	
}

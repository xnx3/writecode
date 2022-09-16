package com.xnx3.autowritecode.entity.util;


/**
 * 驼峰式命名的工具类
 * @author 管雷鸣
 */
public class HumpUtil {
	
	/**
	 * 对字符串进行小驼峰命名转换
	 * @param value 要进行转换的字符串
	 * @return 转换结果
	 */
	public static String lower(String value) {
		
		/*
		 * 
		 * 参考 https://gitee.com/leimingyun/xnx3_util/blob/master/src/main/java/com/xnx3/StringUtil.java 中的 firstCharToLowerCase 方法
		 * 
		 */
		
		return "";
	}
	
	/**
	 * 对字符串进行大驼峰命名转换
	 * @param value 要进行转换的字符串
	 * @return 转换结果
	 */
	public static String upper(String value) {
		if(value==null || value.equals("")){    
			return "";    
        }
		
		//首字母转大写
		if(Character.isUpperCase(value.charAt(0))){
			return value;
		}else{
			return (new StringBuilder()).append(Character.toUpperCase(value.charAt(0))).append(value.substring(1)).toString();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(upper("user"));
	}
}

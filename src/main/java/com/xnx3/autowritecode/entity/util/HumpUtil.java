package com.xnx3.autowritecode.entity.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if(value == null){
			return "";
		}
		// 如果以下划线开头，去除开头的下划线
		if(value.startsWith("_")){
			value = value.substring(1);
		}
		if("".equals(value)){
			return "";
		}
		// 首字母转小写
		if(Character.isLowerCase(value.charAt(0))){

		}else{
			value = (new StringBuilder()).append(Character.toLowerCase(value.charAt(0))).append(value.substring(1)).toString();
		}
		// 将下划线去除，并将其后面的第一个字母转换为大写
		Pattern p = Pattern.compile("_[A-Za-z]");
		StringBuilder builder = new StringBuilder(value);
		Matcher mc = p.matcher(value);
		int i = 0;
		while (mc.find()) {
			builder.replace(mc.start()+i, mc.end()+i, mc.group().toUpperCase().replace("_", ""));
			i--;
		}
		// 如果以下划线结尾，去除尾部的下划线
		if(builder.length() - 0 > 0 && '_' == builder.charAt(builder.length() - 1)){
			builder.deleteCharAt(builder.length() - 1);
		}
		
		return builder.toString();
	}
	
	/**
	 * 对字符串进行大驼峰命名转换
	 * @param value 要进行转换的字符串
	 * @return 转换结果
	 */
	public static String upper(String value) {
		if(value == null || "".equals(value)){
			return "";
		}
		
		// 首字母转大写
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

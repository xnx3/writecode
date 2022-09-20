package com.xnx3;

import java.io.File;

/**
 * 类 相关的操作工具
 * @author 管雷鸣
 *
 */
public class ClassUtil{
	
	/**
	 * 判断某个class是否存在
	 * @param packageName class的包名，传入如 com.xnx3.wangmarket.plugin.learnExample.Plugin
	 * @return true:class存在，  false:class不存在
	 */
	public static boolean classExist(String packageName){
		try{
			Class.forName(packageName);
			return true;
		}catch(ClassNotFoundException e){
			return false;
		}
	}
	

	/**
	 * 传入当前项目的一个包名，返回这个包所在的项目的路径
	 * @param packageName 传入如 com.xnx3.j2ee.entity
	 * @return 返回这个包所在的绝对路径，如 /Users/apple/git/autowritecode/src/main/java/com/xnx3/j2ee/entity/
	 */
	public static String packageToFilePath(String packageName) {
		String fileSeparator = File.separator;
		// 如果时windows系统，这个是\，所以要再加个\\，避免转义字符
		if(fileSeparator.equals("\\")) {
			fileSeparator = "\\" + fileSeparator;
		}
		
		String projectPath = ".src.main.java."+packageName+".";	//得到如 src.main.java.com.xnx3.j2ee.entity.
		projectPath = projectPath.replaceAll("\\.", fileSeparator);	//换为文件路径形式，如 /Users/apple/git/wm/main/java/com/xnx3/j2ee/entity/
		projectPath = SystemUtil.getCurrentDir()+projectPath; 
		return projectPath;
	} 
	
	
	public static void main(String[] args) {
		System.out.println(classExist("com.xnx3.Languages"));
	}
}
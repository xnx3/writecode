package com.xnx3.writecode.util;

import java.io.File;

public class ClassUtil extends com.xnx3.ClassUtil {

	/**
	 * 传入当前项目的一个包名，返回这个包所在的项目的路径
	 * @param packageName 传入如 com.xnx3.j2ee.entity
	 * @return 返回这个包所在的路径，如 /com/xnx3/j2ee/entity/
	 */
	public static String packageToFilePath(String packageName) {
		String fileSeparator = File.separator;
		// 如果时windows系统，这个是\，所以要再加个\\，避免转义字符
		if(fileSeparator.equals("\\")) {
			fileSeparator = "\\" + fileSeparator;
		}
		
		String projectPath = ".src.main.java."+packageName+".";	//得到如 src.main.java.com.xnx3.j2ee.entity.
		projectPath = projectPath.replaceAll("\\.", fileSeparator);	//换为文件路径形式，如 /Users/apple/git/wm/main/java/com/xnx3/j2ee/entity/
		return projectPath;
	} 
	
}

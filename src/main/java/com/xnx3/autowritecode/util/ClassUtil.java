package com.xnx3.autowritecode.util;

import java.io.File;

import com.xnx3.SystemUtil;

public class ClassUtil {
	
	/**
	 * 传入当前项目的一个包名，返回这个包所在的项目的路径
	 * @param packageName 传入如 com.xnx3.j2ee.entity
	 * @return 返回这个包所在的绝对路径，如 /Users/apple/git/autowritecode/src/main/java/com/xnx3/j2ee/entity/
	 */
	public static String packageToFilePath(String packageName) {
		String projectPath = ".src.main.java."+packageName+".";	//得到如 src.main.java.com.xnx3.j2ee.entity.
		projectPath = projectPath.replaceAll("\\.", File.separator);	//换为文件路径形式，如 /Users/apple/git/wm/main/java/com/xnx3/j2ee/entity/
		projectPath = SystemUtil.getCurrentDir()+projectPath; 
		return projectPath;
	} 
	
}

package com.xnx3.writecode.vo;

import java.io.File;
import java.util.List;

import com.xnx3.writecode.interfaces.TemplateInterface;

/**
 * VO 相关的操作
 * @author 管雷鸣
 *
 */
public class VO implements TemplateInterface{

	@Override
	public String javaPackage() {
		return "com.xnx3.j2ee";
	}

	@Override
	public String writeFileAbsolutePath() {
		return null;
	}

	@Override
	public String templateFileName() {
		// TODO Auto-generated method stub
		return "vo.template";
	}
	
}
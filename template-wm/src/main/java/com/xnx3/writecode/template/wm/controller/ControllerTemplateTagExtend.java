package com.xnx3.writecode.template.wm.controller;

import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.interfaces.TemplateTagExtendInterface;
import com.xnx3.writecode.util.TemplateUtil;

/**
 * controller 的模板变量标签扩展
 * @author Administrator
 *
 */
public class ControllerTemplateTagExtend implements TemplateTagExtendInterface {
	
	private String projectUrlPath;	//生成的controller的访问请求url的路径是什么，传入格式入 /user/api/  这个其实就是Controller上的@RequestMapping用的，使用方式如： @RequestMapping("{project.url.path}{database.table.name.hump.upper}/")
	
	public String getProjectUrlPath() {
		return projectUrlPath;
	}
	public void setProjectUrlPath(String projectUrlPath) {
		this.projectUrlPath = projectUrlPath;
	}
	
	@Override
	public String appendTag(String text, TemplateUtil templateUtil, TableBean tableBean) {
		
		text = TemplateUtil.replaceAll(text, "{project.url.path}", this.projectUrlPath);
		
		return text;
	}

}

package com.xnx3.writecode.template.wm.listVo;

import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.interfaces.TemplateTagExtendInterface;
import com.xnx3.writecode.util.TemplateUtil;

/**
 * list vo 的模板变量标签扩展
 * @author 刘晓腾
 */
public class ListVoTemplateTagExtend implements TemplateTagExtendInterface {
	
	private String projectEntityPath;	//生成的实体类存放的位置
	
	public String getProjectEntityPath() {
		return projectEntityPath;
	}
	public void setProjectEntityPath(String projectEntityPath) {
		this.projectEntityPath = projectEntityPath;
	}
	
	@Override
	public String appendTag(String text, TemplateUtil templateUtil, TableBean tableBean) {
		
		text = TemplateUtil.replaceAll(text, "{project.entity.path}", this.projectEntityPath);
		
		return text;
	}

}

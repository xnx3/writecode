package com.xnx3.writecode.template.wm.controller;

import java.io.IOException;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.template.wm.entity.EntityTemplate;

/**
 * 生成wm中实体类的默认模板
 * @author 管雷鸣
 *
 */
public class ControllerTemplate extends Template{
	public ControllerTemplate() {
		this.setTemplateFileName("controller.template");
		this.setWriteFileName("{database.table.name.hump.upper}Controller.java");
		try {
			String templateText = StringUtil.inputStreamToString(EntityTemplate.class.getResourceAsStream("/com/xnx3/writecode/template/wm/controller/template"), FileUtil.UTF8);
			this.setDefaultTemplateText(templateText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ControllerTemplate temp = new ControllerTemplate();
		System.out.println(temp);
	}
}
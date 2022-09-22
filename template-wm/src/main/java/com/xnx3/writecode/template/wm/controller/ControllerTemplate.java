package com.xnx3.writecode.template.wm.controller;

import com.xnx3.writecode.bean.Template;

/**
 * 生成wm中实体类的默认模板
 * @author 管雷鸣
 *
 */
public class ControllerTemplate extends Template{
	public ControllerTemplate() {
		this.setTemplateFileName("controller.template");
		this.setWriteFileName("{database.table.name.hump.upper}Controller.java");
	}
}
package com.xnx3.writecode.template.wm.listJsp;

import com.xnx3.writecode.bean.Template;

/**
 * 生成wm中list.jsp的默认模板
 * @author 管雷鸣
 *
 */
public class ListJspTemplate extends Template{
	public ListJspTemplate() {
		this.setTemplateFileName("list.jsp.template");
		this.setWriteFileName("list.jsp");
		this.setWriteFileAbsolutePath("{project.path.absolute}/src/main/webapp/{database.table.name.hump.lower}/list.jsp");
	}
}
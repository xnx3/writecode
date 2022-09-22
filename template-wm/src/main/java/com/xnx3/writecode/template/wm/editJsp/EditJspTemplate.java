package com.xnx3.writecode.template.wm.editJsp;

import com.xnx3.writecode.bean.Template;

/**
 * 生成wm中edit.jsp的默认模板
 * @author 管雷鸣
 *
 */
public class EditJspTemplate extends Template{
	public EditJspTemplate() {
		this.setTemplateFileName("edit.jsp.template");
		this.setWriteFileName("edit.jsp");
		this.setWriteFileAbsolutePath("{project.path.absolute}/src/main/webapp/{database.table.name.hump.lower}/list.jsp");
	}
}
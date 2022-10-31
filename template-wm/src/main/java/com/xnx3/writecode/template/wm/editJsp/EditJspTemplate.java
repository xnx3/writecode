package com.xnx3.writecode.template.wm.editJsp;

import java.io.IOException;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.template.wm.BaseTemplate;
import com.xnx3.writecode.template.wm.entity.EntityTemplate;

/**
 * 生成wm中edit.jsp的默认模板
 * @author 管雷鸣
 *
 */
public class EditJspTemplate extends BaseTemplate{
	public EditJspTemplate() {
		super.baseInit();
		this.setTemplateFileName("edit.jsp.template");
		this.setWriteFileName("edit.jsp");
		this.setWriteFileAbsolutePath("{project.path.absolute}/src/main/webapp/{database.table.name.hump.lower}/list.jsp");
		try {
			String templateText = StringUtil.inputStreamToString(EntityTemplate.class.getResourceAsStream("/com/xnx3/writecode/template/wm/editJsp/template"), FileUtil.UTF8);
			this.setDefaultTemplateText(templateText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
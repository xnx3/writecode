package com.xnx3.writecode.template.wm.listJsp;

import java.io.IOException;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.template.wm.entity.EntityTemplate;

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
		try {
			String templateText = StringUtil.inputStreamToString(EntityTemplate.class.getResourceAsStream("/com/xnx3/writecode/template/wm/listJsp/template"), FileUtil.UTF8);
			this.setDefaultTemplateText(templateText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
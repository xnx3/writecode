package com.xnx3.writecode.template.wm.entity;

import java.io.IOException;
import java.util.ArrayList;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.microsoft.File;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.template.wm.BaseTemplate;

/**
 * 生成wm中实体类的默认模板
 * @author 管雷鸣
 *
 */
public class EntityTemplate extends BaseTemplate{
	public EntityTemplate() {
		super.baseInit();
		this.setTemplateFileName("entity.template");
		this.setWriteFileName("{database.table.name.hump.upper}.java");
		this.setDefaultTemplateText(javaPackage);
		try {
			String templateText = StringUtil.inputStreamToString(EntityTemplate.class.getResourceAsStream("/com/xnx3/writecode/template/wm/entity/template"), FileUtil.UTF8);
			this.setDefaultTemplateText(templateText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
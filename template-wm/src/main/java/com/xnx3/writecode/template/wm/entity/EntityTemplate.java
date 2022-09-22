package com.xnx3.writecode.template.wm.entity;

import com.xnx3.writecode.bean.Template;

/**
 * 生成wm中实体类的默认模板
 * @author 管雷鸣
 *
 */
public class EntityTemplate extends Template{
	public EntityTemplate() {
		this.setTemplateFileName("entity.template");
		this.setWriteFileName("{database.table.name.hump.upper}.java");
	}
}
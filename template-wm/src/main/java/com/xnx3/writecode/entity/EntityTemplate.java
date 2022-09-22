package com.xnx3.writecode.entity;

import com.xnx3.writecode.bean.Template;

public class EntityTemplate extends Template{
	public EntityTemplate() {
//		template.setJavaPackage(null);
		this.setTemplateFileName("entity.template");
		this.setWriteFileAbsolutePath(null);
		this.setWriteFileName("{database.table.name.hump.upper}.java");
	}
}
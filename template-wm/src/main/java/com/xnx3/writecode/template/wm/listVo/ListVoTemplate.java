package com.xnx3.writecode.template.wm.listVo;

import com.xnx3.writecode.bean.Template;

/**
 * 生成wm中vo类的默认模板
 * @author 管雷鸣
 *
 */
public class ListVoTemplate extends Template{
	public ListVoTemplate() {
		this.setTemplateFileName("vo.template");
		this.setWriteFileName("{database.table.name.hump.upper}ListVO.java");
	}
}
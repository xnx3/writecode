package com.xnx3.writecode.template.wm.editVo;

import com.xnx3.writecode.bean.Template;

/**
 * 生成wm中vo类的默认模板
 * @author 管雷鸣
 *
 */
public class EditVoTemplate extends Template{
	public EditVoTemplate() {
		this.setTemplateFileName("vo.template");
		this.setWriteFileName("{database.table.name.hump.upper}VO.java");
	}
}
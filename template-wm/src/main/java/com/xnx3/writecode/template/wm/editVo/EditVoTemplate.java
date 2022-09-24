package com.xnx3.writecode.template.wm.editVo;

import java.io.IOException;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.template.wm.entity.EntityTemplate;

/**
 * 生成wm中vo类的默认模板
 * @author 管雷鸣
 *
 */
public class EditVoTemplate extends Template{
	public EditVoTemplate() {
		this.setTemplateFileName("vo.template");
		this.setWriteFileName("{database.table.name.hump.upper}VO.java");
		try {
			String templateText = StringUtil.inputStreamToString(EntityTemplate.class.getResourceAsStream("/com/xnx3/writecode/template/wm/editVo/template"), FileUtil.UTF8);
			this.setDefaultTemplateText(templateText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
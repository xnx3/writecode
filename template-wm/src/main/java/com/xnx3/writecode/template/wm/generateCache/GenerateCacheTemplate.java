package com.xnx3.writecode.template.wm.generateCache;

import java.io.IOException;
import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.template.wm.BaseTemplate;

/**
 * 生成wm中 generate 的js常量缓存
 * @author 管雷鸣
 *
 */
public class GenerateCacheTemplate extends BaseTemplate{
	public GenerateCacheTemplate() {
		super.baseInit();
		this.setTemplateFileName("generateCache.template");
		this.setWriteFileName("{database.table.name.hump.upper}.java");
		this.setDefaultTemplateText(javaPackage);
		try {
			String templateText = StringUtil.inputStreamToString(GenerateCacheTemplate.class.getResourceAsStream("/com/xnx3/writecode/template/wm/generateCache/template"), FileUtil.UTF8);
			this.setDefaultTemplateText(templateText);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
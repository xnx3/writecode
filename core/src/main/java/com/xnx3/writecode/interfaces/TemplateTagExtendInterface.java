package com.xnx3.writecode.interfaces;

import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.util.TemplateUtil;

/**
 * 针对 模板中的变量标签的扩展，增加自己的自定义标签。比如 controller 中可以增加生成的文件的后缀名了等
 * @author 管雷鸣
 *
 */
public interface TemplateTagExtendInterface {
	
	/**
	 * 追加自己的自定义标签
	 * @param text 要对它进行替换的字符串
	 * @param templateUtil
	 * @param tableBean 当前所进行替换动作的 table 数据
	 * @return 替换好的
	 */
	public String appendTag(String text, TemplateUtil templateUtil, TableBean tableBean);
	
}

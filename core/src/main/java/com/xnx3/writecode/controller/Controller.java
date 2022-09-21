package com.xnx3.writecode.controller;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.writecode.bean.FieldBean;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.util.DataTypeUtil;
import com.xnx3.net.HttpResponse;
import com.xnx3.net.HttpUtil;
import com.xnx3.HumpUtil;

/**
 * 自动生成controller代码
 * @author 管雷鸣
 */
public class Controller {
	public String packageName; 	//{java.package} 生成的实体类是在哪个包，格式如 com.xnx3.j2ee.entity
	public String template;		//controller.template 模板内容
	
	/**
	 * 设置 controller.template 模板内容
	 * @param template controller.template 模板的内容文本
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * 传入数据表的名字，输出这个数据表的实体类内容
	 * @param TableBean 数据表的结构信息
	 * @return 这个数据表的实体类内容
	 */
	public String template(TableBean tableBean){
		/*** 模板中的变量替换 ***/
		if(template == null) {
			//为空，则拉取cdn节点的
			HttpResponse hr = new HttpUtil().get("http://res.zvo.cn/writecode/template/controller.template");
			if(hr.getCode() != 200) {
				System.err.println("获取云端 controller.template 失败，http code:"+hr.getCode());
				return "获取云端 controller.template 失败，http code:"+hr.getCode();
			}
			template = hr.getContent();
		}
		
		//全局方面
		template = StringUtil.replaceAll(template, "{java.package}", this.packageName);
		template = StringUtil.replaceAll(template, "{database.table.comment}", tableBean.getComment());
		template = StringUtil.replaceAll(template, "{database.table.name}", tableBean.getName());
		template = StringUtil.replaceAll(template, "{database.table.name.hump.upper}", HumpUtil.upper(tableBean.getName()));
		
		
		return template;
	}
	
	/**
	 * 正则替换
	 * @param text 操作的内容源，主体
	 * @param regex 替换掉的
	 * @param replacement 替换成新的，取而代之的。如果传入null，则会变为 "" 空字符串的形式进行替换
	 * @return 替换好的内容。 如果传入的regex为null，则不会进行替换，直接将text原样返回。
	 */
	public static String replaceAll(String text, String regex, String replacement){
		if(text == null || regex == null) {
			return text;
		}
		if(replacement == null) {
			replacement = "";
		}
		
		String s[] = {"?","(",")","{","}","*"}; 
		for (int i = 0; i < s.length; i++) {
			regex = regex.replaceAll("\\"+s[i], "\\\\"+s[i]);
		}
		text = text.replaceAll(regex, replacement);
		
		return text;
	}
	
}

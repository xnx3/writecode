package com.xnx3.writecode.util;

import com.xnx3.StringUtil;
import com.xnx3.SystemUtil;
import com.xnx3.writecode.bean.FieldBean;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.bean.Template;
import com.xnx3.net.HttpResponse;
import com.xnx3.net.HttpUtil;
import com.xnx3.HumpUtil;

/**
 * 自动生成实体类代码
 * @author 管雷鸣
 */
public class TemplateUtil {
//	public String packageName; 	//{java.package} 生成的实体类是在哪个包，格式如 com.xnx3.j2ee.entity
	public String templateText;	//entity.template 模板内容
	public Template template;	//自定义的模板相关属性
	
	public TemplateUtil() {
		// TODO Auto-generated constructor stub
	}
	public TemplateUtil(String templateText, Template template) {
		this.templateText = templateText;
		this.template = template;
	}
	
	
	/**
	 * 设置 entity.template 模板内容
	 * @param templateText entity.template 模板的内容文本
	 */
	public void setTemplateText(String templateText) {
		this.templateText = templateText;
	}

	public String getTemplateText() {
		return templateText;
	}

	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	/**
	 * 传入数据表的名字，输出这个数据表的实体类内容
	 * @param TableBean 数据表的结构信息
	 * @return 这个数据表的实体类内容
	 */
	public String template(TableBean tableBean){
		/*** 模板中的变量替换 ***/
		if(this.templateText == null) {
			//为空，则拉取cdn节点的
			HttpResponse hr = new HttpUtil().get("http://res.zvo.cn/writecode/template/entity.template");
			if(hr.getCode() != 200) {
				System.err.println("获取云端 entity.template 失败，http code:"+hr.getCode());
				return "获取云端 entity.template 失败，http code:"+hr.getCode();
			}
			templateText = hr.getContent();
		}
		
		//全局方面
		templateText = StringUtil.replaceAll(templateText, "{java.package}", this.template.javaPackage);
		templateText = StringUtil.replaceAll(templateText, "{database.table.comment}", tableBean.getComment());
		templateText = StringUtil.replaceAll(templateText, "{database.table.name}", tableBean.getName());
		templateText = StringUtil.replaceAll(templateText, "{database.table.name.hump.upper}", HumpUtil.upper(tableBean.getName()));
		templateText = StringUtil.replaceAll(templateText, "{database.table.name.hump.lower}", HumpUtil.lower(tableBean.getName()));
		templateText = StringUtil.replaceAll(templateText, "{project.path.absolute}", SystemUtil.getCurrentDir());
		
		//{codeblock.field}
		if(templateText.indexOf("{codeblock.field}") > -1) {
			String fieldTemplate = StringUtil.subString(templateText, "{codeblock.field}", "{/codeblock.field}", 2);  //模板
			//如果第一个字符是换行符，那就删掉
			if(fieldTemplate.indexOf("\n") == 0) {
				fieldTemplate = fieldTemplate.substring(1, fieldTemplate.length());
			}
			//如果最后一个字符是tab缩进，那也删掉
			if(fieldTemplate.lastIndexOf("\t") == fieldTemplate.length()-1) {
				fieldTemplate = fieldTemplate.substring(0, fieldTemplate.length()-1);
			}
			StringBuffer fieldStringBuffer = new StringBuffer();	//所有字段属性的集合字符串
			for (int i = 0; tableBean.getFieldList() != null && i < tableBean.getFieldList().size(); i++) {
				FieldBean field = tableBean.getFieldList().get(i);	//具体的表中的某个字段
				
				String fieldString = StringUtil.replaceAll(fieldTemplate, "{java.field.datatype}", DataTypeUtil.databaseToJava(field.getDatatype()));
				fieldString = StringUtil.replaceAll(fieldString, "{database.table.field.name.hump.lower}", HumpUtil.lower(field.getName()));
				fieldString = StringUtil.replaceAll(fieldString, "{database.table.field.comment}", field.getComment());
				fieldStringBuffer.append(fieldString);
			}
			//替换
			templateText = StringUtil.replaceAll(templateText, fieldTemplate, fieldStringBuffer.toString());
			//去掉 field-start、field-end
			templateText = StringUtil.replaceAll(templateText, "[\\t]+{codeblock.field}[\\n]+", "");
			templateText = StringUtil.replaceAll(templateText, "{/codeblock.field}[\\n]+", "");
		}
		
		// {codeblock.method}
		if(templateText.indexOf("{codeblock.method}") > -1) {
			String methodTemplate = StringUtil.subString(templateText, "{codeblock.method}", "{/codeblock.method}", 2); //模板
			StringBuffer methodStringBuffer = new StringBuffer();	//所有字段属性的集合字符串
			for (int i = 0; tableBean.getFieldList() != null && i < tableBean.getFieldList().size(); i++) {
				FieldBean field = tableBean.getFieldList().get(i);	//具体的表中的某个字段
				
				String methodString = StringUtil.replaceAll(methodTemplate, "{java.field.datatype}", DataTypeUtil.databaseToJava(field.getDatatype()));
				methodString = StringUtil.replaceAll(methodString, "{database.table.field.name.hump.lower}", HumpUtil.lower(field.getName()));
				methodString = StringUtil.replaceAll(methodString, "{database.table.field.name.hump.upper}", HumpUtil.upper(field.getName()));
				methodString = StringUtil.replaceAll(methodString, "{database.table.field.name}", field.getName());
				methodString = StringUtil.replaceAll(methodString, "{database.table.field.datatype}", field.getDatatype());
				methodString = StringUtil.replaceAll(methodString, "{database.table.field.length}", field.getLength());
				methodString = StringUtil.replaceAll(methodString, "{database.table.field.collate}", field.getCollate());
				methodString = StringUtil.replaceAll(methodString, "{database.table.field.comment}", field.getComment());
				methodString = StringUtil.replaceAll(methodString, "{database.table.field.default}", field.getDefaultvalue());
				if(field.getIfAnnotationId().length() == 0) {
					methodString = StringUtil.replaceAll(methodString, "[\\t]+{if.java.annotation.id}[\\n]+", "");	//没有则移除这一行
				}else {
					methodString = StringUtil.replaceAll(methodString, "{if.java.annotation.id}", field.getIfAnnotationId());				
				}
				if(field.getIfAnnotationGeneratedValue().length() == 0) {
					methodString = StringUtil.replaceAll(methodString, "[\\t]+{if.java.annotation.generatedvalue}[\\n]+", "");	//没有则移除这一行
				}else {
					methodString = StringUtil.replaceAll(methodString, "{if.java.annotation.generatedvalue}", field.getIfAnnotationGeneratedValue());
				}
				if(field.getDefaultvalue() == null || field.getDefaultvalue().equalsIgnoreCase("null")) {
					methodString = StringUtil.replaceAll(methodString, "{if.database.table.field.default}", "");
				}else {
					methodString = StringUtil.replaceAll(methodString, "{if.database.table.field.default}", (field.getDefaultvalue() == null || field.getDefaultvalue().equalsIgnoreCase("null")) ? "":"default '"+field.getDefaultvalue()+"'");
				}
				
				methodStringBuffer.append(methodString);
			}
			//替换
			templateText = replaceAll(templateText, methodTemplate, methodStringBuffer.toString());
			//去掉 field-start、field-end
			templateText = StringUtil.replaceAll(templateText, "[\\t]+{codeblock.method}[\\n]+", "");
			templateText = StringUtil.replaceAll(templateText, "[\\t]+{/codeblock.method}[\\n]+", "");
		}
		
		/**** tostring ****/
		if(templateText.indexOf("{java.tostring}") > -1) {
			StringBuffer tostring = new StringBuffer();
			tostring.append("{");
			for (int i = 0; tableBean.getFieldList() != null && i < tableBean.getFieldList().size(); i++) {
				FieldBean field = tableBean.getFieldList().get(i);	//具体的表中的某个字段
				if(tostring.length() > 2) {
					tostring.append(", ");
				}
				tostring.append(HumpUtil.lower(field.getName())+" : \"+this."+HumpUtil.lower(field.getName())+"+\"");
			}
			tostring.append("}");
			templateText = StringUtil.replaceAll(templateText, "{java.tostring}", tostring.toString());
		}
		
		return templateText;
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

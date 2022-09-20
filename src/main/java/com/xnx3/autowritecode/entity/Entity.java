package com.xnx3.autowritecode.entity;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.autowritecode.bean.FieldBean;
import com.xnx3.autowritecode.bean.TableBean;
import com.xnx3.autowritecode.util.DataTypeUtil;
import com.xnx3.HumpUtil;

/**
 * 自动生成实体类代码
 * @author 管雷鸣
 */
public class Entity {
	public String packageName; 	//{java.package} 生成的实体类是在哪个包，格式如 com.xnx3.j2ee.entity
	public String template;		//entity.template 模板内容
	
	/**
	 * 设置 entity.template 模板内容
	 * @param template entity.template 模板的内容文本
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
			template = FileUtil.read("/Users/apple/git/autowritecode/src/main/java/com/xnx3/autowritecode/entity/entity.template");
		}
		
		//全局方面
		template = StringUtil.replaceAll(template, "{java.package}", this.packageName);
		template = StringUtil.replaceAll(template, "{database.table.comment}", tableBean.getComment());
		template = StringUtil.replaceAll(template, "{database.table.name}", tableBean.getName());
		template = StringUtil.replaceAll(template, "{database.table.name.hump.upper}", HumpUtil.upper(tableBean.getName()));
		
		//{codeblock.field}
		String fieldTemplate = StringUtil.subString(template, "{codeblock.field}", "{/codeblock.field}", 2);  //模板
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
		template = StringUtil.replaceAll(template, fieldTemplate, fieldStringBuffer.toString());
		//去掉 field-start、field-end
		template = StringUtil.replaceAll(template, "[\\t]+{codeblock.field}[\\n]+", "");
		template = StringUtil.replaceAll(template, "{/codeblock.field}[\\n]+", "");
		
		// {codeblock.method}
		String methodTemplate = StringUtil.subString(template, "{codeblock.method}", "{/codeblock.method}", 2); //模板
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
		template = replaceAll(template, methodTemplate, methodStringBuffer.toString());
		//去掉 field-start、field-end
		template = StringUtil.replaceAll(template, "[\\t]+{codeblock.method}[\\n]+", "");
		template = StringUtil.replaceAll(template, "[\\t]+{/codeblock.method}[\\n]+", "");
		
		/**** tostring ****/
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
		template = StringUtil.replaceAll(template, "{java.tostring}", tostring.toString());
		
		return template;
	}
	
	public static void main(String[] args) {
		Entity entity = new Entity();
		entity.packageName = "com.xnx3";
//		entity.writeCode("system");
//		System.exit(0);
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

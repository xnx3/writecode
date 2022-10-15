package com.xnx3.writecode.util;

import com.xnx3.SystemUtil;
import com.xnx3.writecode.bean.FieldBean;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.interfaces.TemplateTagExtendInterface;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.xnx3.HumpUtil;

/**
 * 自动生成实体类代码
 * @author 管雷鸣
 */
public class TemplateUtil {
//	public String packageName; 	//{java.package} 生成的实体类是在哪个包，格式如 com.xnx3.j2ee.entity
	public String templateText;	//entity.template 模板内容
	public Template template;	//自定义的模板相关属性
	public TemplateTagExtendInterface templateTagExtend; //针对 模板中的变量标签的扩展，增加自己的自定义标签。如果为null则是没有设置扩展，此不生效
	
	public TemplateUtil() {
		// TODO Auto-generated constructor stub
	}
	public TemplateUtil(String templateText, Template template) {
		this.templateText = templateText;
		this.template = template;
	}
	
	/**
	 * 针对 模板中的变量标签的扩展，增加自己的自定义标签。如果为null则是没有设置扩展，此不生效
	 * @return
	 */
	public TemplateTagExtendInterface getTemplateTagExtend() {
		return templateTagExtend;
	}
	/**
	 * 针对 模板中的变量标签的扩展，增加自己的自定义标签。如果为null则是没有设置扩展，此不生效
	 * @param templateTagExtend
	 */
	public void setTemplateTagExtend(TemplateTagExtendInterface templateTagExtend) {
		this.templateTagExtend = templateTagExtend;
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
		if(this.templateTagExtend == null && template.getTemplateTagExtend() != null) {
			this.templateTagExtend = template.getTemplateTagExtend();
		}
	}
	/**
	 * 传入数据表的名字，输出这个数据表的实体类内容
	 * @param TableBean 数据表的结构信息
	 * @return 这个数据表的实体类内容
	 */
	public String template(TableBean tableBean){
		//全局方面
		if(templateText.indexOf("{") < 0) {
			//没有，那就不替换了
			return templateText;
		}
		String templateText = new String(this.templateText);
		templateText = replaceAll(templateText, "{java.package}", this.template.javaPackage);
		templateText = replaceAll(templateText, "{database.table.comment}", tableBean.getComment());
		templateText = replaceAll(templateText, "{database.table.name}", tableBean.getName());
		templateText = replaceAll(templateText, "{database.table.name.hump.upper}", HumpUtil.upper(tableBean.getName()));
		templateText = replaceAll(templateText, "{database.table.name.hump.lower}", HumpUtil.lower(tableBean.getName()));
		templateText = replaceAll(templateText, "{project.path.absolute}", SystemUtil.getCurrentDir().replaceAll("\\\\", "\\\\\\\\"));
		templateText = replaceAll(templateText, "{project.url.path}",this.template.getProjectUrlPath());
		
		
		//{codeblock.field}
		templateText = codeblockField_tag_Replace(templateText, tableBean.getFieldList(), "codeblock.field");

		//{codeblock.field.edit}
		templateText = codeblockField_tag_Replace(templateText, tableBean.getFieldEditList(), "codeblock.field.edit");

		//{codeblock.field.list.search}
		templateText = codeblockField_tag_Replace(templateText, tableBean.getFieldListSearchList(), "codeblock.field.list.search");
		
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
			templateText = replaceAll(templateText, "{java.tostring}", tostring.toString());
		}
		
		if(this.templateTagExtend != null) {
			templateText = this.templateTagExtend.appendTag(templateText, this, tableBean);
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
		
		String s[] = {"?","(",")","{","}","*","."}; 
		for (int i = 0; i < s.length; i++) {
			regex = regex.replaceAll("\\"+s[i], "\\\\"+s[i]);
		}
		
//		while(text.indexOf(regex) > -1) {
//			text = text.replace(regex, replacement);
//		}
		
		text = text.replaceAll(regex, replacement);
		
		return text;
	}
	
	/**
	 * codeblock.field 相关标签的替换
	 * @param templateText 替换的字符串，源
	 * @param tag 传入如 codeblock.field.edit
	 * @return
	 */
	public static String codeblockField_tag_Replace(String templateText, List<FieldBean> fileBeanList, String tag) {
		if(fileBeanList == null) {
			return templateText;
		}
		
		//如果 {codeblock.field} 存在，则需要替换
		if(templateText.indexOf("{"+tag+"") > -1){
//			\\{codeblock\\.field\\}
			Pattern p = Pattern.compile("\\{"+tag.replaceAll("\\.", "\\\\.")+"\\}([\\s|\\S]*?)\\{\\/"+tag.replaceAll("\\.", "\\\\.")+"\\}");
			Matcher m = p.matcher(templateText);
			while (m.find()) {
				String fieldTemplate = m.group(1);	//全局变量的name
				
				//如果第一个字符是换行符，那就删掉
				if(fieldTemplate.indexOf("\n") == 0) {
					fieldTemplate = fieldTemplate.substring(1, fieldTemplate.length());
				}
				//如果最后一个字符是tab缩进，那也删掉
				if(fieldTemplate.lastIndexOf("\t") == fieldTemplate.length()-1) {
					fieldTemplate = fieldTemplate.substring(0, fieldTemplate.length()-1);
				}
				StringBuffer fieldStringBuffer = new StringBuffer();	//所有字段属性的集合字符串
				for (int i = 0; fileBeanList != null && i < fileBeanList.size(); i++) {
					FieldBean field = fileBeanList.get(i);	//具体的表中的某个字段
					
					String fieldString = replaceAll(fieldTemplate, "{java.field.datatype}", DataTypeUtil.databaseToJava(field.getDatatype()));
					fieldString = replaceAll(fieldString, "{database.table.field.name.hump.lower}", HumpUtil.lower(field.getName()));
					fieldString = replaceAll(fieldString, "{database.table.field.name.hump.upper}", HumpUtil.upper(field.getName()));
					fieldString = replaceAll(fieldString, "{database.table.field.comment}", field.getComment());
					fieldString = replaceAll(fieldString, "{database.table.field.datatype}", field.getDatatype());
					fieldString = replaceAll(fieldString, "{database.table.field.datatype.java}", DataTypeUtil.databaseToJava(field.getDatatype()));
					fieldString = replaceAll(fieldString, "{java.field.datatype}", DataTypeUtil.databaseToJava(field.getDatatype()));
					fieldString = replaceAll(fieldString, "{database.table.field.name}", field.getName());
					fieldString = replaceAll(fieldString, "{database.table.field.length}", field.getLength());
					fieldString = replaceAll(fieldString, "{database.table.field.collate}", field.getCollate());
					fieldString = replaceAll(fieldString, "{database.table.field.default}", field.getDefaultvalue());
					if(field.getIfAnnotationId().length() == 0) {
						fieldString = replaceAll(fieldString, "[\\t]+{if.java.annotation.id}[\\r|\\n]+", "");	//没有则移除这一行
						fieldString = replaceAll(fieldString, "{if.java.annotation.id}[\\r|\\n]+", "");	//没有则移除这一行
						fieldString = replaceAll(fieldString, "{if.java.annotation.id}", "");	//没有则移除这一行
					}else {
						fieldString = replaceAll(fieldString, "{if.java.annotation.id}", field.getIfAnnotationId());				
					}
					if(field.getIfAnnotationGeneratedValue().length() == 0) {
						fieldString = replaceAll(fieldString, "[\\t]+{if.java.annotation.generatedvalue}[\\r|\\n]+", "");	//没有则移除这一行
						fieldString = replaceAll(fieldString, "{if.java.annotation.generatedvalue}[\\r|\\n]+", "");	//没有则移除这一行
						fieldString = replaceAll(fieldString, "{if.java.annotation.generatedvalue}", "");	//没有则移除这一行
					}else {
						fieldString = replaceAll(fieldString, "{if.java.annotation.generatedvalue}", field.getIfAnnotationGeneratedValue());
					}
					if(field.getDefaultvalue() == null || field.getDefaultvalue().equalsIgnoreCase("null")) {
						fieldString = replaceAll(fieldString, "{if.database.table.field.default}", "");
					}else {
						fieldString = replaceAll(fieldString, "{if.database.table.field.default}", (field.getDefaultvalue() == null || field.getDefaultvalue().equalsIgnoreCase("null")) ? "":"default '"+field.getDefaultvalue()+"'");
					}
					
					fieldStringBuffer.append(fieldString);
				}
				
				templateText = templateText.replace("{"+tag+"}"+m.group(1)+"{/"+tag+"}", fieldStringBuffer.toString());
			}
		}
		return templateText;
	}
}

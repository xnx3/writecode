package com.xnx3.writecode.util;

import com.xnx3.SystemUtil;
import com.xnx3.writecode.bean.FieldBean;
import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.interfaces.TemplateTagExtendInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.xnx3.HumpUtil;
import com.xnx3.StringUtil;

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
		
		
		//{foreach.field}
		templateText = foreachField_tag_Replace(templateText, tableBean.getFieldList(), "foreach.field");

		//{foreach.field.edit}
		templateText = foreachField_tag_Replace(templateText, tableBean.getFieldEditList(), "foreach.field.edit");

		//{foreach.field.list.search}
		templateText = foreachField_tag_Replace(templateText, tableBean.getFieldListSearchList(), "foreach.field.list.search");
		
		//{foreach.field.list.table}
		templateText = foreachField_tag_Replace(templateText, tableBean.getFieldListTableList(), "foreach.field.list.table");
				
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
		
		/**** {javascript} ****/
		if(templateText.indexOf("{javascript}") > -1) {
			//如果 {javascript} 存在，则需要替换
				
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			
			Pattern p = Pattern.compile("\\{javascript\\}([\\s|\\S]*?)\\{\\/javascript\\}");
			Matcher m = p.matcher(templateText);
			while (m.find()) {
				String jsTemplate = m.group(1);	//全局变量的name
				//System.out.println("jsTemplate:"+jsTemplate);
				
				//如果第一个字符是换行符，那就删掉
				if(jsTemplate.indexOf("\n") == 0) {
					jsTemplate = jsTemplate.substring(1, jsTemplate.length());
				}
				//如果最后一个字符是tab缩进，那也删掉
				if(jsTemplate.lastIndexOf("\t") == jsTemplate.length()-1) {
					jsTemplate = jsTemplate.substring(0, jsTemplate.length()-1);
				}
				
				
				String js = "function writecode(){ "+jsTemplate+" }";
				Invocable inv = (Invocable) engine;
				try {
					engine.eval(js);
					Object result = (Object) inv.invokeFunction("writecode");
//					System.out.println("jsTemplate "+jsTemplate+" : " + result);
//					System.out.println("--{code.javascript}"+m.group(1)+"{/code.javascript}--");
					templateText = templateText.replace("{javascript}"+m.group(1)+"{/javascript}", result == null? "":(String)result);
				} catch (NoSuchMethodException | ScriptException e) {
					e.printStackTrace();
				}
			}
			
			
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
	 * foreach.field 相关标签的替换
	 * @param templateText 替换的字符串，源
	 * @param tag 传入如 foreach.field.edit
	 * @return
	 */
	public static String foreachField_tag_Replace(String templateText, List<FieldBean> fileBeanList, String tag) {
		if(fileBeanList == null) {
			return templateText;
		}
		
		//如果 {foreach.field} 存在，则需要替换
		if(templateText.indexOf("{"+tag+"") > -1){
//			\\{foreach\\.field\\}
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
				//模板中是否有循环遍历注释常量输出. true为有
				boolean foreachCommentConst = false;	
				if(fieldTemplate.indexOf("{foreach.field.comment.const}") > -1) {
					foreachCommentConst = true;
				}
				
				StringBuffer fieldStringBuffer = new StringBuffer();	//所有字段属性的集合字符串
				for (int i = 0; fileBeanList != null && i < fileBeanList.size(); i++) {
					FieldBean field = fileBeanList.get(i);	//具体的表中的某个字段
					
					String fieldString = replaceAll(fieldTemplate, "{java.field.datatype}", DataTypeUtil.databaseToJava(field.getDatatype()));
					fieldString = replaceAll(fieldString, "{database.table.field.name.hump.lower}", HumpUtil.lower(field.getName()));
					fieldString = replaceAll(fieldString, "{database.table.field.name.hump.upper}", HumpUtil.upper(field.getName()));
					fieldString = replaceAll(fieldString, "{database.table.field.comment}", field.getComment());
					fieldString = replaceAll(fieldString, "{database.table.field.comment.split}", fieldCommentSplit(field.getComment()));
					fieldString = replaceAll(fieldString, "{database.table.field.comment.ignore.const}", fieldCommentIgnoreConst(field.getComment()));
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
					if(foreachCommentConst) {
						Pattern pConst = Pattern.compile("\\{foreach\\.field\\.comment\\.const\\}([\\s|\\S]*?)\\{\\/foreach\\.field\\.comment\\.const\\}");
						Matcher mConst = pConst.matcher(fieldString);
						while (mConst.find()) {
							String commentConstTemplate = mConst.group(1);
							List<CommentConstBean> constList = getCommentConst(field.getComment());
							
							StringBuffer constStringBuffer = new StringBuffer();	//所有字段属性的集合字符串
							for(int c = 0; c<constList.size(); c++){
								CommentConstBean bean = constList.get(c);
								
								String str = new String(commentConstTemplate);
								str = replaceAll(str, "{database.table.field.comment.const.value}", bean.getValue());
								str = replaceAll(str, "{database.table.field.comment.const.explain}", bean.getExplain());
								constStringBuffer.append(str);
							}
							fieldString = fieldString.replace("{foreach.field.comment.const}"+mConst.group(1)+"{/foreach.field.comment.const}", constStringBuffer.toString());
						}
					}
					
					
					fieldStringBuffer.append(fieldString);
				}
				
				templateText = templateText.replace("{"+tag+"}"+m.group(1)+"{/"+tag+"}", fieldStringBuffer.toString());
			}
		}
		return templateText;
	}
	
	/**
	 * 针对 field.comment 的split截取
	 * @param fieldComment 
	 * @return 返回 ,，。前的文本
	 */
	public static String fieldCommentSplit(String fieldComment) {
		if(fieldComment == null) {
			return "";
		}
		return fieldComment.trim().split(",|，|。")[0];
	}
	
	/**
	 * 将字段注释中的变量标记去掉，如  性别，是男性还是女性。[1-男性, 2-女性]  会返回 性别，是男性还是女性。
	 * @param fieldComment
	 * @return
	 */
	public static String fieldCommentIgnoreConst(String fieldComment) {
		if(fieldComment == null) {
			return "";
		}
		
		//判断是否存在常量标识
		String str = StringUtil.subString(fieldComment, "[", "]");
		if(str == null || str.length() == 0) {
			return fieldComment;
		}
		
		String comment = new String(fieldComment);
		if(str.indexOf("-") > -1) {
			//应该是是了，把这个忽略掉
			comment = comment.replace("["+str+"]", "");
		}
		return comment;
	}
	
	public static void main(String[] args) {
		System.out.println(fieldCommentSplit("任命，公司名"));
		
	}
	
	/**
	 * 传入数据表的某列的注释，获取注释中包含定义的常量及说明。
	 * @param comment 表的注释，传入如 用户性别 [1-男性, 2-女性]
	 * @return list 如果注释中没有这方面的标记，那list.size 便是 0，list不会为null
	 */
	public static List<CommentConstBean> getCommentConst(String comment) {
		List<CommentConstBean> list = new ArrayList<CommentConstBean>();
		
		//取得 [] 内的值，取得如 1-xxxxxx, 2-xxxxx2
		String str = StringUtil.subString(comment, "[", "]");
		if(str == null) {
			return list;
		}
		String[] items = str.split(",");
		
		for (int i = 0; i < items.length; i++) {
			String item = items[i];
			String[] values = item.split("-");
			if(values.length != 2) {
				continue;
			}
			
			String value = values[0].trim(); //要往数据表中存的值
			String explain = values[1].trim();	//值的说明，这个值表示什么
			if(value.length() == 0) {
				continue;
			}
			if(explain.length() == 0) {
				explain = value;
			}
			CommentConstBean constBean = new CommentConstBean();
			constBean.setValue(value);
			constBean.setExplain(explain);
			//System.out.println(value+" - "+explain);
			list.add(constBean);
		}
		return list;
	}
	
}
/**
 * 字段备注中所定义的常量信息
 * @author 管雷鸣
 */
class CommentConstBean{
	private String value; 	//值，王数据表中存储的值
	private String explain;	//值的说明，描述
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}

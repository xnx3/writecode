package com.xnx3.autowritecode.entity;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.autowritecode.entity.bean.FieldBean;
import com.xnx3.autowritecode.entity.bean.TableBean;
import com.xnx3.autowritecode.entity.util.DataBaseUtil;
import com.xnx3.autowritecode.entity.util.DataTypeUtil;
import com.xnx3.autowritecode.entity.util.HumpUtil;

/**
 * 自动生成实体类代码
 * @author 管雷鸣
 */
public class Entity {
	public String packageName; //{java.package} 生成的实体类是在哪个包，格式如 com.xnx3.j2ee.entity
	
	public void writeCode(String tableName){
		
		/**** 加载 entity.template 模板 ****/
		String template = FileUtil.read("/Users/apple/git/autowritecode/src/main/java/com/xnx3/autowritecode/entity/entity.template");
		
		/*** 取数据表信息 ***/
		TableBean tableBean = DataBaseUtil.table(tableName);
		
		
		/*** 模板中的变量替换 ***/
		
		//全局方面
		template = StringUtil.replaceAll(template, "{java.package}", this.packageName);
		template = StringUtil.replaceAll(template, "{database.table.comment}", tableBean.getComment());
		template = StringUtil.replaceAll(template, "{database.table.name}", tableBean.getName());
		template = StringUtil.replaceAll(template, "{database.table.name.hump.upper}", HumpUtil.upper(tableBean.getName()));
		
		//field-start、field-end
		String fieldTemplate = StringUtil.subString(template, "//field-start", "//field-end", 2);  //模板
		StringBuffer fieldStringBuffer = new StringBuffer();	//所有字段属性的集合字符串
		for (int i = 0; tableBean.getFieldList() != null && i < tableBean.getFieldList().size(); i++) {
			FieldBean field = tableBean.getFieldList().get(i);	//具体的表中的某个字段
			
			String fieldString = StringUtil.replaceAll(fieldTemplate, "{java.field.datatype}", DataTypeUtil.databaseToJava(field.getDatatype()));
			fieldString = StringUtil.replaceAll(fieldTemplate, "{database.table.field.name.hump.lower}", HumpUtil.lower(field.getName()));
			fieldString = StringUtil.replaceAll(fieldTemplate, "{database.table.field.comment}", field.getComment());
			fieldStringBuffer.append(fieldString);
		}
		//替换
		template = StringUtil.replaceAll(template, fieldTemplate, fieldStringBuffer.toString());
		//去掉 field-start、field-end
		template = StringUtil.replaceAll(template, "//field-start\\s*", "");
		template = StringUtil.replaceAll(template, "//field-end\\s*", "");
		
		//get-set-method-start、get-set-method-end
		String methodTemplate = StringUtil.subString(template, "//get-set-method-start", "//get-set-method-end", 2); //模板
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
			methodString = StringUtil.replaceAll(methodString, "{if.java.annotation.id}", field.getIfAnnotationId());
			methodString = StringUtil.replaceAll(methodString, "{if.java.annotation.generatedvalue}", field.getIfAnnotationGeneratedValue());
			methodStringBuffer.append(methodString);
		}
		//替换
		template = StringUtil.replaceAll(template, methodTemplate, methodStringBuffer.toString());
		//去掉 field-start、field-end
		template = StringUtil.replaceAll(template, "//get-set-method-start\\s*", "");
		template = StringUtil.replaceAll(template, "//get-set-method-end\\s*", "");
		
		/**** tostring ****/
		StringBuffer tostring = new StringBuffer();
		tostring.append("{");
		for (int i = 0; tableBean.getFieldList() != null && i < tableBean.getFieldList().size(); i++) {
			FieldBean field = tableBean.getFieldList().get(i);	//具体的表中的某个字段
			if(tostring.length() > 2) {
				tostring.append(",");
			}
			tostring.append("\""+HumpUtil.lower(field.getName())+" : this."+HumpUtil.lower(field.getName())+"\"");
		}
		tostring.append("}");
		template = StringUtil.replaceAll(template, "{java.tostring}", tostring.toString());
		
		System.out.println(template);
	}
	
	public static void main(String[] args) {
		Entity entity = new Entity();
		entity.packageName = "com.xnx3";
		entity.writeCode("user");
	}
	
}

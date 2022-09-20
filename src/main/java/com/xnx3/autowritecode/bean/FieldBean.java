package com.xnx3.autowritecode.bean;

/**
 * 数据表中某个字段的Bean
 * @author 管雷鸣
 */
public class FieldBean {
	private String name;			//{database.table.field.name} 数据表中，字段名。如 role_id
	private String comment;			//{database.table.field.comment} 数据表中，字段的注释
	private String defaultvalue;	//{database.table.field.default} 数据表中，字段的默认值
	private String datatype;		//{database.table.field.datatype} 数据表中，字段的数据类型，如 int、char、varchar 等
	private String length;			//{database.table.field.length} 数据表中，字段数据的长度，比如类型是int、chat等类型，这里输出格式为 11 ,比如类型是float型，这里输出格式为 3,6
	private String collate;			//{database.table.field.collate} 数据表中，字段的编码格式，针对字符串类型，防止乱码。如果数据表中本身是int型，并没有设置编码格式，那这个则没有任何输出。如果数据字段是char，设置了字符编码，那会输出如 COLLATE utf8mb4_unicode_ci
	private String ifAnnotationId;	//{if.java.annotation.id} 如果当前字段是主键，则输出Java注解 @Id
	private String ifAnnotationGeneratedValue;	//{if.java.annotation.generatedvalue} 如果当前字段比如是自增属性，则输出Java注解 @GeneratedValue(strategy = IDENTITY)
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getCollate() {
		return collate;
	}
	public void setCollate(String collate) {
		this.collate = collate;
	}
	public String getIfAnnotationId() {
		return ifAnnotationId;
	}
	public void setIfAnnotationId(String ifAnnotationId) {
		this.ifAnnotationId = ifAnnotationId;
	}
	public String getIfAnnotationGeneratedValue() {
		return ifAnnotationGeneratedValue;
	}
	public void setIfAnnotationGeneratedValue(String ifAnnotationGeneratedValue) {
		this.ifAnnotationGeneratedValue = ifAnnotationGeneratedValue;
	}
	@Override
	public String toString() {
		return "FieldBean [name=" + name + ", comment=" + comment + ", defaultvalue=" + defaultvalue + ", datatype="
				+ datatype + ", length=" + length + ", collate=" + collate + ", ifAnnotationId=" + ifAnnotationId
				+ ", ifAnnotationGeneratedValue=" + ifAnnotationGeneratedValue + "]";
	}
	
}

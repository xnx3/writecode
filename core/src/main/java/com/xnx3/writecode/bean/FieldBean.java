package com.xnx3.writecode.bean;

/**
 * 数据表中某个字段的Bean
 * @author 管雷鸣
 */
public class FieldBean {
	private String name;			//{database.table.field.name} 数据表中，字段名。如 role_id
	private String comment;			//{database.table.field.comment} 数据表中，字段的注释。如果字段没有设置注释，那么将返回字段名字
	private String defaultvalue;	//{database.table.field.default} 数据表中，字段的默认值。如果数据表中字段本身并没有设置，为null，那这里就会输出空字符串
	private String datatype;		//{database.table.field.datatype} 数据表中，字段的数据类型，如 int、char、varchar 等
	private String length;			//{database.table.field.length} 数据表中，字段数据的长度，比如类型是int、chat等类型，这里输出格式为 11 ,比如类型是float型，这里输出格式为 3,6
	private String collate;			//{database.table.field.collate} 数据表中，字段的编码格式，针对字符串类型，防止乱码。如果数据表中本身是int型，并没有设置编码格式，那这个则没有任何输出。如果数据字段是char，设置了字符编码，那会输出如 COLLATE utf8mb4_unicode_ci
	private boolean primaryKey;		//如果当前字段是主键，为true
	private boolean autoIncrement;	//如果当前字段是自增属性，则为true
	
	public FieldBean() {
		this.defaultvalue = "";
		this.primaryKey = false;
		this.autoIncrement = false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		if(this.comment == null || this.comment.length() == 0) {
			return this.name;
		}
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDefaultvalue() {
		if(this.defaultvalue == null) {
			return "";
		}
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
	
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	@Override
	public String toString() {
		return "FieldBean [name=" + name + ", comment=" + comment + ", defaultvalue=" + defaultvalue + ", datatype="
				+ datatype + ", length=" + length + ", collate=" + collate + ", primaryKey=" + primaryKey
				+ ", autoIncrement=" + autoIncrement + "]";
	}
	
}

package com.xnx3.writecode.bean;

import java.util.List;

/**
 * 数据表的Bean
 * @author 管雷鸣
 */
public class TableBean {
	private String name;			//{database.table.name} 数据表的名字，如 user
	private String comment;			//{database.table.comment} 数据表的备注，这个表是干什么的，如 用户表
	private List<FieldBean> fieldList;	 //字段列表
	
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
	public List<FieldBean> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<FieldBean> fieldList) {
		this.fieldList = fieldList;
	}
	@Override
	public String toString() {
		return "TableBean [name=" + name + ", comment=" + comment + ", fieldList=" + fieldList + "]";
	}
	
}

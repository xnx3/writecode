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
	
	private List<FieldBean> fieldEditList;	//新增、编辑页面中，给用户可编辑的字段列表。这个一般就是从ui自定义选择的
	private List<FieldBean> fieldListSearchList;	//列表页面中，提供给用户搜索的字段
	private List<FieldBean> fieldListTableList;		//列表页面中，给用户列表展示出来的字段
	
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
	
	public List<FieldBean> getFieldEditList() {
		if(this.fieldEditList == null) {
			this.fieldEditList = this.fieldList;
		}
		return fieldEditList;
	}
	public void setFieldEditList(List<FieldBean> fieldEditList) {
		this.fieldEditList = fieldEditList;
	}
	public List<FieldBean> getFieldListSearchList() {
		if(this.fieldListSearchList == null) {
			this.fieldListSearchList = this.fieldList;
		}
		return fieldListSearchList;
	}
	public void setFieldListSearchList(List<FieldBean> fieldListSearchList) {
		this.fieldListSearchList = fieldListSearchList;
	}
	public List<FieldBean> getFieldListTableList() {
		if(this.fieldListTableList == null) {
			this.fieldListTableList = this.fieldList;
		}
		return fieldListTableList;
	}
	public void setFieldListTableList(List<FieldBean> fieldListTableList) {
		this.fieldListTableList = fieldListTableList;
	}
	@Override
	public String toString() {
		return "TableBean [name=" + name + ", comment=" + comment + ", fieldList=" + fieldList + ", fieldEditList="
				+ fieldEditList + ", fieldListSearchList=" + fieldListSearchList + ", fieldListTableList="
				+ fieldListTableList + "]";
	}
	
	
}

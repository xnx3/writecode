package com.xnx3.writecode.bean;

/**
 * java的数据类型
 * @author 管雷鸣
 *
 */
public class DataTypeBean {
	private String objectName; //对象类型，如 Integer
	private String basicName;	//基础类型，如 int
	
	public DataTypeBean(String objectName, String basicName) {
		this.objectName = objectName;
		this.basicName = basicName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getBasicName() {
		return basicName;
	}

	public void setBasicName(String basicName) {
		this.basicName = basicName;
	}
	
}

package com.xnx3.writecode.bean;

import java.util.List;

import com.xnx3.writecode.interfaces.TemplateTagExtendInterface;

/**
 * 
 * 生成代码及模板相关
 * @author 管雷鸣
 */
public class Template {
	//要生成的Java文件的包名字。如果要写出的文件是Java文件，此项可以设置，不设置默认就是执行类所在的包名
	//返回格式如 com.xnx3.j2ee.entity
	public String javaPackage;
	
	//写出文件要保存的绝对路径，要保存到哪。如果此项不设置，那默认是保存到 {@link #javaPackage(List)} 的包中
	//格式如 /Users/apple/git/wangmarket_shop/src/main/java/com/xnx3/wangmarket/shop/core/entity
	public String writeFileAbsolutePath;
	
	//template 模板文件的名字
	//格式如 entity.template
	public String templateFileName;
	//template 模板文件所在的绝对路径，格式如 /User/apple/template/
	public String templateFileAbsolutePath;
	
	//自动写出的文件的名字。支持替换标签
	//格式如 UserController.java 或 {database.table.name.hump.upper}Controller.java
	public String writeFileName;
	
	//针对 模板中的变量标签的扩展，增加自己的自定义标签。如果为null则是没有设置扩展，此不生效
	public TemplateTagExtendInterface templateTagExtend; 
	
	public Template() {
	}
	
	/**
	 * 克隆，本对象克隆一份出来
	 * @return
	 */
	public Template clone() {
		Template template = new Template();
		
		if(this.getJavaPackage() != null) {
			template.javaPackage = new String(this.getJavaPackage());
		}
		if(this.getTemplateFileAbsolutePath() != null) {
			template.templateFileAbsolutePath = new String(this.getTemplateFileAbsolutePath());
		}
		if(this.getTemplateFileName() != null) {
			template.templateFileName = new String(this.getTemplateFileName());
		}
		template.templateTagExtend = this.getTemplateTagExtend();
		if(this.getWriteFileAbsolutePath() != null) {
			template.writeFileAbsolutePath = new String(this.getWriteFileAbsolutePath());
		}
		if(this.getWriteFileName() != null) {
			template.writeFileName = new String(this.getWriteFileName());
		}
		
		return template;
	}
	
	/**
	 * 要生成的Java文件的包名字。如果要写出的文件是Java文件，此项可以设置，不设置默认就是执行类所在的包名
	 * @return 返回格式如 com.xnx3.j2ee.entity
	 */
	public String getJavaPackage() {
		return javaPackage;
	}
	
	/**
	 * 要生成的Java文件的包名字。如果要写出的文件是Java文件，此项可以设置，不设置默认就是执行类所在的包名
	 * @param javaPackage 格式如 com.xnx3.j2ee.entity
	 */
	public void setJavaPackage(String javaPackage) {
		this.javaPackage = javaPackage;
	}

	/**
	 * 写出文件要保存的绝对路径，要保存到哪。如果此项不设置，那默认是保存到 {@link #javaPackage(List)} 的包中
	 * @return 格式如 /Users/apple/git/wangmarket_shop/src/main/java/com/xnx3/wangmarket/shop/core/entity
	 */
	public String getWriteFileAbsolutePath() {
		return writeFileAbsolutePath;
	}

	public void setWriteFileAbsolutePath(String writeFileAbsolutePath) {
		this.writeFileAbsolutePath = writeFileAbsolutePath;
	}

	/**
	 * template 模板文件的名字
	 * @return 格式如 entity.template
	 */
	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public String getWriteFileName() {
		return writeFileName;
	}

	public void setWriteFileName(String writeFileName) {
		this.writeFileName = writeFileName;
	}

	public String getTemplateFileAbsolutePath() {
		return templateFileAbsolutePath;
	}

	public void setTemplateFileAbsolutePath(String templateFileAbsolutePath) {
		this.templateFileAbsolutePath = templateFileAbsolutePath;
	}

	public TemplateTagExtendInterface getTemplateTagExtend() {
		return templateTagExtend;
	}

	public void setTemplateTagExtend(TemplateTagExtendInterface templateTagExtend) {
		this.templateTagExtend = templateTagExtend;
	}

	@Override
	public String toString() {
		return "Template [javaPackage=" + javaPackage + ", writeFileAbsolutePath=" + writeFileAbsolutePath
				+ ", templateFileName=" + templateFileName + ", writeFileName=" + writeFileName + "]";
	}
	
	
}

package com.xnx3.autowritecode;

import java.io.File;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.SystemUtil;
import com.xnx3.autowritecode.entity.Entity;
import com.xnx3.autowritecode.entity.bean.TableBean;
import com.xnx3.autowritecode.entity.util.HumpUtil;
import com.xnx3.autowritecode.interfaces.DataSourceInterface;
import com.xnx3.autowritecode.util.ClassUtil;

/**
 * 自动写代码。如写实体类、增删查改了
 * @author 管雷鸣
 *
 */
public class WriteCode {
	public String javaPackage = "xxxx"; //生成存放的包，格式如 com.xnx3.j2ee
	public DataSource dataSouece;	//数据源
	
	public WriteCode(DataSourceInterface dataSourceImpl) {
		StackTraceElement st = Thread.currentThread().getStackTrace()[2];
		javaPackage = StringUtil.subString(st.getClassName(), null, ".", 1); //得到如 com.xnx3.j2ee
		
		this.dataSouece = new DataSource(dataSourceImpl);
	}
	
	public String getEntityCode(String tableName) {
		TableBean tableBean = this.dataSouece.table("user");
		
		Entity entity = new Entity();
		entity.packageName = this.javaPackage;
		String code = entity.template(tableBean);
		
		return code;
	}
	public void writeEntityCode(String tableName) {
		System.out.println(ClassUtil.packageToFilePath(this.javaPackage)+HumpUtil.upper(tableName)+".java");
		FileUtil.write(ClassUtil.packageToFilePath(this.javaPackage)+HumpUtil.upper(tableName)+".java", getEntityCode(tableName));
	}
	
	public static void main(String[] args) {
		
		
	}
	
}

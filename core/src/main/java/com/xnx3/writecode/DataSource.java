package com.xnx3.writecode;

import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.interfaces.DataSourceInterface;

/**
 * 数据源相关操作
 * @author 管雷鸣
 *
 */
public class DataSource {
	public DataSourceInterface dataSourceInterface;	//当前使用的哪种数据源
	
	/**
	 * 初始化-设置数据源
	 * @param dataSourceInterface 使用哪种数据源
	 */
	public DataSource(DataSourceInterface dataSourceInterface) {
		this.dataSourceInterface = dataSourceInterface;
	}
	
	/**
	 * 获取指定数据表的信息
	 * @param name 表名，数据表的实际名称，传入如 user
	 * @return 获取到的该数据表的 {@link TableBean} 信息
	 */
	public TableBean table(String name) {
		//打开连接
		if(!dataSourceInterface.isconnect()) {
			dataSourceInterface.connect();
		}
		
		//取表信息
		TableBean tableBean = dataSourceInterface.getTable(name);
		
		//关闭连接
		dataSourceInterface.disconnect();
		
		return tableBean;
	}
	
}

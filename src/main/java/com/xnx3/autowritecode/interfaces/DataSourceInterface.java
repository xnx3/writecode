package com.xnx3.autowritecode.interfaces;

import java.util.List;
import com.xnx3.autowritecode.bean.TableBean;

/**
 * 数据源。定义当前操作的是Mysql、Sqlite、Oracle等
 * @author 管雷鸣
 *
 */
public interface DataSourceInterface {
	
	/**
	 * 建立连接，连接数据库。
	 */
	public void connect();
	
	/**
	 * 判断当前是否打开连接，连接状态。
	 * @return true已打开连接；false还未打开连接，会自动执行 {@link #connect()}进行打开连接
	 */
	public boolean isconnect();
	
	/**
	 * 获取当前数据库的表名字
	 * @return 数据表名字的集合。TableBean只需要返回 name、comment 两项数据即可
	 */
	public List<TableBean> getTableList();
	
	/**
	 * 获取指定数据表的信息
	 * @param name 数据表的实际名称，传入如 user
	 * @return 获取到的该数据表的 {@link TableBean} 信息
	 */
	public TableBean getTable(String name);
	
	/**
	 * 终端数据库连接。操作完毕后可执行此终端数据库连接
	 */
	public void disconnect();
	
}

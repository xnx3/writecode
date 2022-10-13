package com.xnx3.writecode.client;

import java.util.List;

import com.xnx3.writecode.bean.TableBean;
import com.xnx3.writecode.interfaces.DataSourceInterface;

/**
 * 全局
 * @author 管雷鸣
 */
public class Global {
	public static DataSourceInterface dataSource;	//数据源
	public static List<TableBean> tableList;	//生成的数据表list
	
}

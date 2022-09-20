package com.xnx3.autowritecode.interfaces;

import java.util.List;

/**
 * 界面中选择数据表之后，进行执行的事件
 * @author 管雷鸣
 *
 */
public interface SelectTableInterface {
	
	/**
	 * 界面上选择完成，将选择的数据库名字的列表传入
	 * @param list 选择的数据库名字列表
	 */
	public void selectFinish(List<String> list);
	
}

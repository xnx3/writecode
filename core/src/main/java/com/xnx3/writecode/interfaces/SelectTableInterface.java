package com.xnx3.writecode.interfaces;

import java.util.List;

import com.xnx3.writecode.bean.TableBean;

/**
 * 界面中选择数据表之后，进行执行的事件
 * @author 管雷鸣
 *
 */
public interface SelectTableInterface {
	
	/**
	 * 界面上选择完成，将选择的数据库名字的列表传入
	 * @param list 要进行打印的（选择的）数据表信息
	 */
	public void selectFinish(List<TableBean> list);
	
}

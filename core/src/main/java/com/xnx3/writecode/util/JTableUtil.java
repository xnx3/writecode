package com.xnx3.writecode.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

/**
 * swing JTable 组件相关
 * @author 管雷鸣
 *
 */
public class JTableUtil {
	
	/**
	 * 获取JTable中的数据并组合为map返回。其中map.key 以table中的某一列来定义
	 * @param table 要获取的信息的jtable
	 * @param keyIndex 返回的map.key 的值，是以jtable哪一列为准。注意jtable下标从0开始
	 * @return jtable的数据。
	 * 		<ul>
	 * 			<li>map.key : 以jtable的某一列的值赋予，具体那列，也就是传入的keyIndex</li>
	 * 			<li>map.value : 这一行的数据数组。当然数组下表跟jtable列的都是一直的</li>
	 * 		</ul>
	 */
	public static Map<String, Object[]> getTableData(JTable table, int keyIndex) {
		List<String> list = new ArrayList<String>();
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		
		int row = table.getRowCount();
		int column = table.getColumnCount();
		for (int i = 0; i < row; i++) {
			String mapKey = "";
			
			Object[] objs = new Object[column];
			for(int c = 0; c<column; c++) {
				Object obj = table.getValueAt(i, c);
				objs[c] = obj;
				
				if(c == keyIndex) {
					mapKey = obj.toString();
				}
			}
			
			map.put(mapKey, objs);
		}
		return map;
	}
}

package com;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import com.xnx3.StringUtil;
import com.xnx3.autowritecode.DataSource;
import com.xnx3.autowritecode.WriteCode;
import com.xnx3.autowritecode.bean.TableBean;
import com.xnx3.autowritecode.interfaces.DataSourceInterface;
import com.xnx3.autowritecode.interfaces.SelectTableInterface;
import com.xnx3.autowritecode.interfaces.impl.Mysql;
import com.xnx3.autowritecode.ui.SelectTableJframe;

public class Te {
	public static void main(String[] args) {
		
		WriteCode code = new WriteCode(new Mysql("local.mysql.leimingyun.com", 3306, "wangmarket", "root", "111111"));
//		code.writeEntityCode("system");
		code.selectTable(new SelectTableInterface() {
			@Override
			public void selectFinish(List<String> list) {
				for (int i = 0; i < list.size(); i++) {
					code.writeEntityCode(list.get(i));
				}
			}
		});
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectTableJframe frame = new SelectTableJframe();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});	
	}
}

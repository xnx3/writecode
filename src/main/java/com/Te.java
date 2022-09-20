package com;

import com.xnx3.autowritecode.WriteCode;
import com.xnx3.autowritecode.interfaces.impl.Mysql;

public class Te {
	public static void main(String[] args) {
		
		WriteCode code = new WriteCode(new Mysql("local.mysql.leimingyun.com", 3306, "wangmarket", "root", "111111"));
		code.writeEntityCodeBySelectTableUI();
		
	}
}

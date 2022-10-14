package com.xnx3.writecode;

import com.xnx3.writecode.template.wm.Code;

/**
 * DEMO示例，自动生成实体类、controller、vo、jsp页面等
 * @author 管雷鸣
 */
public class Demo {
	public static void main(String[] args) {
		Code code = new Code();
		code.setPackageName("com.xnx3.test");	//设置生成的entity、controller、vo等java类放到哪个包下
		code.setProjectUrlPath("/admin/"); //设置url请求的路径
		code.write();	//创建相关代码文件
	}
}

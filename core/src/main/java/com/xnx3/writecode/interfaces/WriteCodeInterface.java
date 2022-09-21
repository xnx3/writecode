package com.xnx3.writecode.interfaces;

import java.util.List;

/**
 * 生成代码相关
 * @author 管雷鸣
 *
 */
public interface WriteCodeInterface {
	
	/**
	 * 要生成的Java文件的包名字。如果要写出的文件是Java文件，此项是要设置的，不然生成的Java文件的 package 就是空着的。
	 * @return 返回格式如 com.xnx3.j2ee.entity
	 */
	public String javaPackage(List<String> list);
	
	/**
	 * 写出文件要保存的绝对路径，要保存到哪。如果此项不设置，那默认是保存到 {@link #javaPackage(List)} 的包中
	 * @return 格式如 /Users/apple/git/wangmarket_shop/src/main/java/com/xnx3/wangmarket/shop/core/entity
	 */
	public String writeFileAbsolutePath();
	
	
}

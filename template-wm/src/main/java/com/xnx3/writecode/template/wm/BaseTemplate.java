package com.xnx3.writecode.template.wm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xnx3.FileUtil;
import com.xnx3.StringUtil;
import com.xnx3.writecode.bean.Template;
import com.xnx3.writecode.template.wm.entity.EntityTemplate;

/**
 * controller\entity\。。。等的父类
 * @author 管雷鸣
 *
 */
public class BaseTemplate extends Template{
	
	/**
	 * 初始化一些公用的参数设置
	 */
	public void baseInit() {
		
		//加入三方js
		List<String> jsList = new ArrayList<String>();
		jsList.add("http://res.zvo.cn/pinyin/pinyin.js");
		//jsList.add("http://res.zvo.cn/translate/translate.js");
		this.setExternalJS(jsList);
		
	}
	
}
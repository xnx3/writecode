package com.xnx3.writecode.util;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.xnx3.net.HttpResponse;
import com.xnx3.net.HttpUtil;

/**
 * js 相关
 * @author 管雷鸣
 *
 */
public class JavaScriptUtil {
	
	/**
	 * 初始化js支持
	 * @param engine
	 */
	public static ScriptEngine init(ScriptEngine engine) {
		String pinyinJS = "var pinyin={ convert:function(text){ return '加载云端汉字转拼音资源失败'; } }";
		HttpUtil http = new HttpUtil();
		HttpResponse hr = http.get("http://res.zvo.cn/pinyin/pinyin.js?form=writecode");
		if(hr.getCode() == 200) {
			pinyinJS = hr.getContent();
		}
		try {
			engine.eval(pinyinJS);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		return engine;
	}
	
}

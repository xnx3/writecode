package com.xnx3.writecode.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import cn.zvo.http.Http;
import cn.zvo.http.Response;

/**
 * js 相关
 * @author 管雷鸣
 *
 */
public class JavaScriptUtil {
	//key： js url     value: js文件的内容，缓存作用
	public static Map<String, String> jsCache;
	static {
		jsCache = new HashMap<String, String>();
	}
	
	/**
	 * 加载js的资源文件 （url）
	 * @throws IOException 
	 */
	public static ScriptEngine loadExternalJS(ScriptEngine engine, List<String> jsUrlList) throws IOException {
		if(jsUrlList == null || jsUrlList.size() == 0) {
			return engine;
		}
		
		for (int i = 0; i < jsUrlList.size(); i++) {
			String url =jsUrlList.get(i).trim();
			String text = "";
			
			if(jsCache.get(url) != null) {
				text = jsCache.get(url);
			}else {
				//没有缓存，那就拉取
				
				//http://res.zvo.cn/pinyin/pinyin.js?form=writecode
				Http http = new Http();
				Response res = http.get(url);
				if(res.getCode() == 200) {
					text = res.getContent();
				}
				
				if(text.length() == 0) {
					System.err.println("加载外部js资源文件失败:"+url);
				}else {
					//缓存内容
					jsCache.put(url, text);
				}
			}
			
			if(text != null && text.length() > 0) {
				try {
					engine.eval(text);
				} catch (ScriptException e) {
					e.printStackTrace();
				}
			}
		}
		
		return engine;
	}
	
}

package com.xnx3.writecode.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import com.xnx3.net.HttpResponse;
import com.xnx3.net.HttpUtil;
import com.xnx3.net.HttpsUtil;

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
	 */
	public static ScriptEngine loadExternalJS(ScriptEngine engine, List<String> jsUrlList) {
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
				if(url.indexOf("http://") > -1) {
					//http
					HttpUtil http = new HttpUtil();
					HttpResponse hr = http.get(url);
					if(hr.getCode() == 200) {
						text = hr.getContent();
					}
				}else {
					//https
					
					HttpsUtil https = new HttpsUtil();
					HttpResponse hr = https.get(url);
					if(hr.getCode() == 200) {
						text = hr.getContent();
					}
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

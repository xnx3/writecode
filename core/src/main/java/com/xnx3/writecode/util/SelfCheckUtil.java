package com.xnx3.writecode.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import com.xnx3.StringUtil;
import com.xnx3.writecode.bean.FieldBean;
import com.xnx3.writecode.bean.TableBean;

/**
 * 自检，将不足进行警示
 * @author 管雷鸣
 *
 */
public class SelfCheckUtil {
	public static boolean warning = true;	//默认开启
	public static int FIELD_COMMENT_SPLIT_LENGTH = 5;	//自检 {database.table.field.comment.split} 的长度，不包含英文字母的情况下的长度，默认是5
	public static int FIELD_COMMENT_SPLIT_ENGLISH_LENGTH = 12;	//自检 {database.table.field.comment.split} 的长度-包含英文字母情况下的长度，默认是10
	
	//key table.name value:随便一个1
	public static Map<String, String> tableCache;
	static {
		tableCache = new HashMap<String, String>();
	}
	
	/**
	 * 控制台输出 warning 警告信息
	 * @param text 输出的信息
	 */
	public static void warning(String text) {
		if(!warning) {
			return;
		}
		
		System.err.println("WARNING : "+text);
	}
	
	/**
	 * 数据表设计的自检
	 * 1. 表注释是否设置
	 * 2. 字段注释是否设置
	 * @param tableBean
	 */
	public static void selfCheck(TableBean tableBean) {
		if(tableCache.get(tableBean.getName()) != null) {
			return;
		}
		
		//表注释
		if(tableBean.getName().equals(tableBean.getComment())) {
			warning("请给 ["+tableBean.getName()+"] 表增加一个注释用于说明此表是做什么的");
		}
		
		//字段相关
		for (int i = 0; i < tableBean.getFieldList().size(); i++) {
			FieldBean field = tableBean.getFieldList().get(i);
			
			//字段注释
			if(field.getName().equals(field.getComment())) {
				warning("请给 ["+tableBean.getName()+"] 表中 ["+field.getName()+"] 字段增加一个注释用于说明此字段是做什么的");
			}
			
			
			if(!field.getName().equals(field.getComment())) {
				String split = TemplateUtil.fieldCommentSplit(field.getComment());
				boolean lengthTishi = false;	//长度提示
				if(split.matches(".*[a-zA-z].*")) {
					//有英文
					if(split.length() > FIELD_COMMENT_SPLIT_ENGLISH_LENGTH) {
						lengthTishi = true;
					}
				}else {
					//无英文
					if(split.length() > FIELD_COMMENT_SPLIT_LENGTH) {
						lengthTishi = true;
					}
				}
				if(lengthTishi) {
					warning("["+tableBean.getName()+"] 表中 ["+field.getName()+"] 字段的注释 ["+field.getComment()+"] 可优化，当前该注释可提取出字段标题为 ["+split+"]，标题长度太长，建议不超过"+FIELD_COMMENT_SPLIT_LENGTH+"个汉字或"+FIELD_COMMENT_SPLIT_ENGLISH_LENGTH+"个英文，以保证生成列表页面及编辑页面该字段标题显示美观程度，不至于太长挤换行。详细图文讲解参见： https://github.com/xnx3/writecode/blob/master/doc/tag_database.table.field.comment.split.md");
				}
			}
			
		}
		
		if(tableBean.getName().equals(tableBean.getComment())) {
			warning("请给 ["+tableBean.getName()+"] 表增加一个注释用于说明此表是做什么的");
		}
		
		tableCache.put(tableBean.getName(),"1");
	}
}

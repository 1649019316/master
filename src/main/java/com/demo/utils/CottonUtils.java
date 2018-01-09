package com.demo.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class CottonUtils {
	
	/**
	 * 自动补0
	 * @param pre 前缀
	 * @param post 后缀
	 * @param sequence 当前基础
	 * @param num 补0数量
	 * @return
	 */
	public static String createSalesNo(String pre, String post, int sequence,int num) {
		String seq = "" + sequence;
		for (int i = 0; i < num; i++) {
			seq= "0"+seq;
		}
		String currDateStr = DateUtils.getFormatDate(new Date(), "yyyyMMdd");
		String returnStr = currDateStr + seq.substring(seq.length() - num, seq.length());
		if(StringUtils.isNotBlank(pre)){
			returnStr = pre + returnStr;
		}
		if(StringUtils.isNotBlank(post)){
			returnStr = returnStr + post;
		}
		return returnStr;
	}
}

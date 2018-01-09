/**
 * 中棉机械项目
 * cn.com.cotton.util
 * CommonUtils.java
 * 
 * 2012-12-10-下午03:08:31
 * 2012中棉机械成套设备有限公司-版权所有
 * 
 */
package com.demo.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * CommonUtils
 * anyq
 * 2012-12-10 下午03:08:31
 * 
 * @version 1.0.0
 * 
 */
public class CommonUtils {
	/* excel中文乱码问题 */
	public static String toUtf8String(String s){ 
	     StringBuffer sb = new StringBuffer(); 
	       for (int i=0;i<s.length();i++){ 
	          char c = s.charAt(i); 
	          if (c >= 0 && c <= 255){sb.append(c);} 
	        else{ 
	        byte[] b; 
	         try { b = Character.toString(c).getBytes("utf-8");} 
	         catch (Exception ex) { 
	             System.out.println(ex); 
	                  b = new byte[0]; 
	         } 
	            for (int j = 0; j < b.length; j++) { 
	             int k = b[j]; 
	              if (k < 0) k += 256; 
	              sb.append("%" + Integer.toHexString(k).toUpperCase()); 
	              } 
	     } 
	  } 
	  return sb.toString(); 
	}
	
	public static int getInt(byte[] bb, int index) {  
        return (int) ((((bb[index + 3] & 0xff) << 24) | ((bb[index + 2] & 0xff) << 16)  
                | ((bb[index + 1] & 0xff) << 8) | ((bb[index + 0] & 0xff) << 0)));  
    }

	public static String getMapValue(Map<String, Object> map, String key) {
		if(map.containsKey(key)){
			String value=String.valueOf(map.get(key));
			if(StringUtils.isNotBlank(value) && !"null".equals(value)){
				return value;
			}else{
				return "";
			}
		}
		return "";
	}
	
}

/**
 * 中棉机械项目
 * cn.com.cotton.util
 * DoubleUtils.java
 * 
 * 2013-6-7-下午03:48:07
 * 2013中棉机械成套设备有限公司-版权所有
 * 
 */
package com.demo.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;


/**
 * 
 * DoubleUtils
 * anyq
 * 2013-6-7 下午03:48:07
 * 
 * @version 1.0.0
 * 
 */
public class DoubleUtils {
	  /**  
     * 对double数据进行取精度.  
     * @param value  double数据.  
     * @param scale  精度位数(保留的小数位数).  
     * @param roundingMode  精度取值方式.  
     * @return 精度计算后的数据.
     * 
     * 	setScale(1)表示保留一位小数，默认用四舍五入方式 
		setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3 
		setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4 
		setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4;2.34变成2.3
		setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍，2.36变成2.4
     */  
    public static double round(double value, int scale, 
             int roundingMode) {   
        BigDecimal bd = new BigDecimal(value+"");   
        bd = bd.setScale(scale, roundingMode);   
        double d = bd.doubleValue();   
        bd = null;   
        return d;   
    }   


     /** 
     * double 相加 
     * @param d1 
     * @param d2 
     * @return 
     */ 
    public static double sum(double d1,double d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.add(bd2).doubleValue(); 
    } 


    /** 
     * double 相减 
     * @param d1 
     * @param d2 
     * @return 
     */ 
    public static double sub(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.subtract(bd2).doubleValue(); 
    } 

    /** 
     * double 乘法 
     * @param d1 
     * @param d2 
     * @return 
     */ 
    public static double mul(double d1,double d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.multiply(bd2).doubleValue(); 
    } 


    /** 
     * double 除法 
     * @param d1 
     * @param d2 
     * @param scale 四舍五入 小数点位数 
     * @return 
     */ 
    public static double div(double d1,double d2,int scale){ 
        //  当然在此之前，你要判断分母是否为0，   
        //  为0你可以根据实际需求做相应的处理 

        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
        return bd1.divide 
               (bd2,scale,BigDecimal.ROUND_HALF_UP).doubleValue(); 
    } 
    
    public static  String formatNum(Object object, int i) {
		if (object != null) {
			String value = object.toString();
//			先四舍五入，然后格式化。直接格式化不符合四舍五入。
			value=DoubleUtils.round(Double.valueOf(value), 2, BigDecimal.ROUND_HALF_UP)+"";
			StringBuffer format = new  StringBuffer("######0.");
			for (int j = 0; j < i; j++) {
				format.append("0");
			}
			DecimalFormat df = new DecimalFormat(format.toString());
			if (StringUtils.isNotBlank(value)) {
				return df.format(new BigDecimal(value));
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
    
    
}

/**
 * 中棉机械项目
 * com.isoft.test
 * Base64.java
 * 
 * 2013-1-9-下午04:20:38
 * 2013中棉机械成套设备有限公司-版权所有
 * 
 */
package com.demo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * Base64
 * anyq
 * 2013-1-9 下午04:20:38
 * 
 * @version 1.0.0
 * 
 */
@SuppressWarnings("restriction")
public class Base64 {

	  /**    
     * BASE64解密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static byte[] decryptBASE64(String key) throws Exception {               
        return (new BASE64Decoder()).decodeBuffer(key);               
    }
    /**         
     * BASE64加密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(byte[] key) throws Exception {               
        return (new BASE64Encoder()).encodeBuffer(key);               
    }       
         
    public static void main(String[] args) throws Exception     
    {     
    	//!@#$zhongmian
        String data = Base64.encryptBASE64("37024".getBytes());
        System.out.println("加密后："+data);  
        data =  Base64.encryptBASE64(data.getBytes());
        System.out.println("加密后："+data);     
        byte[] byteArray = Base64.decryptBASE64(data);     
        String reData = new String(byteArray);
        System.out.println("解密后："+reData);   
        byteArray = Base64.decryptBASE64(reData);  
        System.out.println(new String(byteArray));
        
        System.out.println();
        
        String str = "NjU1MjQxNTIwMDksMCw5MDY1NTI0MTUxMDI5NDIyOTg1MjAwMDEyMjIwMTQ3NQ==";
        System.out.println(new String(Base64.decryptBASE64(str)));
        
        
    }     
}

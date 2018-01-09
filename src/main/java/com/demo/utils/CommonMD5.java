
package com.demo.utils;

import java.io.UnsupportedEncodingException;

import org.springframework.util.DigestUtils;

public class CommonMD5 {
	public static String encrypt(String input) throws Exception {
		return DigestUtils.md5DigestAsHex(input.getBytes());
	}

	public static String encrypt(String input, String character) throws Exception, UnsupportedEncodingException {
		return DigestUtils.md5DigestAsHex(input.getBytes(character));
	}

	public static void main(String[] args) throws Exception {
		String str1 = "201309200015@2.1@10.0@41.2@39.9@5@拖-中国400@622223196702104060@7100@3380@3720@0@2013-\r\n09-20@9.30@1484.28@3128@65588@长绒棉@1@11111111111<Row>201309200016@1.9@9.8@40.2@40.0@5@\r\n挂-2013005@622223196308204030@14820@4360@10460@0@2013-09-20@9.30@4184.00@3128@65588@长绒\r\n棉@1@11111111111<Row>\r\n";
		System.out.println(CommonMD5.encrypt(str1, "utf-8"));
		System.out.println(CommonMD5.encrypt("123"));
		System.out.println(CommonMD5.encrypt("123456","utf-8"));
	}
}

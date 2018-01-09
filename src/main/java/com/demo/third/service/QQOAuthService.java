package com.demo.third.service;

import com.alibaba.fastjson.JSONObject;

public interface QQOAuthService {

	String getAccessToken(String code);

	String getLoginUrl();

	String getOpenId(String accessToken);

	JSONObject getUserInfo(String accessToken, String openId);
	
}

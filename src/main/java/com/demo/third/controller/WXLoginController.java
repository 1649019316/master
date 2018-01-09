package com.demo.third.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.third.utils.HttpRequest;


@Controller
public class WXLoginController {

	@RequestMapping(method = { RequestMethod.GET }, produces = { "application/json;charset=UTF-8" },value="weixin/wxLogin.action")
	@ResponseBody
	public String wxLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String appId = "wx3cb78c9873ad3bcd";
			String appSecret = "369edb9ec1a518154559cd9222dbd633";
			String code = request.getParameter("code");
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret
					+ "&code=" + code + "&grant_type=authorization_code";
			// InputStream is = CommonUtils.doHttpGetRequest(url, "");
			String sendGet = HttpRequest.sendGet(url, "");
			// 获取并处理 响应的数据,将string 转换为JSON对象
			// String result = CommonUtils.convertStreamToString(is);
			// JSONObject jsonObj = new JSONObject(sendGet);
			// System.out.println(jsonObj.toString());
			// String openId = jsonObj.getString("openid");
			// String access_token = jsonObj.getString("access_token");
			// url =
			// "https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openId;
			// url =
			// "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openId;
			// is = CommonUtils.doHttpGetRequest(url, "");
			// result = CommonUtils.convertStreamToString(is);
			// System.out.println(result);
			// jsonObj = new JSONObject(result);
			// WeChatUser chatUserInfo = new WeChatUser();
			// chatUserInfo.setCountry(jsonObj.getString("country"));
			// chatUserInfo.setProvince(jsonObj.getString("province"));
			// chatUserInfo.setCity(jsonObj.getString("city"));
			// chatUserInfo.setOpenId(jsonObj.getString("openid"));
			// chatUserInfo.setUnionId(jsonObj.getString("unionid"));
			// chatUserInfo.setSex(String.valueOf(jsonObj.getInt("sex")));
			// chatUserInfo.setNickName(jsonObj.getString("nickname"));
			// chatUserInfo.setHeadImgUrl(jsonObj.getString("headimgurl"));
			// chatUserInfo.setLanguage(jsonObj.getString("language"));
			// System.out.println(chatUserInfo.toString());
			// 将weChat用户信息存入数据库中
			// int rowNum = weChatUserService.saveWeChatUser(chatUserInfo);
			// if (rowNum > 0) {
			// System.out.println("登录成功,openId:"+chatUserInfo.getOpenId());
			// 保存用户成功,将openId,nickname放入session中
			// request.getSession().setAttribute("openId",
			// chatUserInfo.getOpenId());
			// request.getSession().setAttribute("nickname",chatUserInfo.getNickName());
			// return new ResultWithJson("0","ok",chatUserInfo.getOpenId());
			// }else{
			// System.out.println("登录失败");
			// return new ResultWithJson("1","loginError","登录失败");
			// }
		} catch (Exception e) {
			e.printStackTrace();
			// return new ResultWithJson("1","loginError","登录失败");
		}
		return null;
	}

}

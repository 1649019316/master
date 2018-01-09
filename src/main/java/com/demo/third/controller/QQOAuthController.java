package com.demo.third.controller;
//package cn.third.controller;
//
//import com.alibaba.fastjson.JSONObject;
//
//import cn.third.service.QQOAuthService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@Controller
//public class QQOAuthController {
//    @Autowired
//    private QQOAuthService qqOAuthService;
//    // 访问登陆页面，然后会重定向到 QQ 的登陆页面
//    @RequestMapping(method = RequestMethod.GET,value="/oauth/qq")
//    public String qqLogin() {
//        return "redirect:" + qqOAuthService.getLoginUrl();
//    }
//    // QQ 成功登陆后的回调
////    @GetMapping("/oauth/qq/callback")
//    @RequestMapping(method = RequestMethod.GET,value="/oauth/qq/callback")
//    @ResponseBody
//    public String qqLoginCallback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
//        String accessToken = qqOAuthService.getAccessToken(code); // 5943BF2461ED97237B878BECE78A8744
//        // 保存 accessToken 到 cookie，过期时间为 30 天，便于以后使用
//        Cookie cookie = new Cookie("accessToken", accessToken);
//        cookie.setMaxAge(60 * 24 * 30);
//        response.addCookie(cookie);
//        return accessToken;
//    }
//    // 获取 QQ 用户的信息
////    @GetMapping("/oauth/qq/user")
//    @RequestMapping(method = RequestMethod.GET,value="/oauth/qq/user")
//    @ResponseBody
//    public String getUserInfo(@CookieValue(value = "accessToken", required = false) String accessToken) throws IOException {
//        if (accessToken == null) {
//            return "There is no access token, please login first!";
//        }
//        String openId = qqOAuthService.getOpenId(accessToken);
//        JSONObject json = qqOAuthService.getUserInfo(accessToken, openId);
//        return json.toJSONString();
//    }
//}
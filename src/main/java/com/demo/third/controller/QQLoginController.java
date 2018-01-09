package com.demo.third.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.third.utils.HttpRequest;


@Controller
public class QQLoginController {
	@RequestMapping("qqLogin/getUserInfo.action")
	@ResponseBody
	public String getUserInfo(HttpServletRequest request,Model model) {
	    String url=request.getParameter("url");
	    System.out.println(url);
		String param=null;
		String sendGet = null;
		try {
			sendGet = HttpRequest.sendGet(url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sendGet);
		return sendGet;
	}

}

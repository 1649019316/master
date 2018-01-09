<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script type="text/javascript"
src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="1106010345" data-redirecturi="localhost:8093/success.jsp" charset="utf-8"></script>  -->
<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<script type="text/javascript" src="js/scrollTop.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

function qqLogin(){
	var ip="192.168.1.56:8118";
/* 	var ip="211.103.196.77:58118"; */
	window.location.href="https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id=1106010345&redirect_uri=http://"+ip+"/all-demo/success.jsp&scope=get_user";
}
function wxLogin(){
	/* window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3cb78c9873ad3bcd&redirect_uri=http://www.baidu.com&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect"; */
	/* 获取access Token */
/* 	var params=[];
	params.grant_type="client_credential";
	params.appid="wx3cb78c9873ad3bcd";
	params.secret="369edb9ec1a518154559cd9222dbd633"; */
	var ip="localhost:8093";
    var obj = new WxLogin({
        id: "login_container",
        appid: "wx3cb78c9873ad3bcd",
        scope: "snsapi_login",
        redirect_uri: encodeURIComponent("http://" + ip + "/weixin/wxLogin.action"),
        state: Math.ceil(Math.random()*1000),
        style: "black",
        href: ""});
}
</script>
</head>
<body>
<!-- 211.103.196.77 -->
<!-- <a href="https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id=1106010345&redirect_uri=http://192.168.1.56:58118/all-demo/success.jsp&scope=get_user">腾讯QQ登录</a> --> 
<a href="javascript:void(0)" onclick="qqLogin();return false">腾讯QQ登录</a>
<br>
<a href="javascript:void(0)" onclick="wxLogin();return false">微信登录</a>
 <span id="login_container"></span>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	getUserInfo();
});
function getUserInfo(){
var accessToken = window.location.hash.substring(1);//获取路径中的access_token
var serverUrl="<%=request.getContextPath()%>/qqLogin/getUserInfo.action";
$.ajax({
	  type: 'GET',
	  url: 'https://graph.qq.com/oauth2.0/me?'+accessToken,
	  async: false,
	  dataType: "jsonp",
	  jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
	  jsonpCallback:"callback",
	  success: function(o){
		  console.log(o);//o就是上面提到的返回包
		  /* alert(o.openid); */
		  $.ajax({
			  type: 'POST',
			  url:serverUrl,
			  data:{url: 'https://graph.qq.com/user/get_user_info?'+accessToken+'&oauth_consumer_key=1106010345&openid='+o.openid},
			  async: false,
			  dataType: "json",
			  success: function(e){
				  
				  alert(e.ret+":"+e.msg);
			  
			  }
			}); 
	  }
});
}
</script>

</head>
<body>
登录中！请稍后！
</body>
</html>
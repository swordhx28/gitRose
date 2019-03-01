<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE	html PUBLIC	"-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html	xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"	/>
 <title>贵美商城</title>
 <link rel="stylesheet"	type="text/css"	href="${pageContext.request.contextPath}/css/global.css"	/>
 <link rel="stylesheet"	type="text/css"	href="${pageContext.request.contextPath}/css/layout.css"	/>
		
<script type="text/javascript">
// snameCheck() && passCheck()
function validateform(){
	if(snameCheck() && passCheck()) 
	   return true;
	 else
	   return false;
}

// Screen Name Validation
function snameCheck(){
var sname = document.getElementsByName("sname")[0].value;
var illegalChars = /\W/;
	if(sname.length != 0){
		if(illegalChars.test(sname)){
		document.getElementById("passMsg").innerHTML.replace(/'会员名无效'/,'');
    document.getElementById("passMsg").innerHTML='会员名无效';
		document.getElementsByName("sname")[0].select();
		return false;
		}
	}
	else
	{
	document.getElementById("userMsg").innerHTML.replace(/'是否忘记输入会员名？'/,'');
 document.getElementById("userMsg").innerHTML='是否忘记输入会员名？';
	document.getElementsByName("sname")[0].focus();
	return false
	}
	if(sname!=="zhangsan"){   //验证会员名必须为张三
	document.getElementById("userMsg").innerHTML.replace(/'是否输入的会员名有错？'/,'');
  document.getElementById("userMsg").innerHTML='是否输入的会员名有错？';
	document.getElementsByName("sname")[0].focus();
	return false;
	}
	return true;
}

function passCheck(){
var userpass = document.getElementsByName("pass")[0].value;
var illegalChars = /[\W_]/;// allow only charactors and numbers
	// Check if Password field is blank.
	if(userpass == ""){
		document.getElementById("passMsg").innerHTML.replace(/'未输入密码 \n" + "请输入密码'/,'');
    document.getElementById("passMsg").innerHTML='未输入密码 \n" + "请输入密码';
		document.getElementsByName("pass")[0].focus();
	return false;
	}
	// Check if password length is less than 6 charactor.
	if(userpass.length < 6){
		document.getElementById("passMsg").innerHTML.replace(/'密码必须多于或等于 6 个字符。\n'/,'');
    document.getElementById("passMsg").innerHTML='密码必须多于或等于 6 个字符。\n';
		document.getElementsByName("pass")[0].focus();
	return false;
	}
	//check if password contain illigal charactors.
	else if(illegalChars.test(userpass)){
		document.getElementById("passMsg").innerHTML.replace(/'密码包含非法字符'/,'');
    document.getElementById("passMsg").innerHTML='密码包含非法字符';
		document.getElementsByName("pass")[0].select();
		return false;
	}
	if(userpass!=="123456"){    //验证密码必须为123456
	  document.getElementById("passMsg").innerHTML.replace(/'密码错误，请重新输入！'/,'');
    document.getElementById("passMsg").innerHTML='密码错误，请重新输入！';
		document.getElementsByName("pass")[0].focus();
		return false;
	}
	return true;
}
</script> 	

<script type="text/javascript">
	function login(){
		var form = document.getElementsByName("myForm")[0];
		form.action = "${pageContext.request.contextPath}/userController/login";
		form.submit();
	}
</script>
</head>
<body>
<div id="container">
	<iframe id="header" src="${pageContext.request.contextPath}/header" width="980" height="136" frameborder="0" scrolling="no"></iframe>
	<form name="myForm" method="post">
	<div class="login">
		<ul class="form f_l">
			<h1><img src="images/logintitle.gif" alt="会员登录" /></h1>
			<li class="login_list"><label>会员名：</label><input type="text" name="loginName" />（可包含 a-z、0-9 和下划线）</li>
			<li class="login_list"><label>密&nbsp;&nbsp;&nbsp;码：</label><input type="password" name="pass" />（至少包含 6 个字符）</li>
			<li class="a_c"><img src="images/login.gif" alt="登录" onclick="login()"/></li>
			<li class="desc">&delta;&nbsp;&nbsp;<a href="#">什么是安全登录</a>。</li>
			<li class="desc">&delta;&nbsp;&nbsp;香港會員（繁體中文用戶）由此<a href="#">登入</a></li>
			<li class="desc">&delta;&nbsp;&nbsp;<a href="#">密码安全贴士</a>。</li>
			<li class="desc">&delta;&nbsp;&nbsp;防止病毒或者木马窃取您的账户信息，<a href="#">在线检查</a>您的电脑是否安全。</li>						
		</ul>
			<h1><img src="images/register.gif" alt="注册会员" /></h1>
		<ul class="aside f_l">
			<li class="regpic"><span>便宜有好货！</span>超过7000万件商品任您选。</li>
		 	<li class="regpic regpic2"><span>买卖更安全！</span>交易超安全。</li>
			<li class="regpic regpic3"><span>免费开网店！</span>轻松赚钱交友。</li>
			<li class="regpic regpic4"><span>超人气社区！</span>彩活动每一天</li>
			<li class="a_c"><a href="#"><img src="images/registernow.gif" alt="现在就注册" /></a></li>
			<ul class="reged">
				<li class="regpic regpic5">您已经是会员？<a href="#">由此登入</a></li>
			<li class="regpic regpic6">繁体中文用户由此<a href="#">注册</a></li>
			</ul>
		</ul>
	</div>
	</form>
	<iframe id="footer" src="${pageContext.request.contextPath}/footer.jsp" width="980" height="136" frameborder="0" scrolling="no"></iframe>
</div> <!--container end-->	
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE	html PUBLIC	"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html	xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"	/>
 <title>贵美商城</title>
 <link rel="stylesheet"	type="text/css"	href="${pageContext.request.contextPath}/css/global.css"	/>
 <link rel="stylesheet"	type="text/css"	href="${pageContext.request.contextPath}/css/layout.css"	/>
 <script type="text/JavaScript" src="js/func.js"></script>
</head>
<body>
<div id="container">
	<iframe id="header" src="${pageContext.request.contextPath}/header" width="980" height="136" frameborder="0" scrolling="no"></iframe>
    <form id="form1" method="post" action="${pageContext.request.contextPath}/userController/register" enctype="multipart/form-data">
    <table class="reg" cellspacing="0px" cellpadding="0px">
      <tbody>
      <tr>
        <td class="a_r w15p"><label for="fname">真实姓名：</label></td>
				<td class="w500"><input name="myName" id="fname" type="text" /></td>
        <td class="protocol" rowspan="11">
    		  <h4><img src="images/read.gif" alt="alt" />阅读贵美网服务协议 </h4>
        	<textarea name="textarea" cols="35" rows="15">欢迎阅读服务条款协议，本协议阐述之条款和条件适用于您使用Gmgw.com网站的各种工具和服务。
  本服务协议双方为贵美与贵美网用户，本服务协议具有合同效力。 
  贵美的权利和义务
    1.贵美有义务在现有技术上维护整个网上交易平台的正常运行，并努力提升和改进技术，使用户网上交易活动的顺利。 
    2.对用户在注册使用贵美网上交易平台中所遇到的与交易或注册有关的问题及反映的情况，贵美应及时作出回复。 
    3.对于在贵美网网上交易平台上的不当行为或其它任何贵美认为应当终止服务的情况，贵美有权随时作出删除相关信息、终止服务提供等处理，而无须征得用户的同意。 
    4.因网上交易平台的特殊性，贵美没有义务对所有用户的注册资料、所有的交易行为以及与交易有关的其他事项进行事先审查。 	
				  </textarea>
    	  </td> 				
      </tr>
     
      <tr>
				<td class="a_r" ><label for="sname">登录名：</label></td>
        <td><input name="loginName" id="sname" type="text" />（可包含 a-z、0-9 和下划线）</td>
      </tr>
      <tr>
        <td class="a_r"><label for="pass">密码：</label></td>
        <td><input name="pass" id="pass" type="password" />（至少包含 6 个字符）</td>
      </tr>
      <tr>
        <td class="a_r"><label for="rpass">再次输入密码：</label></td>
        <td><input name="rpass" id="rpass" type="password" /></td>
      </tr>
      <tr>
        <td class="a_r"><label for="email">电子邮箱：</label></td>
        <td><input name="email" id="email" type="text" />（必须包含 @  字符）</td>
      </tr>
      <tr>
        <td class="a_r"><label for="gender">性别：</label></td>
				<td>
					<input name="sex" id="gender" class="b0" type="radio" value="男" checked="checked" />
    	    <img src="images/Male.gif" alt="alt" />男&nbsp; 
    	    <input name="sex" class="b0" type="radio" value="女" />
    	    <img src="images/Female.gif" alt="alt" />女
        </td>
      </tr>
      <tr>
        <td class="a_r">头像：</td>
        <td><input type="file" name="image" /></td>
      </tr>    
      <tr>
        <td class="a_r"><label for="nYear">出生日期：</label></td>
        <td>
    		<input name="nyear" id="nYear" class="w40" value="yyyy" maxlength="4" />&nbsp;年&nbsp;&nbsp;
    	   <select name="nmonth">
    		   <option value="" selected="selected">[选择月份]</option>
    	     <option value="0">一月</option>
    	     <option value="1">二月</option>
    	     <option value="2">三月</option>
    	     <option value="3">四月</option>
    	     <option value="4">五月</option>
    	     <option value="5">六月</option>
    	     <option value="6">七月</option>
    	     <option value="7">八月</option>
    	     <option value="8">九月</option>
    	     <option value="9">十月</option>
    	     <option value="10">十一月</option>
    	     <option value="11">十二月</option>
    	   </select>
    	  <input name="nday" value="dd" class="w20" maxlength="2" />日
    	</td>
      </tr>
      <tr>
        <td colspan="2" class="pl150"><input type="image" class="b0" src="images/submit.gif" /><img src="images/reset.gif" class="hand" alt="重置" /></td>
      </tr>
      </tbody>
    </table>	
    </form>		
		<iframe id="footer" src="${pageContext.request.contextPath}/footer.jsp" width="980" height="150" frameborder="0" scrolling="no"></iframe>
	</div><!--container	end-->
</body>

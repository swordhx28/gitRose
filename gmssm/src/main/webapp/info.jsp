<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.shida.cn.dao.model.Product"%>
<!DOCTYPE	html PUBLIC	"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html	xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>贵美商城-商品分类页</title>
 <link rel="stylesheet"	type="text/css"	href="css/global.css"	/>
 <link rel="stylesheet"	type="text/css"	href="css/layout.css"	/>
 <script type="text/JavaScript" defer="true">
	var oImg=document.getElementById("show").getElementsByTagName("img");
	for(var i=1;i<=(oImg.length-3);i++){
		oImg[i].onmouseover=function(){oImg[0].src=this.src.replace('.jpg','_big.jpg');
			this.style.border='2px #ff7300 solid';}
		oImg[i].onmouseout=function(){this.style.border='2px #ccc solid';}
 	}
 </script>
</head>
<body>
<div id="container">
	<iframe id="header" src="header" width="980" height="136" frameborder="0" scrolling="no"></iframe>
	<div class="good">
		<%
			Product product = (Product)request.getAttribute("product");
		%>
		<h1><%=product.getName() %></h1>
		<ul class="f_l" id="show">
			<li class="bigimg"><a href="buy.jsp"><img src="images/show1_big.jpg" alt="笔记本大图" /></a></li>
			<li class="smallimg"><img src="images/show1.jpg" alt="小图1" /></li>
			<li class="smallimg"><img src="images/show2.jpg" alt="小图2" /></li>
			<li class="smallimg"><img src="images/show3.jpg" alt="小图3" /></li>
			<li class="smallimg"><img src="images/show4.jpg" alt="小图4" /></li>
			<li class="smallimg"><img src="images/show5.jpg" alt="小图5" /></li>			
			<li class="op clear f_l"><a href="#"><img src="images/share.gif" alt="分享" /></a></li>
			<li class="op f_l"><a href="#"><img src="images/favthis.gif" alt="藏" /></a></li>			
		</ul>
		<ul class="goodinfo f_l">
			<li>一 口 价：<span><%=product.getPrice() %>元</span></li>
			<li>运　　费：<span><%=product.getFreight() %>元</span></li>
			<li class="h74 buynow"><a href="buy?pid=<%=product.getId() %>"><img src="images/buynow.gif" alt="alt" /></a></li>
			<li class="onlinepay">此商品支持<a href="#">网银支付</a>，网上汇款免手续费。<br />收货满意后出售者才能拿钱，货款都安全！</li>
			<li>剩余时间：<span>10天23小时</span></li>
			<li>累计售出：<span><%=product.getSellNum() %>件</span></li>
			<li class="f_l w175">商品类型：<span><%=request.getAttribute("categoryName") %></span></li>
			<li>所 在 地：<span><%=product.getSellAddress() %></span></li>
			<li class="f_l w175">商品库存：<span><%=product.getStockNum() %>件</span></li>
			<li>浏 览 量：<span><%=product.getViewNum() %>次</span></li>			
		</ul>
	</div>
	<iframe id="footer" src="footer.jsp" width="980" height="136" frameborder="0" scrolling="no"></iframe>
</div> <!--container end-->	
</body>
</html>

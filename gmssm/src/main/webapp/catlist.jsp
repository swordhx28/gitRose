<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE	html PUBLIC	"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html	xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>贵美商城</title>
 <link rel="stylesheet"	type="text/css"	href="css/global.css"	/>
 <link rel="stylesheet"	type="text/css"	href="css/layout.css"	/>
 <script type="text/JavaScript">
 	function multiCheck(oThis){
 		var oInput=document.getElementsByTagName("input");
 		for (var i=1;i<oInput.length;i++){ 	
 				oInput[i].checked=oThis.checked;}
 	}
	</script>
</head>
<body>
<div id="container">
	<iframe id="header" runat="server" src="header" width="980" height="136" frameborder="0" scrolling="no"></iframe>
	<div class="catlist a_c">
		<ul class="w20p check">
			<h3><input type="checkbox" name="checkall" onclick="multiCheck(this)" />全选</h3>
			<li><input type="checkbox" /></li>
			<li><input type="checkbox" /></li>
			<li><input type="checkbox" /></li>
			<li><input type="checkbox" /></li>			
		</ul>
		<ul class="w20p">
			<h3>商品图片</h3>
			<li><a href="#"><img src="images/list1.jpg" alt="" /></a></li>
			<li><a href="#"><img src="images/list2.jpg" alt="" /></a></li>
			<li><a href="#"><img src="images/list3.jpg" alt="" /></a></li>
			<li><a href="#"><img src="images/list4.jpg" alt="" /></a></li>
		</ul>
		<ul class="w50p info">
			<h3>商品名称/出售者/联系方式</h3>
			<li><a href="#">杜比环绕，家庭影院必备，超真实享受</a><br />出售者：<a href="#">ling112233<br /><a href="#"><img src="images/contactme.gif" alt="alt" /> <img src="images/addfav.gif" alt="收藏" />收藏</a></li>
			<li><a href="#">NVDIA 9999GT 512MB 256bit极品显卡，不容错过</a><br />出售者：<a href="#">aipiaopiao110<br /><a href="#"><img src="images/contactme.gif" alt="alt" /> <img src="images/addfav.gif" alt="收藏" />收藏</a></li>
			<li><a href="#">精品热卖：高清晰，30寸等离子电视</a><br />出售者：<a href="#">阳光的挣扎<br /><a href="#"><img src="images/contactme.gif" alt="alt" /> <img src="images/addfav.gif" alt="收藏" />收藏</a></li>
			<li><a href="#">Sony索尼家用最新款笔记本</a><br />出售者：<a href="#">疯狂的镜无<br /><a href="#"><img src="images/contactme.gif" alt="alt" /> <img src="images/addfav.gif" alt="收藏" />收藏</a></li>																			
		</ul>
		<ul class="w10p price">
			<h3>价格</h3>
			<li>一口价：<br />2833.0</li>
			<li>一口价：<br />2460.0</li>
			<li>一口价：<br />18880</li>
			<li>一口价：<br />5889.0</li>
		</ul>
	</div>
	<iframe id="footer"  src="footer.jsp" width="980" height="136" frameborder="0" scrolling="no"></iframe>
</div> <!--container end-->	
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.shida.cn.dao.model.Product"%>
<!DOCTYPE	html PUBLIC	"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html	xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>贵美商城-商品购买页</title>
 <link rel="stylesheet"	type="text/css"	href="css/global.css"	/>
 <link rel="stylesheet"	type="text/css"	href="css/layout.css"	/>
 <%
    	Product product = (Product)request.getAttribute("product");
 %>
 <script type="text/JavaScript" defer="true">
function openWindow(){
    var address=window.showModalDialog("address.jsp","","dialogWidth=340px;dialogHeight=270px");
	  document.getElementById("address").value=address;	
		}
  	function remove(oThis){
  		oFather=oThis.parentNode;
  		oFather.parentNode.parentNode.removeChild(oFather.parentNode);
  		priceCalc();
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#submitBtn").click(function(){
			var username = $("#username").val();
			var address = $("#address").val();
			var phone = $("#code").val() + $("#telnumber").val() + $("#branchnumber").val();
			var buyNum = $("#buyNum").val();
			var price = <%=product.getPrice() %>;
			var pid = <%=product.getId() %>;
			$.post("addbuy",{username:username,address:address,phone:phone,price:price,buyNum:buyNum,pid:pid},function(data){
				alert(data);
			},"json");
		});
	});
</script>
</head>
<body>
<div id="container">
	<iframe id="header" src="header" width="980" height="136" frameborder="0" scrolling="no"></iframe>
    <div class="buy">
			<h4><img src="images/mycart.gif" alt="alt" /> <a href="#">全场运费一律2元</a> <a href="#" class="b" onclick="JavaScript:window.open('calc.jsp','计算器','width=200,height=200,toolbar=no,scrollbars=no,menubar=no,screenX=100,screenY=100')">简易计算器</a></h4>
      <table class="buy" cellpadding="0" cellspacing="0">
    	<tbody id="priceTable">
			<tr class="buytitle"><td colspan="6" class="b">确认商品价格与交易条件</td></tr>
      <tr>
        <td class="w364 b">&nbsp;&nbsp;&nbsp;&nbsp;商品名</td>
        <td class="w100 b">原价</td>
        <td class="w100 b">优惠价</td>
        <td class="w100 b">打折</td>
        <td class="w100 b">数量</td>
        <td class="w81 b">删除</td>
      </tr>
      <tr class="h26 blue">
      	
        <td><a href="info?id=<%=product.getId() %>" >&nbsp;&nbsp;&nbsp;&nbsp;<%=product.getName() %></a></td>
        <td><span class="c9">￥<%=product.getPrice() %></span></td>
        <td><span class="red">￥00.00</span></td>
        <td><span class="black">骨折</span></td>
        <td><input onchange="priceCalc()" type="text" id="buyNum" name="buyNum" value="1" maxlength="4" size="3"/></td>
        <td><a href="JavaScript:void(0)" onclick="remove(this)">删除</a></td>
      </tr>
      <tr class="h26">
        <td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="catlist.jsp">继续挑选商品</a></td>
        <td class="a_c" colspan="2" id="sum" class="pl58">￥<%=product.getPrice() %></td>
        <td colspan="3">你共节省：￥00.00</td>
      </tr>
    </tbody>
  	</table>
    <form id="buyForm" method="post">
    <table class="buy">    
    <tbody>    
      <tr class="buytitle">
        <td colspan="2" >补充您的邮件地址和联系人基本信息</td>
      </tr>
      <tr>
				<td class="a_r"><label for="username">收件人：</label></td>
        <td><input type="text" name="username" id="username" />
			</tr>
      <tr>
				<td class="a_r"><label for="address">地&nbsp;&nbsp;&nbsp;址： </label></td>
        <td id="selectPlace">
        	<input type="text" name="address" id="address" />
        	<a href="javascript:openWindow()" class="b">请点击填写地址</a>
  			</td>
      </tr>      
      <tr>
				<td class="a_r"><label for="tel">电&nbsp;&nbsp;&nbsp;话：</label></td>
        <td><input class="w30" type="text" name="code" id="code" size="4" id="tel" />-<input class="w81" type="text" name="telnumber" id="telnumber" size="8" />-<input class="w30" type="text" name="branchnumber" id="branchnumber" size="4" /><span class="red b"></span>（区号-电话号码-分机）</td>
      </tr>
      <tr>
        <td class="h65">&nbsp;</td>
        <td><input id="submitBtn" class="imginput" type="image" src="images/submit.gif" /></td>
      </tr>
      </tbody>
  </table>
  </form>    		
		<iframe id="footer" src="footer.jsp" width="980" height="150" frameborder="0" scrolling="no"></iframe>
	</div><!--container	end-->
</body>

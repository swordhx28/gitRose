<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模式窗口练习</title>
<style type="text/css">
<!--
#addressTable {
	width: 340px;
	height: 270px;
	background-color: #666666;
	text-align: left;
	border: 0px;
	font-size: 12px;
}

input {
	border: 1px solid #cccccc;
}

.td_title {
	text-align: right;
	color: #dddddd;
}

.a_c {
	text-align: center;
	color: #dddddd;
}
-->
</style>
<script type="text/JavaScript">
	function closeWindow() {
		var selProvince = document.getElementById("selProvince").value;
		var selCity = document.getElementById("selCity").value;
		var number = document.getElementById("number").value;
		var street = document.getElementById("street").value;
		window.returnValue = selProvince + selCity + street + number;
		window.close();
	}

	function changeselCity() {
		var selProvince = document.getElementById("selProvince").value;
		var selCityList = new Array();
		selCityList['北京市'] = [ '海淀区', '昌平区', '东城区', '西城区', '通州区', '顺义区', '朝阳区',
				'崇文区', '宣武区', '石景山区', '丰台区', '房山区', '大兴区', '平谷区', '门头沟区',
				'怀柔区', '延庆区', '密云区' ];
		selCityList['山东省'] = [ '济南', '青岛', '淄博', '枣庄', '东营', '烟台', '潍坊', '济宁',
				'泰安', '威海', '日照' ];
		selCityList['四川省'] = [ '成都', '绵阳', '德阳', '自贡', '内江', '乐山', '南充', '雅安',
				'眉山', '甘孜', '凉山', '泸州' ];
		selCityList['湖北省'] = [ '武汉', '宜昌', '荆州', '襄樊', '黄石', '荆门', '黄冈', '十堰',
				'恩施', '潜江' ];

		document.getElementById("selCity").options.length = 0;
		//获得省份选项的索引，这里使用标识
		var pIndex = document.getElementById("selProvince").value;
		var newoption1;
		for ( var j in selCityList[pIndex]) {
			newoption1 = new Option(selCityList[pIndex][j],
					selCityList[pIndex][j]);
			document.getElementById("selCity").options.add(newoption1);
		}
	}
</script>
</head>

<body>
	<form action="" method="post">
		<table id="addressTable" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td colspan="2" class="a_c"><h4>送货地址确认</h4></td>
				</tr>
				<tr>
					<td class="td_title">省份：</td>
					<td><select id="selProvince" onChange="changeselCity( )">
							<option value="北京市">北京市</option>
							<option value="四川省">四川省</option>
							<option value="山东省">山东省</option>
							<option value="湖北省">湖北省</option>
					</select></td>
				</tr>
				<tr>
					<td class="td_title">市/区：</td>
					<td><select id="selCity">
							<option value="海淀区">海淀区</option>
							<option value="朝阳区">朝阳区</option>
							<option value="东城区">东城区</option>
							<option value="西城区">西城区</option>
					</select></td>
				</tr>

				<tr>
					<td class="td_title">街道：</td>
					<td><input id="street" type="text" size="20" /></td>
				</tr>
				<tr>
					<td class="td_title">门牌号：</td>
					<td><input id="number" type="text" size="20" /></td>
				</tr>
				<tr>
					<td colspan="2" class="a_c"><input name="btn" type="button"
						value="确认" onClick="closeWindow()" /></td>
				</tr>
			</tbody>
		</table>
	</form>

</body>
</html>

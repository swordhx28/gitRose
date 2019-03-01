<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE>
<html>
<head>
<script src="static/js/jquery-3.3.1.min.js"></script>
<script>
	$(function() {
		/**监听表单提交事件**/
		 $("form").submit(function(e){
                        //一次性获取所有表单数据
           //html5
			var formData = new FormData(document.getElementById("form"));
                        //通过ajax提交数据
			$.ajax({
			    url : "index/upload.jhtml",
			    type : "POST",
			    data : formData,
			    /***必须false才会自动加上正确的Content-Type*/
			    contentType : false,
			    /**必须false才会避开jQuery对 formdata 的默认处理
			    * XMLHttpRequest会对 formdata 进行正确的处理 */
			    processData : false,
			    success : function(data) {
				    $("[id=status]").text(data);
			    },
			    error : function() {
							
			    }
			  });
			//阻止submit的默认提交事件	
			return false;	
		});
	});
</script>
</head>
<body>
	<form id="form"   enctype="multipart/form-data"  method="post">
	<table border="1px">
		<tr><td>username:</td><td><input type="text" name="username"></td></tr>
		<tr><td>upload file:</td><td> <input type="file" name="file"></td></tr>
		<tr>
			<td>
				<span>status:</span>
				<span style="color: red" id="status"></span>
			</td>
			<td style="text-align: right;">
				<input type="submit" value="upload">
			</td>
		</tr>
		</table>
	</form>
	<img src="index/image.jhtml?filename=m00/group/51/29/51298962-d388-4a22-ae12-1464639f6132-000-000.jpg"/>
</body>
</html>
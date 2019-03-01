<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.shida.cn.dao.model.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
</head>
<body>

	<form action="<%=path%>/insertProduct" method="post"
		enctype="multipart/form-data">
		名称:<input type="text" name="name" /> <br /> 价格:<input
			type="text" name="price" /> <br /> 运费:<input type="text"
			name="freight" /> <br /> 失效时间:<input type="text"
			name="expireTime" /> yyyy/MM/dd格式 <br /> 库存:<input type="text"
			name="stockNum" /> <br /> 所在地:<input type="text"
			name="sellAddress" /> <br /> 
			商品类型: <select name="categoryId">
			<%
				/* List<CategoryDTO> categoryList = (List<CategoryDTO>) request
						.getAttribute("categoryList");
				for (int i = 0; i < categoryList.size(); i++) {
					CategoryDTO c = categoryList.get(i);
					Set<CategoryDTO> children = c.getCategoriesDTO();
					for(CategoryDTO child : children){
						out.print("<option value=" + child.getId() + ">" + child.getName() + "</option>");
					}
				} */
				Map<Category,List<Category>> cateMap = (Map<Category,List<Category>>)request.getAttribute("categoryName");
				for(Category category : cateMap.keySet()){
					for(Category cate : cateMap.get(category)){
						out.print("<option value=" + cate.getId() + ">" + cate.getName() + "</option>");
					}
				}
			%>
		</select>
		<br/>
		商品主图片:<input type="file" name="images" /> <br/>
		商品副图片1:<input type="file" name="images" /> <br/>
		商品副图片2:<input type="file" name="images" /> <br/>
		商品副图片3:<input type="file" name="images" /> <br/>
		商品副图片4:<input type="file" name="images" /> <br />
		<button type="submit">添加</button>
		<button type="reset">重置</button>
	</form>
</body>
</html>
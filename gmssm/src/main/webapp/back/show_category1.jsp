<%@page import="com.shida.cn.dao.model.Category"%>
<%@page import="com.shida.cn.utils.Page"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>编号</td>
			<td>类别名称</td>
		</tr>
	
	<%
		Page<Category> resultList = (Page<Category>)request.getAttribute("categoryList");
		for(Category category : resultList.getLists()){
			out.print("<tr><td>"+category.getId()+"</td><td>"+category.getName()+"</td></tr>");
		}
	%>
	</table>
	<%	
		String pageStr = request.getParameter("page")==null?"1":request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize")==null?"5":request.getParameter("pageSize");
		Integer pageNum = Integer.parseInt(pageStr);
		Integer pageSize = Integer.parseInt(pageSizeStr);
	%>
	<a href="categoryList?page=1&pageSize=5">首页</a>
	<a href="categoryList?page=<%=pageNum-1<1? 1: pageNum-1 %>&pageSize=<%=pageSize%>">上一页</a>
	<a href="categoryList?page=<%=pageNum+1>resultList.getTotalPage()?resultList.getTotalPage():pageNum+1 %>&pageSize=<%=pageSize%>">下一页</a>
	<a href="categoryList?page=<%=resultList.getTotalPage() %>&pageSize=5">末页</a>
</body>
</html>
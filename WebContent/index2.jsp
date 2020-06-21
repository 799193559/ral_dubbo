<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>客户管理页面</title>
</head>
<body>
	<h2 align="center">客户管理系统</h2>
	<form action="${pageContext.request.contextPath }/findOne.action" method="post">
		<table border="1px" align="center" width="700px">
			<tr>
				<td colspan="6" align="right">
					请输入客户ID：<input type="text" name="c_id"/>
					<input type="submit" value="查询"/>
				</td>
			</tr>
			<tr align="center">
				<td>客户ID</td>
				<td>客户姓名</td>
				<td>客户密码</td>
				<td>客户地址</td>
				<td>客户手机</td>
				<td>客户邮箱</td>
			</tr>
			<tr align="center">
				<td>${c.c_id}</td>
	            <td>${c.c_name}</td>
	            <td>${c.c_password}</td>
		        <td>${c.c_address }</td>
		        <td>${c.c_phone }</td>
		        <td>${c.c_email }</td>
			</tr>	
		</table>
	</form>
</body>
</html>

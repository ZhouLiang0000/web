<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/transferServlet"
		method="post">
		转出账号：<input type="text" name="out"><br> 转入账号：<input
			type="text" name="in"><br> 转账金额：<input type="text"
			name="money"><br> <input type="submit" value="确认转账"><br>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ai.web.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		pageContext.setAttribute("company", "亚信集团");
		//存储字符串
		request.setAttribute("company", "亚信");
		//存储对象
		User mUser = new User();
		mUser.setUid(1);
		mUser.setUname("zhangsan");
		mUser.setUpassword("123");
		session.setAttribute("user", mUser);
		//存储集合
		List<User> list = new ArrayList<User>();
		User user1 = new User();
		user1.setUid(2);
		user1.setUname("lisi");
		user1.setUpassword("123");
		list.add(user1);
		User user2 = new User();
		user2.setUid(3);
		user2.setUname("wangwu");
		user2.setUpassword("123");
		list.add(user2);
		application.setAttribute("list", list);
	%>
	<!-- 脚本取值 -->
	<%=request.getAttribute("company")%>
	<%
		User sessionUser = (User) session.getAttribute("user");
		out.write(sessionUser.getUname());
	%>
	<hr />
	<!-- 使用EL表达式取值 -->
	${requestScope.company} ${sessionScope.user.uname }
	${applicationScope.list[1].uname}
	<hr />
	<!-- 使用EL全域查找取值 -->
	${company} ${user.uname } ${list[1].uname}
</body>
</html>
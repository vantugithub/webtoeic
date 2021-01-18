<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page user</title>
</head>
<body>

	<jsp:include page="header.jsp" />

	<h1>Đây là trang của User</h1>
	<h1>
		<%= session.getAttribute("sessionuser") %>
	</h1>
	<form action="LogoutController" method="post">
<input type="submit"  value="Logout" />
</form>
	<jsp:include page="footer.jsp" />

</body>
</html>
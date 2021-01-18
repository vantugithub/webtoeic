<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${listcommentgrammar}" var="list">
		<h4 style="background-color: yellow" class="input-large">${list.name}</h4>

		
		<p style="background-color: powderblue; height: 100px"
			class="input-xxlarge">${list.cmtGrammarContent}</p>

	</c:forEach>
</body>
</html>
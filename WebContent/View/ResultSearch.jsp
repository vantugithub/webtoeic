<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
<link href="Template/Frontend/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="Template/Frontend/css/style.css" rel="stylesheet">

<link rel="stylesheet" href="Template/Font/font-awesome.min.css" />
<!-- Fav and touch icons -->

<link rel="shortcut icon" href="ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="ico/apple-touch-icon-57-precomposed.png">
<script src="Template/Frontend/js/jquery-1.js"></script>
<script src="Template/Frontend/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<c:if test="${ketqua ==null }">

				<c:forEach items="${list}" var="lis">
					<div class="span6">
						<div class="media">
							<a href="#" class="pull-left"><img
								src="UploadImage/${lis.grammarimage}" class="media-object"
								alt='' width="250" height="100" /></a>
							<div class="media-body">
								<h4 class="media-heading"></h4>
								<p>${lis.grammarname}</p>
								<a
									href="DetailsgrammarguidelineForward?grammarguidlineid=${lis.grammarguidlineid}"
									class="btn" type="button">Button</a>
							</div>
						</div>
					</div>
				</c:forEach>

			</c:if>
		</div>
	</div>

</body>
</html>
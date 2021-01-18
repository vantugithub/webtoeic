<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chủ đề từ vựng</title>

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
	<jsp:include page="header.jsp" />

	<div class="container">
		<!--PAGE TITLE-->

		<div class="row">
			<div class="span12">
				<div class="page-header">
					<h1>Danh sách các chủ đề từ vựng</h1>
				</div>
			</div>
		</div>

		<!-- /. PAGE TITLE-->

		<div class="row">
			<c:forEach items="${list}" var="lis">
				<div class="span6">
					<div class="media">
						<a href="#" class="pull-left"><img
							src="ImageVocabulary/${lis.vocabularyimage}" class="media-object" alt=''
							width="250" height="100" /></a>
						<div class="media-body">
							<h4 class="media-heading"></h4>
							<p>${lis.vocabularyname}</p>
							<a
								href="ContentOfVocabularyTopicsController?vocabularyguidelineid=${lis.vocabularyguidelineid}"
								class="btn" type="button">Button</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<!--Pagination-->
	<div class="row">
		<div class="span9">
			
			<div class="pagination">

				<ul>
					<c:if test="${numberPage == 1}">

						<li><a href="VocabularyTopicsForward?page=1">1</a></li>
						<li><a href="VocabularyTopicsForward?page=2">2</a></li>
						<li><a
							href="VocabularyTopicsForward?page=${numberPage+1}">Next</a></li>
					</c:if>

					<c:if test="${numberPage == maxPageId}">
						<li><a
							href="VocabularyTopicsForward?page=${numberPage-1}">Prev</a></li>
						<li><a href="VocabularyTopicsForward?page=1">1</a></li>
						<li><a href="VocabularyTopicsForward?page=2">2</a></li>
					</c:if>

					<c:if test="${numberPage > 1 && numberPage < maxPageId}">
						<li><a
							href="VocabularyTopicsForward?page=${numberPage-1}">Prev</a></li>
						<li><a href="VocabularyTopicsForward?page=1">1</a></li>
						<li><a href="VocabularyTopicsForward?page=2">2</a></li>
						<li><a
							href="VocabularyTopicsForward?page=${numberPage+1}">Next</a></li>

					</c:if>
					
				</ul>

			</div>
			
		</div>
	</div>        
<!--/.Pagination-->

	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
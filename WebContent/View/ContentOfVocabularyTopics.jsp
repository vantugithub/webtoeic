<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết chủ đề từ vựng</title>

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
							<h3>
								Nội dung chủ đề từ vựng
							</h3>
							
							
						</div>
						
				</div>
				
			</div>
		
		  <!-- /. PAGE TITLE-->
		  	
		  	
			<div class="row">	
					<c:forEach items="${list}" var="lis">
						<div class="span6">			
								<div class="media">
									 <a href="#" class="pull-left"><img src="Imageaudiotuvung/${lis.image}" class="media-object" alt='anh' width="128px" height="128px"/></a>
									<div class="media-body">
										 
										<p>
											Từ: ${lis.vocabularycontentname}
										</p>
										<p>
											Phiên âm: ${lis.transcribe}
										</p>
										<p>
											Nghĩa và từ loại: ${lis.mean}
										</p>
										
										<p>
												<audio controls>
														<source src="Imageaudiotuvung/${lis.audiogg}" type="audio/ogg">
														<source src="Imageaudiotuvung/${lis.audiomp3}" type="audio/mpeg">
												</audio>
										</p>
										
									</div>
								</div>						
						</div>	
					</c:forEach>				
			</div>
			
		</div>
		<jsp:include page="footer.jsp" />
</body>
</html>
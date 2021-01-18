<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết bài hướng dẫn ngữ pháp</title>
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


<script type="text/javascript">
	function Binhluan() {
		var xhttp;
		var memberid = "<%=session.getAttribute("sessionuserid") %>";
		var grammarid = "<%=request.getAttribute("grammarId")%>";
		var content = document.formbinhluan.content.value;
		var url = "CmtController?content="+content+"&memberid="+memberid+"&grammarid="+grammarid;
		
		
			if (window.XMLHttpRequest) {
				xhttp = new XMLHttpRequest();
			} else {
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4) {
					var data = xhttp.responseText;
					document.getElementById("listcomment").innerHTML = data;
				}
			}
			xhttp.open("POST", url, true);
			xhttp.send();
		}
		
</script>


</head>
<body>
	<jsp:include page="header.jsp" />


	<div class="container">
		<!--PAGE TITLE-->

		<div class="row">
			<div class="span12">
				<div class="page-header">
					<h1>Blog</h1>
				</div>
			</div>
		</div>

		<!-- /. PAGE TITLE-->

		<div class="row">
			<div class="span9">
			<div class="postmetadata">
								<ul>
										<li>
											 <i class="icon-calendar"></i><%= new java.util.Date() %>
										</li>
								</ul>
							</div>
				<!--Blog Post-->
				
				<c:forEach items="${list}" var="lis">
					<div class="blog-post">
						<h2>${lis.grammarname}</h2>
						<img src="UploadImage/${lis.grammarimage}">
						<p>
							<c:set var="kq1" value="${fn:replace(lis.content,kitutrongdatabase1,kitutronghtml1)}" />
							<c:out value="${kq1}" escapeXml="false" />

						</p>
					</div>
				</c:forEach>
				<!--/.Blog Post-->
			</div>


			<!--==================-->
		</div>
	</div>
	<%
	if(session.getAttribute("sessionuser") == null) {
	%>
	<div class="container" class="disabled">
		<div class="row">
			<span class="span6">
				<form>
					<fieldset>

						<textarea class="input-xxlarge" rows="10"
							placeholder="Your Message" disabled></textarea>
						<br>
						<button type="submit" class="btn" disabled>Submit</button>
					</fieldset>
				</form>
			</span>
		</div>
	</div>

	<%
	} else {
		
	 %>

	<div class="container">
		<form name="formbinhluan">
			<div class="row">
				<span class="span6">
					<form>
						<fieldset>
							<textarea class="input-xxlarge" rows="10"
								placeholder="Your Message" name="content"></textarea>
							<br>
							<button type="button" class="btn" onclick="Binhluan()">Submit</button>
						</fieldset>
					</form>
				</span>
			</div>
		</form>
	</div>

	<%
	}
	 %>
	 
	 <div class="reading_description" style="overflow: auto; height: 300px; width:550px;" >
							<div id="listcomment">				
									<c:forEach items="${listcommentgrammar}" var="list"> 
											<h4 style="background-color:yellow" class="input-large">${list.name}</h4>
							
											<p style="background-color:powderblue; height:100px" class="input-xxlarge">
												${list.cmtGrammarContent}
											</p>
									</c:forEach>
										
							</div>
						</div>	

 	

	<jsp:include page="footer.jsp" />
</body>
</html>
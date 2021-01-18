<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Làm bài thi toeic</title>

<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
<link href="Template/Frontend/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="Template/Frontend/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="Template/Font/font-awesome.min.css" />
<!-- Fav and touch icons -->

<link rel="apple-touch-icon-precomposed"
	href="ico/apple-touch-icon-57-precomposed.png">
<script src="Template/Frontend/js/jquery-1.js"></script>
<script src="Template/Frontend/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="Jqueryphantrang/css/style.css" media="screen" />
<style>
.demo {
	width: 580px;
	padding: 10px;
	margin: 10px auto;
	border: 1px solid #fff;
	background-color: #f7f7f7;
}

.pagedemo {
	border: 1px solid #CCC;
	width: 310px;
	margin: 2px;
	padding: 50px 10px;
	text-align: center;
	background-color: white;
}

.khoangcach {
	padding-top: 100px;
}
</style>


<script type="text/javascript">
			
			
					function Xuatketqua()
					{
						var xhttp;
						var kq = document.myform.radio.value;
						
						var url = "LamBaiTapNgheController?kq="+kq+"&num="+<%=request.getAttribute("numberPage")%>+"&listenexerciseid="+<%=request.getAttribute("listenexerciseid")%>;
						
						if (window.XMLHttpRequest) 
						{        
						    xhttp = new XMLHttpRequest(); 
						} 
						
						xhttp.onreadystatechange= function()
						{
							if (xhttp.readyState == 4)
							{
								var data = xhttp.responseText;
								document.getElementById("ketqualambtnghe").innerHTML=data;
							}
							
						}
						
						xhttp.open("POST",url,true);
						xhttp.send();

					}
						
			
			</script>


</head>
<body>

<!--HEADER ROW-->
<jsp:include page="header.jsp"></jsp:include>
<!-- /HEADER ROW -->

<div class="container">
	<!--PAGE TITLE-->

	<div class="row">
		<div class="span12">
			<h3>Làm bài reading</h3>
		</div>
	</div>
	<br />

<div class="row">
				<div class="span12">
				<ul class="thumbnails">
					<li class="span12">
						<div class="thumbnail" >
							<form name="myform" id="ketqualambtnghe">
							<c:forEach items="${danhsachcauhoibtnghe}" var ="list">
								<p>
									Câu ${list.num}. ${list.question}
									
								</p>
								<p>
									<img src= "ImageAudioListening/${list.imagename}" alt="" style="width:250px;height:150px;"/>
									
								</p>
								<p>
									
									<audio controls>
										<source src="ImageAudioListening/${list.audiogg}" type="audio/ogg">
										<source src="ImageAudioListening/${list.audiomp3}" type="audio/mpeg">
									</audio>
									
								</p>
								<p>
						  			<input type="radio" name="radio" value="A"/>
						  			${list.option1}
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="B"/>
						  			${list.option2}
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="C"/>
						  			${list.option3}
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="D"/>
						  			${list.option4}
						  		</p>
					  		</c:forEach>
					  		</form>
					   </div>
					</li>
				  </ul>
				</div>			
			</div>
			
			
			<div class="row">
				<div class="span12">
					
					<div>
						 <a href = "DSPhanNghe?page=1" class="btn btn-info">Thoát</a>
							<c:if test="${numberPage == 1}">
							   <a href = "#" class="btn btn-info disabled">Prev</a>
							   <input type="button" value="Đáp án" class="btn btn-info" onclick="Xuatketqua()"/>
							   <a href = "LamBaiTapNgheController?page=${numberPage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Làm lại</a>
							   <a href = "LamBaiTapNgheController?page=${numberPage+1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Next</a>
							   
						   </c:if>
						   <c:if test="${numberPage == maxPage}">
							   <a href = "LamBaiTapNgheController?page=${numberPage-1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Prev</a>
							   <input type="button" value="Đáp án" class="btn btn-info" onclick="Xuatketqua()"/>
							   <a href = "LamBaiTapNgheController?page=${numberPage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Làm lại</a>
							   
							   <a href ="#" class="btn btn-info disabled">Next</a>
						   </c:if>
						   
						   <c:if test="${numberPage > 1 && numberPage < maxPage}">
							   <a href = "LamBaiTapNgheController?page=${numberPage-1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Prev</a>
							   <input type="button" value="Đáp án" class="btn btn-info" onclick="Xuatketqua()"/>
							   <a href = "LamBaiTapNgheController?page=${numberPage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Làm lại</a>
							   
							   <a href = "LamBaiTapNgheController?page=${numberPage+1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Next</a>
						   </c:if>
					
						   
						
					</div>	
					
			 	</div>
			</div>
	
</div>

<div>
	<jsp:include page="footer.jsp"></jsp:include>
</div>

<script type="text/javascript" src="Jqueryphantrang/jquery-1.3.2.js"></script>
<script src="Jqueryphantrang/jquery.paginate.js" type="text/javascript"></script>

</body>
</html>
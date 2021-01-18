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
<script src="countdown_v5.3/countdown.js"></script>

<script type="text/javascript">

function auto_submit(){
	document.form.submit();
}
function auto_submit1(){
	setTimeout("auto_submit()", 10000);
	
}
</script>

</head>
<body onload="auto_submit1();">

<!--HEADER ROW-->
<jsp:include page="header.jsp"></jsp:include>
<!-- /HEADER ROW -->

<div class="container">
	<!--PAGE TITLE-->

	<div class="row">
		<div class="span12">
			<h3>Làm bài thi toeic hoàn chỉnh</h3>


			<!-- START COUNTDOWN -->
			<script>
				function doneHandler(result) {

					// Result is a javascript Date object, containing the deadline time.
					//
					// 		NOTES on the javascript Date object:
					// 			- months are 0-11, not 1-12
					// 			- "day" is day of the week (0-6) representing sunday-saturday
					// 			- "date" is the day of the month (0-31)

					var year = result.getFullYear();
					var month = result.getMonth() + 1; // bump by 1 for humans
					var day = result.getDate();
					var h = result.getHours();
					var m = result.getMinutes();
					var s = result.getSeconds();

					var UTC = result.toString();

					var output = UTC + "\n";
					output += "year: " + year + "\n";
					output += "month: " + month + "\n";
					output += "day: " + day + "\n";
					output += "h: " + h + "\n";
					output += "m: " + m + "\n";
					output += "s: " + s + "\n";

					//alert(output);
				}

				var myCountdownTest = new Countdown({
					time : 10,
					width : 300,
					height : 50,
					onComplete : doneHandler
				});
			</script>
			<!-- END COUNTDOWN -->
		</div>
	</div>
	<br />


	<div class="row">
		<div class="span8">

			<div id="paginationdemo" class="thumbnail">
				<div class="reading_description"
					style="overflow: auto; height: 400px">
					<div id="p1" class=" _current" style="">
						<h1 class="khoangcach">Nhấn page 2 để xem đề thi</h1>
					</div>

					<c:forEach items="${list}" var="lis">


						<c:if
							test="${(lis.imagequestion==null)&&(lis.audiogg==null) &&(lis.audiomp3==null)&&(lis.paragraph==null)}">
							<div id="p${lis.num+1}" style="display: none;">
								<b>Câu ${lis.num}. ${lis.question}</b>
								<p>${lis.option1}</p>
								<p>${lis.option2}</p>
								<p>${lis.option3}</p>
								<p>${lis.option4}</p>
							</div>
						</c:if>

						<c:if
							test="${(lis.imagequestion!=null)&&(lis.audiogg!=null)&&(lis.audiomp3!=null)}">
							<div id="p${lis.num+1}" style="display: none;">
								<img src="Imageaudiodethi/${lis.imagequestion}" alt="arnh"
									style="width: 550px; height: 250px;" /> <br /> <br /> <br />
								<br />
								<p>
									<audio controls> <source
										src="Imageaudiodethi/${lis.audiogg}" type="audio/ogg">
									<source src="Imageaudiodethi/${lis.audiomp3}" type="audio/mpeg"></audio>
								</p>
								<b>Câu ${lis.num}. ${lis.question}</b>
								<p>${lis.option1}</p>
								<p>${lis.option2}</p>
								<p>${lis.option3}</p>
								<p>${lis.option4}</p>
							</div>
						</c:if>

						<c:if
							test="${(lis.imagequestion==null)&&(lis.audiogg==null)&&(lis.audiomp3==null)&&(lis.paragraph!=null)}">
							<div id="p${lis.num+1}" style="display: none;">
								<c:set var="kq1"
									value="${fn:replace(lis.paragraph,kitutrongdatabase1,kitutronghtml1)}" />
								<c:out value="${kq1}" escapeXml="false" />
								<br /> <br /> <b>Câu ${lis.num}. ${lis.question}</b>
								<p>${lis.option1}</p>
								<p>${lis.option2}</p>
								<p>${lis.option3}</p>
								<p>${lis.option4}</p>

							</div>
						</c:if>

					</c:forEach>


				</div>

			</div>
			<br />
			<div id="demo5"></div>
		</div>
	<form action="LamBaiThiController?examinationid=<%=request.getAttribute("examinationid")%>&memberid=<%=session.getAttribute("sessionuserid")%>" method="POST" name="form">
		<div class="span4">
			<div class="thumbnail">
				<div class="reading_description"
					style="overflow: auto; height: 400px">
					<c:forEach items="${list}" var="lis">
									&nbsp;
									<div class="span1">${lis.num}.</div>
									A. <input type="radio" name="ans[${lis.num}]" value="A"> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									B. <input type="radio" name="ans[${lis.num}]" value="B"> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									C. <input type="radio" name="ans[${lis.num}]" value="C"> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									D. <input type="radio" name="ans[${lis.num}]" value="D"> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
						<br />
					</c:forEach>
				</div>
			</div>
			<br /> <input type="submit" class="btn btn-primary" value="Nộp bài" />
		</div>
		</form>
	</div>
</div>

<div>
	<jsp:include page="footer.jsp"></jsp:include>
</div>

<script type="text/javascript" src="Jqueryphantrang/jquery-1.3.2.js"></script>
<script src="Jqueryphantrang/jquery.paginate.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$("#demo5").paginate(
				{
					count : 10,
					start : 1,
					display : 5,
					border : true,
					border_color : '#fff',
					text_color : '#fff',
					background_color : 'black',
					border_hover_color : '#ccc',
					text_hover_color : '#000',
					background_hover_color : '#fff',
					images : false,
					mouse : 'press',
					onChange : function(page) {
						$('._current', '#paginationdemo').removeClass(
								'_current').hide();
						$('#p' + page).addClass('_current').show();
					}
				});
	});
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kết quả</title>

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

	<!-- 
	<c:forEach items="${listAnswer}" var="lis">

	<h3>Cau hoi : ${lis.num} dap an cua ban la : ${lis.correct}</h3> 
	<br/>
	<p> so dap an dung cua ban la :request.getAttribute("dapandung")g") %> </p>
	<p>time =request.getAttribute("data")a") %> </p>
	</c:forEach>
	
	-->

	<div class="container">
			  <!--PAGE TITLE-->
			<br/>
			
		
		  <!-- /. PAGE TITLE-->
		  
			<div class="row">	
						<div class="span9">	
							<h4>
								Đáp án đúng
							</h4>	
						</div>
						<div class="span3">	
							<h4>
								Đáp án người dùng
							</h4>	
						</div>
						<div class="span9">			
								<div class="thumbnail">
									<div class="reading_description" style="overflow: auto; height: 400px" >
										<c:forEach items= "${listCorrectAnswer}" var= "listcorrectanswer">
										
											<c:if test="${(listcorrectanswer.imagequestion!=null)&&(listcorrectanswer.audiogg!=null)&&(listcorrectanswer.audiomp3!=null)}">
													<img src= "Imageaudiodethi/${listcorrectanswer.imagequestion}" alt="" style="width:250px;height:150px;"/>
													<br/>
													<br/>
													<p>
														<audio controls>
																<source src="Imageaudiodethi/${listcorrectanswer.audiogg}" type="audio/ogg">
																<source src="Imageaudiodethi/${listcorrectanswer.audiomp3}" type="audio/mpeg">
														</audio>
													</p>
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
													
														 <p style="color:red">Câu ${listcorrectanswer.num}. Đáp án A đúng</p>
													</c:if>
													
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án B đúng</p>
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án C đúng</p>	
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án D đúng</p>
													
													</c:if>
															
											</c:if>
											
											<c:if test="${(listcorrectanswer.imagequestion==null)&&(listcorrectanswer.audiogg!=null)&&(listcorrectanswer.audiomp3!=null)}">
													
													<br/>
													<br/>
													<p>
														<audio controls>
																<source src="Imageaudiodethi/${listcorrectanswer.audiogg}" type="audio/ogg">
																<source src="Imageaudiodethi/${listcorrectanswer.audiomp3}" type="audio/mpeg">
														</audio>
													</p>
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
													
														 <p style="color:red">Câu ${listcorrectanswer.num}. Đáp án A đúng</p>
													</c:if>
													
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án B đúng</p>
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án C đúng</p>	
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án D đúng</p>
													
													</c:if>
															
											</c:if>
											
											<c:if test="${(listcorrectanswer.imagequestion==null)&&(listcorrectanswer.audiogg==null)&&(listcorrectanswer.audiomp3==null)}">
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
														
														<p>${listcorrectanswer.num}. 
																
																<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																<c:out value= "${kq}" escapeXml="false"/>		
														</p>
														<p>${listcorrectanswer.question}</p>
														  <img alt="" src="Image/correct.png"> ${listcorrectanswer.option1}
														<br>
														<br>
														  ${listcorrectanswer.option2}
														<br>
														<br>
														  ${listcorrectanswer.option3}
														<br>
														<br>
														  ${listcorrectanswer.option4}
														<br>
														<br>
												
												
										
													</c:if>
										
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
																
																<p>${listcorrectanswer.num}. 
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  <img alt="" src="Image/correct.png">${listcorrectanswer.option2}
																<br>
																<br>
																  ${listcorrectanswer.option3}
																<br>
																<br>
																  ${listcorrectanswer.option4}
																<br>
																<br>
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
															
																<p>${listcorrectanswer.num}.
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  ${listcorrectanswer.option2}
																<br>
																<br>
																  <img alt="" src="Image/correct.png">${listcorrectanswer.option3}
																<br>
																<br>
																  ${listcorrectanswer.option4}
																<br>
																<br>
															
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
													
															
															
																<p>${listcorrectanswer.num}.
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  ${listcorrectanswer.option2}
																<br>
																<br>
																  ${listcorrectanswer.option3}
																<br>
																<br>
																  <img alt="" src="Image/correct.png">${listcorrectanswer.option4}
																<br>
																<br>
															
															
													
													</c:if>
															
											</c:if>
										
								
									   </c:forEach>
									
									</div>
								</div>						
						</div>	
						
						
						<div class="span3">			
								<div class="thumbnail">
									<div class="reading_description" style="overflow: auto; height: 400px" >
											<c:forEach items="${listAnswer}" var="list">
													<div class="span1">
														${list.num}. 
													</div>
													
																
													${list.correct}
													
													<br/>
											</c:forEach>
									</div>
								</div>
								<br/>
								<input type="button" class="btn btn-primary" value="Xem kết quả thi" data-toggle="modal" data-target="#exampleModal"/>
								<a href="DsDeThiForward?page=1" role="button" class="btn btn-primary" >Làm lại</a>
								
								
						</div>
			</div>
		</div>
	<jsp:include page="footer.jsp" />
	
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Kết quả thi</h5>
				</div>
				<div class="modal-body">
							<c:forEach items="${ketquathi}" var = "list">
								<div class="media">
									
									<div class="media-body">
										<h4>
											Số câu đúng: ${list.correctanswernum}
											<br/>
												- Số câu đúng phần nghe: ${list.correctanswerlisten}
											<br/>
												- Số câu đúng phần đọc: ${list.correctanswerread}
										</h4> 					
									</div>
								</div>
							
					          	<div class="media">
									
									<div class="media-body">
										<h4>
											Số câu sai: ${list.incorrectanswernum}
										</h4> 					
									</div>
								</div>
								
							
							</c:forEach>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>


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
	function search() {
		var xhttp;
		var name = document.myform.name.value;
		var url = "SearchController?name="+name;
		
		if(name!="") {
			if (window.XMLHttpRequest) {
				xhttp = new XMLHttpRequest();
			} else {
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}

			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4) {
					var data = xhttp.responseText;
					document.getElementById("ketqua").innerHTML = data;
				}
			}
			xhttp.open("POST", url, true);
			xhttp.send();
		}
		else {
			document.getElementById("ketqua").innerHTML = "";
		}
		
	}
</script>

</head>
<body>


	<!--HEADER ROW-->
	<jsp:include page="header.jsp" />
	<!-- /HEADER ROW -->

	<div class="container">
		<div class="row">
			<div>
				<form name="myform">
				<input type="text" placeholder="tìm kiếm" style="width:500px" name="name" onkeyup="search()" />
				</form>
			</div>
		</div>
	</div>


	<div class="container" id="ketqua">
		<!--Carousel -->
		<div id="myCarousel" class="carousel slide">
			<div class="carousel-inner">
				<div class="active item">
					<div class="container">
						<div class="row">
							<div class="span6">
								<div class="carousel-caption">
									<h1>Toeic English</h1>
									<p class="lead">Chào mừng các bạn đến ông Nù vê lốc</p>
									<a class="btn btn-large btn-primary" href="#">Hãy tham gia
										!!!</a>
								</div>
							</div>
							<div class="span6">
								<img src="Template/Frontend/img/slide/slide1.png">
							</div>
						</div>
					</div>
				</div>


				<c:forEach items="${listslidebanner}" var="list">
					<div class="item">
						<div class="container">
							<div class="row">
								<div class="span6">
									<div class="carousel-caption">
										<h1>${list.slideName}</h1>
										<p class="lead">${list.slideContent}</p>
										<a class="btn btn-large btn-primary" href="#">Hãy tham gia
											!!!</a>
									</div>
								</div>
								<div class="span6">
									<img src="Template/Frontend/img/slide/${list.slideImage}.jpg">
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
			<!-- Carousel nav -->
			<a class="carousel-control left " href="#myCarousel"
				data-slide="prev"><i class="icon-chevron-left"></i></a> <a
				class="carousel-control right" href="#myCarousel" data-slide="next"><i
				class="icon-chevron-right"></i></a>
			<!-- /.Carousel nav -->

		</div>
		<!-- /Carousel -->



		<!-- Feature 
  ==============================================-->


		<div class="row feature-box">
			<div class="span12 cnt-title">
				<h1>Chúng tôi cung cấp giao diện học và thi thân thiện với
					người dùng</h1>
				<span>Ok các bạn nhá</span>
			</div>

			<div class="span4">
				<img src="Template/Frontend/img/icon3.png">
				<h2>Hướng dẫn từ vựng và ngữ pháp</h2>
				<p>Chỉ cần nhìn không cần học mà cũng đủ đã để hiểu rồi</p>

				<a href="" data-toggle="modal" data-target="#exampleModal">Chi
					tiết &rarr;</a>

			</div>

			<div class="span4">
				<img src="Template/Frontend/img/icon2.png">
				<h2>Bài tập phần nghe và đọc</h2>
				<p>Chúng tôi cung cấp mọi thứ mà các quý vị cần.</p>
				<a href="#" data-toggle="modal" data-target="#exampleModal1">Chi tiết &rarr;</a>
			</div>

			<div class="span4">
				<img src="Template/Frontend/img/icon1.png">
				<h2>Đề thi thử</h2>
				<p>Chúng tôi cung cấp các đề thi giống y chang đề thi thật.</p>
				<a href="DsDeThiForward?page=1">Chi tiết &rarr;</a>
			</div>
		</div>


		<!-- /.Feature -->

		<div class="hr-divider"></div>

		<!-- Row View -->


		<div class="row">
			<div class="span6">
				<img src="Template/Frontend/img/responsive.png">
			</div>

			<div class="span6">
				<img class="hidden-phone" src="Template/Frontend/img/icon4.png"
					alt="">
				<h1>Fully Responsive</h1>
				<p>Pellentesque habitant morbi tristique senectus et netus et
					malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat
					vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit
					amet quam egestas semper. Aenean ultricies mi vitae est. Mauris
					placerat eleifend leo.</p>
				<a href="#">Read More &rarr;</a>
			</div>
		</div>


	</div>


	<!-- /.Row View -->



	<!-- Begin Footer-->
	<jsp:include page="footer.jsp" />
	<!-- End Footer -->

	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Chọn từ vựng
						hoặc ngữ pháp</h5>
				</div>
				<div class="modal-body">
					<div class="span6">
						<img src="Template/Frontend/Image/grammar.png"> <a
							class="btn btn-success"
							href="ViewbaitaphuongdannguphapForward?pageid=1">Hãy nhấp vào
							em đi</a>
					</div>
					<div class="span6">
						<img src="Template/Frontend/Image/vocabulary.jpg"> <a
							class="btn btn-success" href="VocabularyTopicsForward?page=1">Hãy nhấp vào em đi</a>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Chọn phần đọc hay phần nghe</h5>
				</div>
				<div class="modal-body">
					<div class="span6">
						<img src="Template/Frontend/Image/reading.jpg"> <a
							class="btn btn-success"
							href="ListPhanDocForward?page=1">Hãy nhấp vào em đi</a>
					</div>
					<div class="span6">
						<img src="Template/Frontend/Image/Listening.png"> <a
							class="btn btn-success" href="ListPhanNgheForward?page=1">Hãy nhấp vào em đi</a>
					</div>
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
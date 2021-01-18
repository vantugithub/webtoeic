<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login page</title>
<!--   <link rel="stylesheet" href="Template/assets/css/bootstrap.min.css" /> -->
<link rel="stylesheet"
	href="Template/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="Template/Font/font-awesome.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="Template/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace-rtl.min.css" />
<script src="Template/assets/js/ace-extra.min.js"></script>
</head>
<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="ace-icon fa fa-leaf green"></i> <span class="red">Nù</span>
								<span class="white" id="id-text2">OPPA</span>
							</h1>
							<h4 class="blue" id="id-company-text">&copy; HIHI</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i> Nhập tài khoản
										</h4>

										<div class="space-6"></div>

										<form  action="LoginController" method="POST">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" placeholder="Username"
														name="membername" /> <i class="ace-icon fa fa-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control"
														placeholder="Password" name="memberpass" /> <i
														class="ace-icon fa fa-lock"></i>
												</span>
												</label> <label class="block clearfix" style="color: red;"> <%=request.getAttribute("alert") == null ? " " : request.getAttribute("alert")%>
												</label>

												<div class="space"></div>

												<div class="clearfix">

													<button type="submit"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i> <span
															class="bigger-110">Login</span>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>



										<div class="space-6"></div>
									</div>
									<!-- /.widget-main -->

									<div class="toolbar clearfix">
										<div>
											<a href="ControllerForward" class="forgot-password-link">
												<i class="ace-icon fa fa-arrow-left"></i> Trở về trang chủ
											</a>
										</div>

										<div>
											<a href="RegisterForward" class="user-signup-link"> Đăng
												ký <i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div id="signup-box" class="signup-box widget-box no-border">
								<div class="widget-body">
									<div class="toolbar center">
										<a href="#" data-target="#login-box"
											class="back-to-login-link"> <i
											class="ace-icon fa fa-arrow-left"></i> Back to login
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="Template/assets/js/jquery-2.1.4.min.js"></script>

	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='Template/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>

	<script type="text/javascript">
		jQuery(function($) {
			$(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			});
		});

		//you don't need this, just used for changing background
		jQuery(function($) {
			$('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');

				e.preventDefault();
			});
			$('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');

				e.preventDefault();
			});
			$('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');

				e.preventDefault();
			});

		});
	</script>
</body>
</html>
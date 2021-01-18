<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm ảnh và audio</title>
<link rel="stylesheet"
	href="Template/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="Template/Font/font-awesome.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="Template/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace-rtl.min.css" />
<script src="Template/assets/js/ace-extra.min.js"></script>

<script src="bootstrap/js/jquery-3.4.1.min.js"></script>

<script>
	$(document)
			.ready(
					function() {
						//add more file components if Add is clicked
						$('#addFile')
								.click(
										function() {
											var fileIndex = $('#fileTable tr')
													.children().length - 1;
											$('#fileTable')
													.append(
															'<tr><td>'
																	+ '   <input type="file" name="files['+ fileIndex +']" />'
																	+ '</td></tr>');
										});

					});
</script>

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
											<i class="ace-icon fa fa-coffee green"></i> Link of image
											file
										</h4>
										<div class="space-6"></div>
										<form method="POST" action="AddAudioAndImageListeningController" enctype="multipart/form-data">
											<fieldset>
												<br> <br>
												<div align="center">
													<p><%=request.getAttribute("test")!=null ? request.getAttribute("test") : "" %></p>

													<table id="fileTable">
														<tr>
															<td><input name="files[0]" type="file" /></td>
														</tr>
														<tr>
															<td><input name="files[1]" type="file" /></td>
														</tr>
													</table>
													<br /> <input type="submit" value="Upload" /> <input
														id="addFile" type="button" value="Add File" /> <br />
												</div>
											</fieldset>
										</form>
										<div class="space-6"></div>
									</div>
									<!-- /.widget-main -->
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý phần hướng dẫn ngữ pháp</title>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="Template/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="Template/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="Template/assets/css/jquery-ui.custom.min.css" />

<!-- text fonts -->
<link rel="stylesheet" href="Template/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="Template/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

<!-- ace styles -->
<link rel="stylesheet" href="Template/assets/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<link rel="stylesheet" href="Template/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace-rtl.min.css" />


<script src="Template/assets/js/ace-extra.min.js"></script>

</head>
<body>
<body class="no-skin">


	<!-- header -->
	<jsp:include page="Header.jsp" />
	<!-- end header -->
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>

		<!-- begin menu -->

		<jsp:include page="Menu.jsp" />

		<!-- end menu -->


		<!-- Begin content -->

		<div class="main-content">
			<div class="main-content-inner">

				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">

						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
						</li>
						<li><a href="">Quản lý phần ngữ pháp</a></li>
						<li class="active">Hướng dẫn ngữ pháp</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<form
					action="Contentgrammarcontroller?id=<%=request.getAttribute("id")%>"
					method="POST">
					<div class="page-content">

						<!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-sm-7">
										<h4 class="header green">Soạn content</h4>
										<div class="widget-box widget-color-blue">
											<div class="widget-header widget-header-small"></div>
											<div class="widget-body">
												<div class="widget-main no-padding">
													<textarea data-hidden-buttons="Image" name="content" data-provide="markdown"
														data-iconlibrary="fa" rows="10">
														<%=request.getAttribute("content")%>
														</textarea>
												</div>
												<div class="widget-toolbox padding-4 clearfix">
													<div class="btn-group pull-left">
														<button class="btn btn-sm btn-info">
															<i class="ace-icon fa fa-times bigger-125"></i> Cancel
														</button>
													</div>
													<div class="btn-group pull-right">
														<button type="submit" class="btn btn-sm btn-purple">
															<i class="ace-icon fa fa-floppy-o bigger-125"></i> Save
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- end maincontent -->

		<!-- End content -->


		<!-- Begin footer -->
		<jsp:include page="Footeradmin.jsp" />
		<!-- End footer -->
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>

	<script src="Template/assets/js/jquery-2.1.4.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='Template/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="Template/assets/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->
	<script src="Template/assets/js/jquery-ui.custom.min.js"></script>
	<script src="Template/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="Template/assets/js/markdown.min.js"></script>
	<script src="Template/assets/js/bootstrap-markdown.min.js"></script>
	<script src="Template/assets/js/jquery.hotkeys.index.min.js"></script>
	<script src="Template/assets/js/bootstrap-wysiwyg.min.js"></script>
	<script src="Template/assets/js/bootbox.js"></script>

	<!-- ace scripts -->
	<script src="Template/assets/js/ace-elements.min.js"></script>
	<script src="Template/assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {

			$('textarea[data-provide="markdown"]').each(function() {
				var $this = $(this);

				if ($this.data('markdown')) {
					$this.data('markdown').showEditor();
				} else
					$this.markdown()

				$this.parent().find('.btn').addClass('btn-white');
			})

			function showErrorAlert(reason, detail) {
				var msg = '';
				if (reason === 'unsupported-file-type') {
					msg = "Unsupported format " + detail;
				} else {
				}
				$(
						'<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
								+ '<strong>File upload error</strong> '
								+ msg
								+ ' </div>').prependTo('#alerts');
			}

			$('#editor1').ace_wysiwyg({
				toolbar : [ 'font', null, 'fontSize', null, {
					name : 'bold',
					className : 'btn-info'
				}, {
					name : 'italic',
					className : 'btn-info'
				}, {
					name : 'strikethrough',
					className : 'btn-info'
				}, {
					name : 'underline',
					className : 'btn-info'
				}, null, {
					name : 'insertunorderedlist',
					className : 'btn-success'
				}, {
					name : 'insertorderedlist',
					className : 'btn-success'
				}, {
					name : 'outdent',
					className : 'btn-purple'
				}, {
					name : 'indent',
					className : 'btn-purple'
				}, null, {
					name : 'justifyleft',
					className : 'btn-primary'
				}, {
					name : 'justifycenter',
					className : 'btn-primary'
				}, {
					name : 'justifyright',
					className : 'btn-primary'
				}, {
					name : 'justifyfull',
					className : 'btn-inverse'
				}, null, {
					name : 'createLink',
					className : 'btn-pink'
				}, {
					name : 'unlink',
					className : 'btn-pink'
				}, null, {
					name : 'insertImage',
					className : 'btn-success'
				}, null, 'foreColor', null, {
					name : 'undo',
					className : 'btn-grey'
				}, {
					name : 'redo',
					className : 'btn-grey'
				} ],
				'wysiwyg' : {
					fileUploadError : showErrorAlert
				}
			}).prev().addClass('wysiwyg-style2');

			$('#editor2').css({
				'height' : '200px'
			}).ace_wysiwyg(
					{
						toolbar_place : function(toolbar) {
							return $(this).closest('.widget-box').find(
									'.widget-header').prepend(toolbar).find(
									'.wysiwyg-toolbar').addClass('inline');
						},
						toolbar : [ 'bold', {
							name : 'italic',
							title : 'Change Title!',
							icon : 'ace-icon fa fa-leaf'
						}, 'strikethrough', null, 'insertunorderedlist',
								'insertorderedlist', null, 'justifyleft',
								'justifycenter', 'justifyright' ],
						speech_button : false
					});

			$('[data-toggle="buttons"] .btn').on(
					'click',
					function(e) {
						var target = $(this).find('input[type=radio]');
						var which = parseInt(target.val());
						var toolbar = $('#editor1').prev().get(0);
						if (which >= 1 && which <= 4) {
							toolbar.className = toolbar.className.replace(
									/wysiwyg\-style(1|2)/g, '');
							if (which == 1)
								$(toolbar).addClass('wysiwyg-style1');
							else if (which == 2)
								$(toolbar).addClass('wysiwyg-style2');
							if (which == 4) {
								$(toolbar).find('.btn-group > .btn').addClass(
										'btn-white btn-round');
							} else
								$(toolbar).find('.btn-group > .btn-white')
										.removeClass('btn-white btn-round');
						}
					});

			if (typeof jQuery.ui !== 'undefined' && ace.vars['webkit']) {

				var lastResizableImg = null;
				function destroyResizable() {
					if (lastResizableImg == null)
						return;
					lastResizableImg.resizable("destroy");
					lastResizableImg.removeData('resizable');
					lastResizableImg = null;
				}

				var enableImageResize = function() {
					$('.wysiwyg-editor')
							.on(
									'mousedown',
									function(e) {
										var target = $(e.target);
										if (e.target instanceof HTMLImageElement) {
											if (!target.data('resizable')) {
												target
														.resizable({
															aspectRatio : e.target.width
																	/ e.target.height,
														});
												target.data('resizable', true);

												if (lastResizableImg != null) {
													//disable previous resizable image
													lastResizableImg
															.resizable("destroy");
													lastResizableImg
															.removeData('resizable');
												}
												lastResizableImg = target;
											}
										}
									})
							.on(
									'click',
									function(e) {
										if (lastResizableImg != null
												&& !(e.target instanceof HTMLImageElement)) {
											destroyResizable();
										}
									}).on('keydown', function() {
								destroyResizable();
							});
				}

				enableImageResize();
			}

		});
	</script>
</body>
</body>
</html>
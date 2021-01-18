<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page admin</title>
<link href="Template/Frontend/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="Template/Font/font-awesome.min.css" />
<meta name="description"
	content="responsive photo gallery using colorbox" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" href="Template/assets/css/bootstrap.min.css" />


<link rel="stylesheet"href="Template/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace.min.css"

	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="Template/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace-ie.min.css" />
<script src="Template/assets/js/ace-extra.min.js"></script>
<style type="text/css">
.paddingtop-image{
padding-top: 50px;
}
</style>
</head>
<body class="no-skin">
<!-- header -->
<jsp:include page="Header.jsp"/>
<!-- end header -->
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
	   		<!-- begin menu -->
		<jsp:include page="Menu.jsp"/>
		<!-- end menu -->
		<!-- Begin content -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
						</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<!-- /.page-header -->
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<center>
							<img class="paddingtop-image" src="Template/assets/16679.jpg" width="700px" height="250px" >
							</center>
						</div>
					</div>
				</div>
			</div>
		</div> <!-- end maincontent -->
		<!-- End content -->
		<!-- Begin footer -->
		<jsp:include page="Footeradmin.jsp"/>
		<!-- End footer -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
	</div> 
	<script src="Template/assets/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='Template/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
	<script src="Template/assets/js/bootstrap.min.js"></script>
	<script src="Template/assets/js/jquery.colorbox.min.js"></script>
	<script src="Template/assets/js/ace-elements.min.js"></script>
	<script src="Template/assets/js/ace.min.js"></script>
	<script type="text/javascript">
			jQuery(function($) {
	var $overflow = '';
	var colorbox_params = {
		rel: 'colorbox',
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="ace-icon fa fa-arrow-left"></i>',
		next:'<i class="ace-icon fa fa-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			$overflow = document.body.style.overflow;
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = $overflow;
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");
	
	
	$(document).one('ajaxloadstart.page', function(e) {
		$('#colorbox, #cboxOverlay').remove();
   });
})
		</script>

</body>
</html>
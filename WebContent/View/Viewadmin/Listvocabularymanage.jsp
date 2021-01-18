<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý từ vựng</title>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="Template/Frontend/css/style.css" rel="stylesheet">

<link rel="stylesheet" href="Template/Font/font-awesome.min.css" />
<meta name="description"
	content="responsive photo gallery using colorbox" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<link rel="stylesheet" href="Template/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="Template/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace.min.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="Template/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="Template/assets/css/ace-rtl.min.css" />

<link rel="stylesheet" href="Template/assets/css/ace-ie.min.css" />
<script src="Template/assets/js/ace-extra.min.js"></script>

</head>
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

						<li><a href="#">Quản lý từ vựng </a></li>
						<li class="active">Thêm chủ đề từ vựng</li>

					</ul>
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								<div class="col-xs-12">
									<table id="simple-table"
										class="table  table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">Id</th>
												<th class="center">Name Vocabulary Topics</th>
												<th class="center">Image</th>
												<th class="center">Delete</th>
												<th class="center">Add</th>
												<th class="center">Checked</th>
											</tr>
										</thead>
										<tbody>
									 		<c:forEach items="${list}" var="lis">
												<tr>
													<td class="center">${lis.vocabularyguidelineid}</td>
													<td class="center">${lis.vocabularyname}</td>
													<td class="center">${lis.vocabularyimage}</td>
													<td class="center"><a class="red" href="#"> <i
															class="ace-icon fa fa-trash-o bigger-130"></i>
													</a></td>
													<td class="center"><a
														href="addFileVocabularyForward?vocabularyguidelineid=${lis.vocabularyguidelineid}"
														class="green"> <i
															class="ace-icon fa fa-pencil bigger-130"></i>
													</a></td>
													<td>
													<div class="col-xs-6 col-sm-3">
														<ul class="list-unstyled">
																<c:if test="${lis.status == 1}">
																<li><i class="ace-icon fa fa-check-square-o"></i>
																	</li>
															</c:if>
															<c:if test="${lis.status == null}">
																<li><i class="ace-icon fa fa-square-o"></i>
																	</li>
															</c:if>
														</ul>
														</div>
													</td>
												</tr>
											</c:forEach>   
										</tbody>
									</table>
								</div>
							</div>
							
							
							
								<!--Pagination-->
								
 	<div class="row">
		<div class="span9">
			
			<div class="pagination">

				<ul>
					<c:if test="${numberpage == 1}">

						<a class="w3-button"
							href="ShowExamList?page=${numberpage+1}">Next</a>
					</c:if>

					<c:if test="${numberpage == maxPage}">
						<a class="w3-button"
							href="ShowExamList?page=${numberpage-1}">Prev</a>
					</c:if>

					<c:if test="${numberpage > 1 && numberpage < maxPage}">
						<a class="w3-button"
							href="ShowExamList?page=${numberpage-1}">Prev</a>
					
						<a class="w3-button"
							href="ShowExamList?page=${numberpage+1}">Next</a>

					</c:if>
					
				</ul>

			</div>
			
		</div>
	</div>        
	
	
	
<!--/.Pagination-->
							
							
							
							<div class="row">
								<div class="col-xs-12">
									<button type="button" class="btn btn-white btn-info btn-bold "
										data-toggle="modal" data-target="#exampleModal">
										<i class="ace-icon fa fa-floppy-o bigger-120 blue"></i> Thêm
										chủ đề từ vựng
									</button>
									
									<a href="AddImageAndAudioVocabularyController" role="button" class="btn btn-white btn-warning btn-bold">Thêm audio và hình ảnh từ vựng</a>
									
								</div>
							</div>
						</div>
					</div>
				</div>
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

		<form action="AddVocabularyTopicsController" method="POST">
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Tên chủ đề từ vựng</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>

						<div class="modal-body">
							<label>Nhập chủ đề từ vựng: </label> <input
								id="vocabularyname" name="vocabularyname" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Thêm chủ đề</button>
						</div>

					</div>
				</div>
			</div>
		</form>

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
	<script src="Template/assets/js/jquery.dataTables.min.js"></script>
	<script src="Template/assets/js/jquery.dataTables.bootstrap.min.js"></script>
	<script src="Template/assets/js/dataTables.buttons.min.js"></script>
	<script src="Template/assets/js/buttons.flash.min.js"></script>
	<script src="Template/assets/js/buttons.html5.min.js"></script>
	<script src="Template/assets/js/buttons.print.min.js"></script>
	<script src="Template/assets/js/buttons.colVis.min.js"></script>
	<script src="Template/assets/js/dataTables.select.min.js"></script>

	<!-- ace scripts -->
	<script src="Template/assets/js/ace-elements.min.js"></script>
	<script src="Template/assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			//initiate dataTables plugin
			var myTable = $('#dynamic-table')
			//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
			.DataTable({
				bAutoWidth : false,
				"aoColumns" : [ {
					"bSortable" : false
				}, null, null, null, null, null, {
					"bSortable" : false
				} ],
				"aaSorting" : [],


				select : {
					style : 'multi'
				}
			});

			$.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';

			new $.fn.dataTable.Buttons(
					myTable,
					{
						buttons : [
								{
									"extend" : "colvis",
									"text" : "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
									"className" : "btn btn-white btn-primary btn-bold",
									columns : ':not(:first):not(:last)'
								},
								{
									"extend" : "copy",
									"text" : "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
									"className" : "btn btn-white btn-primary btn-bold"
								},
								{
									"extend" : "csv",
									"text" : "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
									"className" : "btn btn-white btn-primary btn-bold"
								},
								{
									"extend" : "excel",
									"text" : "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
									"className" : "btn btn-white btn-primary btn-bold"
								},
								{
									"extend" : "pdf",
									"text" : "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
									"className" : "btn btn-white btn-primary btn-bold"
								},
								{
									"extend" : "print",
									"text" : "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
									"className" : "btn btn-white btn-primary btn-bold",
									autoPrint : false,
									message : 'This print was produced using the Print button for DataTables'
								} ]
					});
			myTable.buttons().container().appendTo($('.tableTools-container'));

			//style the message box
			var defaultCopyAction = myTable.button(1).action();
			myTable
					.button(1)
					.action(
							function(e, dt, button, config) {
								defaultCopyAction(e, dt, button, config);
								$('.dt-button-info')
										.addClass(
												'gritter-item-wrapper gritter-info gritter-center white');
							});

			var defaultColvisAction = myTable.button(0).action();
			myTable
					.button(0)
					.action(
							function(e, dt, button, config) {

								defaultColvisAction(e, dt, button, config);

								if ($('.dt-button-collection > .dropdown-menu').length == 0) {
									$('.dt-button-collection')
											.wrapInner(
													'<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
											.find('a').attr('href', '#').wrap(
													"<li />")
								}
								$('.dt-button-collection').appendTo(
										'.tableTools-container .dt-buttons')
							});

			////

			setTimeout(function() {
				$($('.tableTools-container')).find('a.dt-button').each(
						function() {
							var div = $(this).find(' > div').first();
							if (div.length == 1)
								div.tooltip({
									container : 'body',
									title : div.parent().text()
								});
							else
								$(this).tooltip({
									container : 'body',
									title : $(this).text()
								});
						});
			}, 500);

			myTable.on('select', function(e, dt, type, index) {
				if (type === 'row') {
					$(myTable.row(index).node()).find('input:checkbox').prop(
							'checked', true);
				}
			});
			myTable.on('deselect', function(e, dt, type, index) {
				if (type === 'row') {
					$(myTable.row(index).node()).find('input:checkbox').prop(
							'checked', false);
				}
			});

			/////////////////////////////////
			//table checkboxes
			$('th input[type=checkbox], td input[type=checkbox]').prop(
					'checked', false);

			//select/deselect all rows according to table header checkbox
			$(
					'#dynamic-table > thead > tr > th input[type=checkbox], #dynamic-table_wrapper input[type=checkbox]')
					.eq(0).on('click', function() {
						var th_checked = this.checked;//checkbox inside "TH" table header

						$('#dynamic-table').find('tbody > tr').each(function() {
							var row = this;
							if (th_checked)
								myTable.row(row).select();
							else
								myTable.row(row).deselect();
						});
					});

			//select/deselect a row when the checkbox is checked/unchecked
			$('#dynamic-table').on('click', 'td input[type=checkbox]',
					function() {
						var row = $(this).closest('tr').get(0);
						if (this.checked)
							myTable.row(row).deselect();
						else
							myTable.row(row).select();
					});

			$(document).on('click', '#dynamic-table .dropdown-toggle',
					function(e) {
						e.stopImmediatePropagation();
						e.stopPropagation();
						e.preventDefault();
					});

			//And for the first simple table, which doesn't have TableTools or dataTables
			//select/deselect all rows according to table header checkbox
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on(
					'click',
					function() {
						var th_checked = this.checked;//checkbox inside "TH" table header

						$(this).closest('table').find('tbody > tr').each(
								function() {
									var row = this;
									if (th_checked)
										$(row).addClass(active_class).find(
												'input[type=checkbox]').eq(0)
												.prop('checked', true);
									else
										$(row).removeClass(active_class).find(
												'input[type=checkbox]').eq(0)
												.prop('checked', false);
								});
					});

			//select/deselect a row when the checkbox is checked/unchecked
			$('#simple-table').on('click', 'td input[type=checkbox]',
					function() {
						var $row = $(this).closest('tr');
						if ($row.is('.detail-row '))
							return;
						if (this.checked)
							$row.addClass(active_class);
						else
							$row.removeClass(active_class);
					});

			/********************************/
			//add tooltip for small view action buttons in dropdown menu
			$('[data-rel="tooltip"]').tooltip({
				placement : tooltip_placement
			});

			//tooltip placement on right or left
			function tooltip_placement(context, source) {
				var $source = $(source);
				var $parent = $source.closest('table')
				var off1 = $parent.offset();
				var w1 = $parent.width();

				var off2 = $source.offset();
				//var w2 = $source.width();

				if (parseInt(off2.left) < parseInt(off1.left)
						+ parseInt(w1 / 2))
					return 'right';
				return 'left';
			}

			$('.show-details-btn').on(
					'click',
					function(e) {
						e.preventDefault();
						$(this).closest('tr').next().toggleClass('open');
						$(this).find(ace.vars['.icon']).toggleClass(
								'fa-angle-double-down').toggleClass(
								'fa-angle-double-up');
					});

		})
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="sidebar"
			class="sidebar                  responsive                    ace-save-state">
			<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts">

				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="ace-icon fa fa-signal"></i>
					</button>

					<button class="btn btn-info">
						<i class="ace-icon fa fa-pencil"></i>
					</button>

					<button class="btn btn-warning">
						<i class="ace-icon fa fa-users"></i>
					</button>

					<button class="btn btn-danger">
						<i class="ace-icon fa fa-cogs"></i>
					</button>
				</div>
				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>

					<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
				</div>
			</div>

			<ul class="nav nav-list">
				<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-list"></i>
							<span class="menu-text"> Guideline manage </span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="ListgrammarguidlinemanageForward?page=1">
									<i class="menu-icon fa fa-caret-right"></i>
									Ngữ pháp
								</a>

								<b class="arrow"></b>
							</li>

							<li class="">
								<a href="ListvocabularymanageForward?page=1">
									<i class="menu-icon fa fa-caret-right"></i>
									Từ vựng
								</a>

								<b class="arrow"></b>
							</li>
						</ul>
					</li>
			</ul>
			
			<ul class="nav nav-list">
				<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-list"></i>
							<span class="menu-text"> Quản lý đề thi </span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="ShowExamList?page=1">
									<i class="menu-icon fa fa-caret-right"></i>
									Hiển thị danh sách đề thi hoàn chỉnh
								</a>

								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="HienThiDanhSachDeThiDoc?page=1">
									<i class="menu-icon fa fa-caret-right"></i>
									Hiển thị danh sách đề thi đọc
								</a>

								<b class="arrow"></b>
							</li>
							<li class="">
								<a href="HienThiDanhSachDeThiNghe?page=1">
									<i class="menu-icon fa fa-caret-right"></i>
									Hiển thị danh sách đề thi nghe
								</a>

								<b class="arrow"></b>
							</li>
						</ul>
					</li>
			</ul>
			
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i id="sidebar-toggle-icon"
					class="ace-icon fa fa-angle-double-left ace-save-state"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>
		</div>
</body>
</html>
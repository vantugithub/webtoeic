<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<div class="navbar-container ace-save-state" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="AdminForward" class="navbar-brand"> <small> <i
						class="fa fa-leaf"></i> Ace Admin
				</small>
				</a>
			</div>

			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue dropdown-modal"><a
						data-toggle="dropdown" href="#" class="dropdown-toggle"> <img
							class="nav-user-photo"
							src="Template/assets/images/avatars/user.jpg" alt="Jason's Photo" />
							<span class="user-info"> <small>Welcome,</small> <%=session.getAttribute("sessionadmin") %>
						</span> <i class="ace-icon fa fa-caret-down"></i>
					</a>

						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">


							<li class="divider"></li>

							<li><a href="LogoutController"> <i
									class="ace-icon fa fa-power-off"></i> Logout
							</a></li>
						</ul></li>
				</ul>
			</div>

		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta HTTP-EQUIV="Pragma" content="no-cache">
<meta HTTP-EQUIV="Expires" content="-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimal-ui">
<title>Secure It Pro</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/simple-line-icons.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/templatemo_style.css">

<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 20px;
	border-style: solid;
	padding: 5px 5px;
	text-align: left;
}

.tg td  input {
	font-family: Arial, sans-serif;
	color: #000000;
	font-size: 20px;
	border-style: solid;
	padding: 5px 5px;
	text-align: left;
}

.tg th {
	font-family: Arial, sans-serif;
	color: #ffffff;
	font-size: 20px;
	border-style: solid;
	font-weight: normal;
	padding: 5px 20px;
	margin-left: 50px; :
	text-align: left;
}
</style>
<meta name="viewport" content="width=device-width">
</head>
<%
	if (session.getAttribute("session") == null)
		response.sendRedirect("index.jsp");
	String uid = request.getParameter("id");
	String flag = request.getParameter("flag");
	int id = Integer.parseInt(uid);
%>
<body>
	<header class="site-header container animated fadeInDown">
	<div class="header-wrapper">
		<div class="row">
			<div class="col-md-4">
				<div class="site-branding">
					<h1>
						<strong>Secure It Pro</strong>
					</h1>
				</div>
			</div>
			<a href="#" class="toggle-nav hidden-md hidden-lg"> <i
				class="fa fa-bars"></i>
			</a>
			<div class="col-md-8">
				<nav id="nav" class="main-navigation hidden-xs hidden-sm">
				<ul class="main-menu">

					<li><a
						href=<%="\"selectuser.do?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-magnifier"></i>&nbsp;Profile</a></li>
					<li><a href=<%="\"map.jsp?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-user"></i>&nbsp;Find Me</a></li>
					<li><a class="show-1 active homebutton"
						href=<%="\"setting.jsp?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-settings"></i>&nbsp;Settings</a></li>
					<li><a href="logout.do?flag=logout"><i class="icon-logout"></i>&nbsp;Log
							Out</a></li>
				</ul>
				</nav>
				<nav class="main-navigation menu-responsive hidden-md hidden-lg">
				<ul class="main-menu">

					<li><a
						href=<%="\"selectuser.do?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-magnifier"></i>&nbsp;Profile</a></li>
					<li><a href=<%="\"map.jsp?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-user"></i>&nbsp;Find Me</a></li>
					<li><a class="show-1 active homebutton"
						href=<%="\"setting.jsp?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-settings"></i>&nbsp;Settings</a></li>
					<li><a href="logout.do?flag=logout"><i class="icon-logout"></i>&nbsp;Log
							Out</a></li>
				</ul>
				</nav>
			</div>
		</div>
	</div>
	</header>


	<div id="menu-4" class="homepage home-section container">

		<div class="blog-header text-center">

			<div class="home-intro text-center">
				<h2 class="welcome-title animated fadeInLeft">Change Your
					Details</h2>
				<ul class="list-icons animated fadeInUp">
					<li><i class="icon-settings"></i></li>
					<li><i class="icon-bulb"></i></li>
					<li><i class="icon-key"></i></li>
					<li><i class="icon-lock-open"></i></li>
				</ul>
				<br>
				<h3 class="welcome-title animated fadeInLeft"
					style="color: green; font-weight: 400;">
					<%
						String success = (String) request.getAttribute("Success");
						if (success != null) {
							out.print(success);
						}
					%>
				</h3>
			</div>
			<a href=<%="\"changepwd.jsp?id=" + id + "&flag=" + flag + "\""%>
				class="blog-button animated fadeInUp">Change Password</a>
		</div>
		<div class="row blog-posts"></div>
	</div>




	<footer class="site-footer container text-center">
	<div class="row">
		<div class="col-md-12">
			<div class="main-footer">
				<ul class="social">
					<li><a href="#">Facebook</a></li>
					<li><a href="#">Twitter</a></li>
					<li><a href="#">Instagram</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12 copyright">
			<p>
				Copyright &copy; 2015 <a href="#">Secure it Pro</a>
			</p>
		</div>
	</div>
	</footer>
	<span class="border-top"></span>
	<span class="border-left"></span>
	<span class="border-right"></span>
	<span class="border-bottom"></span>
	<span class="shape-1"></span>
	<span class="shape-2"></span>
	<script src="js/jquery.min.js"></script>
	<script src="js/templatemo_custom.js"></script>
</body>
</html>
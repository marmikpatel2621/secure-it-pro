<%@page import="com.bean.ProfileBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta HTTP-EQUIV="Pragma" content="no-cache">
and
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
	color: #ffffff;
	font-size: 20px;
	border-style: solid;
	padding: 5px 5px;
	overflow: hidden;
	word-break: normal;
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
	overflow: hidden;
	word-break: normal;
	text-align: right;
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

					<li><a class="show-1 active homebutton"
						href=<%="\"selectuser.do?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-magnifier"></i>&nbsp;Profile</a></li>
					<li><a href=<%="\"map.jsp?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-user"></i>&nbsp;Find Me</a></li>
					<li><a
						href=<%="\"setting.jsp?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-settings"></i>&nbsp;Settings</a></li>
					<li><a href="logout.do?flag=logout"><i class="icon-logout"></i>&nbsp;Log
							Out</a></li>
				</ul>
				</nav>
				<nav class="main-navigation menu-responsive hidden-md hidden-lg">
				<ul class="main-menu">

					<li><a class="show-1 active homebutton"
						href=<%="\"selectuser.do?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-magnifier"></i>&nbsp;Profile</a></li>
					<li><a href=<%="\"map.jsp?id=" + id + "&flag=" + flag + "\""%>><i
							class="icon-user"></i>&nbsp;Find Me</a></li>
					<li><a
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
	<%
		ProfileBean profile = (ProfileBean) request.getAttribute("profile");
		System.out.println("name:" + profile.getName());
	%>

	<div id="menu-container">
		<div id="menu-1" class="homepage home-section container">
			<div class="home-intro text-center">
				<h2 class="welcome-title animated fadeInLeft">Profile</h2>
				<fieldset>
					<center>

						<div class="contact-form animated fadeInUp">
							<h3 style="color: #318CE7; align: center; font-weight: bold">
								<b>Know Yourself</b>
							</h3>
							<br>
							<table class="tg" style="align: center">
								<tr>
									<th class="tg-031e">Name</th>
									<td class="tg-031e"><%=profile.getName()%></td>
								</tr>
								<tr>
									<th class="tg-031e">Email</th>
									<td class="tg-031e"><%=profile.getEmail()%></td>
								</tr>
								<tr>
									<th class="tg-031e">IMEI</th>
									<td class="tg-031e"><%=profile.getImei()%></td>
								</tr>
								<tr>
									<th class="tg-031e">SIM ID</th>
									<td class="tg-031e"><%=profile.getSim()%></td>
								</tr>
								<tr>
									<th class="tg-031e">Mobile</th>
									<td class="tg-031e"><%=profile.getMobile()%></td>
								</tr>

							</table>
					</center>
				</fieldset>
			</div>
		</div>
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
	<!-- templatemo 421 raleway -->
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
<%@page import="com.bean.ProfileBean"%>
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
<script type="text/javascript">
	function validateoPassword() {
		var opwiname = document.getElementById("opwiname");

		var onamePattern = document.getElementById("opwd").value;

		if (onamePattern.length == 0) {
			alert("Old Password Not Empty")

			return false;
		} else {
			return true;
		}
	}
	function validatenPassword() {

		var npwiname = document.getElementById("npwiname");

		var nnamePattern = document.getElementById("npwd").value;

		var passwordPattern = /^[a-zA-Z]\w{3,14}$/;
		if (passwordPattern.test(nnamePattern)) {
			return true;
		} else {
			alert("The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be used");
			
			return false;
		}
	}
	function validatecPassword() {

		var cpwiname = document.getElementById("cpwiname");

		var cnamePattern = document.getElementById("cpwd").value;
		var passwordPattern = /^[a-zA-Z]\w{3,14}$/;
		if (passwordPattern.test(cnamePattern)) {
			return true;
		} else {
			alert("The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be used");
			
			return false;
		}
	}
	function Compare() {
		var pw = document.getElementById("npwd").value;
		var password1 = document.getElementById("cpwiname");
		var rpw = document.getElementById("cpwd").value;
		if (pw == rpw && (rpw != null) && (pw != null)) {

			password1.innerHTML = "Match";
			password1.style.color = "Green"
			return true;
		} else {

			password1.innerHTML = "Not Match";
			password1.style.color = "Red"
			return false;
		}
	}
	
	function Check() {
		if (Compare() && validatecPassword() && validateoPassword() && validatenPassword()) {
			alert("true");
			return true;
		} else {
			alert("Insert All Detail.Some details are empty.");
			return false;
		}
	}
</script>

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
	
	<form action="<%=request.getContextPath()%>/changepwd.do" class="register" method="post"
		onsubmit="return Check();">
		<div id="menu-1" class="homepage home-section container">

			<div class="blog-header text-center">

				<div class="home-intro text-center">
					<h2 class="welcome-title animated fadeInLeft">Change Your
						Password</h2>
					<ul class="list-icons animated fadeInUp">
						<li><i class="icon-settings"></i></li>
						<li><i class="icon-bulb"></i></li>
						<li><i class="icon-key"></i></li>
						<li><i class="icon-lock-open"></i></li>
					</ul>
					<h3 class="welcome-title animated fadeInLeft"
						style="color: red; font-weight: 400;">
						<%
							String error = (String) request.getAttribute("ERROR");
							if (error != null) {
								out.print(error);
							}
						%>
					</h3>
				</div>

				<fieldset>

					<input type="password" name="opwd" id="opwd"
						placeholder="Old Password" onBlur="validateoPassword();"><span
						id="opwiname"> </span>
				</fieldset>
				<br>
				<fieldset>
					<input type="hidden" value="<%=id%>" name="id" /> <input
						type="password" name="npwd" id="npwd"
						onBlur="validatenPassword();" placeholder="New Password"><span
						id="npwiname"> </span>
				</fieldset>
				<br>
				<fieldset>
					<input type="hidden" value="login" name="flag" /> 
					<input type="password" name="opwd" id="cpwd"
						onBlur="validatecPassword();" placeholder="Re Enter Password"><span
						id="cpwiname"> </span>
				</fieldset>
				<br> <input type="submit" value="Change Password"
					height="200px" class="animated fadeInUp" />


			</div>
			<div class="row blog-posts"></div>
		</div>
	</form>

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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p"%>

<html lang="en">
<head>
<meta charset="utf-8">
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
<script type="text/javascript">
	function validateUser() {

		var uiname = document.getElementById("uiname");
		var unamePattern = document.getElementById("uname").value;
		if (unamePattern.length == 0) {
			alert("Username Not Empty")

			return false;
		} else {
			return true;
		}
	}

	function validatePassword() {
		var piname = document.getElementById("piname");
		var pnamePattern = document.getElementById("pw").value;
		if (pnamePattern.length == 0) {
			alert("Password Not Empty")

			return false;
		} else {
			return true;
		}
	}
	function Check() {
		if (validateUser() && validatePassword()) {

			return true;
		} else {
			alert("Check your details.");
			return false;
		}
	}
</script>
</head>
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
					<li><a class="show-1 active homebutton" href="#"><i
							class="icon-home"></i>&nbsp;Home</a></li>
					<li><a class="show-2 aboutbutton" href="#"><i
							class="icon-docs"></i>&nbsp;About Us</a></li>

					<li><a class="show-4 blogbutton" href="#"><i
							class="icon-login"></i>&nbsp;Login</a></li>
					<li><a class="show-5 contactbutton" href="#"><i
							class="icon-envelope"></i>&nbsp;Contact</a></li>
				</ul>
				</nav>
				<nav class="main-navigation menu-responsive hidden-md hidden-lg">
				<ul class="main-menu">
					<li><a class="show-1 active homebutton" href="#"><i
							class="icon-home"></i>&nbsp;Home</a></li>
					<li><a class="show-2 aboutbutton" href="#"><i
							class="icon-docs"></i>&nbsp;About Us</a></li>

					<li><a class="show-4 blogbutton" href="#"><i
							class="icon-login"></i>&nbsp;Login</a></li>
					<li><a class="show-5 contactbutton" href="#"><i
							class="icon-envelope"></i>&nbsp;Contact</a></li>
				</ul>
				</nav>
			</div>
		</div>
	</div>
	</header>


	<div id="menu-container">
		<div id="menu-1" class="homepage home-section container">
			<div class="home-intro text-center">
				<h2 class="welcome-title animated fadeInLeft">Introduction</h2>
				<p class="animated fadeInRight">
					Find out the location using <span class="blue">Google</span><span
						class="green"> Map</span>. Credit goes to Secure It Pro Team.
				</p>
				<ul class="list-icons animated fadeInUp">
					<li><i class="icon-directions"></i></li>
					<li><i class="icon-direction"></i></li>
					<li><i class="icon-compass"></i></li>
					<li><i class="icon-info"></i></li>
				</ul>

			</div>
			<div class="home-projects">
				<div class="row">
					<div class="col-md-6 col-sm-12">


						<img src="img/gicon1.jpg" alt="">


					</div>
					<div class="project-home-holder col-md-6 col-sm-12">
						<div class="row">
							<div class="col-md-6 col-sm-6">
								<div class="project-item one animated fadeInRight">
									<img src="img/indiamap.jpg" alt="">
									<div class="overlay">
										<h4>
											<a href="#">india</a>
										</h4>
									</div>
								</div>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="project-item two animated fadeInRight">
									<img src="img/usamap.jpg" alt="">
									<div class="overlay">
										<h4>
											<a href="#">USA</a>
										</h4>
									</div>
								</div>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="project-item three animated fadeInRight">
									<img src="img/southafricamap.jpg" alt="">
									<div class="overlay">
										<h4>
											<a href="#">South Africa</a>
										</h4>
									</div>
								</div>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="project-item four animated fadeInRight">
									<img src="img/australiamap.jpg" alt="">
									<div class="overlay">
										<h4>
											<a href="#">Australia</a>
										</h4>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="menu-2" class="content about-section container">
			<div class="our-story">
				<div class="story-bg animated fadeIn"></div>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						<div class="inner-story animated fadeInRight text-center">
							<h2>ABOUT US</h2>
							<p>We are team of four members.We are interested in
								developing android and java base applications.</p>
						</div>
					</div>
				</div>
			</div>
			<div class="our-offers">
				<div class="offer-bg animated fadeIn"></div>
				<div class="offer-header">
					<div class="row">
						<div class="col-md-6 col-md-offset-3 text-center">
							<div class="offer-title animated fadeInDown">
								<h2>OUR WORK In secure It Pro</h2>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="offer-holder">
						<div class="col-md-6">
							<div class="offer-item offer-1 animated fadeInLeft">
								<figure> <img src="img/i1.jpg" alt=""> </figure>
								<div class="offer-content text-center">
									<h4>Antitheft & Emergency Call</h4>
									<p>Antitheft is used to track stolen phones</p>
									<p>Emergency call is used to call any person on one click</p>
									<span class="offer-left"><i></i></span> <span
										class="offer-right"><i></i></span>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="offer-item offer-2 animated fadeInRight">
								<figure> <img src="img/i2.jpg" alt=""> </figure>
								<div class="offer-content text-center">
									<h4>Applock & DataHiding</h4>
									<p>Applock is used to protect apps from accessing</p>
									<p>DataHiding is used to hide images,videos,files etc.</p>
									<span class="offer-left"><i></i></span> <span
										class="offer-right"><i></i></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<form action="<%=request.getContextPath()%>/login.do" method="post" onsubmit="return Check();">
			<div id="menu-4" class="content blog-section container">

				<div class="blog-header text-center">

					<h3 class="welcome-title animated fadeInLeft" style="color:red; font-weight: 400;" >
						<%
							String error = (String) request.getAttribute("ERROR");
							if (error != null) {
								out.print(error);
							}
						%>
						</h3>	
						<fieldset>

							<input type="text" name="uname" id="uname"
								onBlur="validateUser();" placeholder="Username"><span
								id="uiname"> </span>
						</fieldset>

						<br>
						<fieldset>
							<input type="password" name="pwd" id="pw"
								onBlur="validatePassword();" placeholder="Password"><span
								id="piname"> </span>
						</fieldset>
						
						<input type="hidden" name="flag" value="login"/>
						
					<br> <input type="submit" value="login" 
						class="animated fadeInUp" /> <a href="forgot.jsp"
						class="blog-button animated fadeInUp">Forgot Password?</a>
				</div>
				<div class="row blog-posts"></div>
			</div>
		</form>
		<div id="menu-5" class="content contact-section container">
			<div class="contact-header text-center">
				<h2 class="animated fadeInLeft">Get in Touch</h2>
				<p class="animated fadeInRight">Feel free to talk to us about
					anything.</p>
				<ul class="contact-social animated fadeInUp">
					<li><a href="#"><i class="fa fa-twitter"></i></a></li>
					<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
					<li><a href="#"><i class="fa fa-instagram"></i></a></li>
					<li><a href="#"><i class="fa fa-share-alt"></i></a></li>
				</ul>
			</div>
			<div class="contact-holder">
			<div class="row">
				
				<fieldset>
					<center>

						<div class="contact-form animated fadeInUp">
							<h3 style="color: #318CE7; align: center; font-weight: bold">
								<b>Developers</b>
							</h3>
							<br>
							<table class="tg" style="align: center">
								<tr>
									<th class="tg-031e">Marmik Patel</th>
									<td class="tg-031e">marmikpatel262@gmail.com</td>
								</tr>
								<tr>
									<th class="tg-031e">Niket Patel</th>
									<td class="tg-031e">niketpatel2525@gmail.com</td>
								</tr>
								<tr>
									<th class="tg-031e">Gaurav Shah</th>
									<td class="tg-031e">gaurav38shah@gmail.com</td>
								</tr>
								<tr>
									<th class="tg-031e">Nisarg Shah</th>
									<td class="tg-031e">nisargshah_008@yahoo.com</td>
								</tr>
								
							</table>
					</center>
				</fieldset>
			</div>
		</div>			</div>
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
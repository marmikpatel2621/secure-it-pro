<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimal-ui">
	<title>Secure It Pro</title>
<!--
Raleway Template 
http://www.templatemo.com/preview/templatemo_421_raleway
-->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/simple-line-icons.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/templatemo_style.css">
	<script type="text/javascript">
	function validatenEmail() {

		

		var email = document.getElementById("email").value;

		var emailPattern = /^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$/;
		if (emailPattern.test(email)) {
			return true;
		} else {
			alert("Enter Valid Email");
			
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
						<h1><strong>Secure It Pro</strong></h1>
					</div>
				</div>
				<a href="#" class="toggle-nav hidden-md hidden-lg">
					<i class="fa fa-bars"></i>
					</a>
					<div class="col-md-8">
					<nav id="nav" class="main-navigation hidden-xs hidden-sm">
						<ul class="main-menu">
							<li><a href="index.jsp"><i class="icon-home"></i>&nbsp;Home</a></li>
							
						</ul>
					</nav>
					<nav class="main-navigation menu-responsive hidden-md hidden-lg">
						<ul class="main-menu">
							<li><a  href="index.html"><i class="icon-home"></i>&nbsp;Home</a></li>
							
						</ul>
					</nav>
				</div>
				
				
			</div>
		</div>
	</header>

	<form action="<%=request.getContextPath()%>/forgot.do" method="post">
	<div id="menu-container">
		<div id="menu-1" class="homepage home-section container">
			<div class="home-intro text-center">
			
					<h3 class="welcome-title animated fadeInLeft" style="color:red; font-weight: 400;" >
						<%
							String error = (String) request.getAttribute("ERROR");
							if (error != null) {
								out.print(error);
							}
						%>
						</h3>	
				<h2 class="welcome-title animated fadeInLeft">Enter Email Address</h2>
				<fieldset>
				
									
									<input type="text" name="email" id="email" onBlur="validatenEmail();" placeholder="Email" size="35" style="height:38px;">
									 <input type="submit" value="send mail" class="animated fadeInUp"/>
								</fieldset>
					<br>
				
				
			</div>
			
		</div>
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
				<p>Copyright &copy; 2015 <a href="index.jsp">Secure it Pro</a></p>
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
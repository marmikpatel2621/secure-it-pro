<%@page import="com.bean.LocationBean"%>
<%@page import="com.dao.LocationDao"%>
<%@page import="com.mysql.jdbc.Connection"%>
<%@page import="com.model.DbConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="refresh" content="100">
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
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?
                      key=mykey&sensor=false">
   </script>

<meta name="viewport" content="width=device-width">
</head>
<%	
	if(session.getAttribute("session") == null)
		response.sendRedirect("index.jsp");
	String uid = request.getParameter("id");
	String flag = request.getParameter("flag");
	int id = Integer.parseInt(uid);
%>
<%
	DbConnection db = new DbConnection();
	Connection con = (Connection) db.getCon();
	LocationDao ld = new LocationDao();
	LocationBean location = ld.getLocationById(id, con);
	System.out.println("loc:" + location.getLatitude() + "::"
			+ location.getLongitude());
%>

<body
	onload="GetMap(<%=location.getLatitude()%>,<%=location.getLongitude()%>)">
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
					
					<li><a  href=<%="\"selectuser.do?id=" + id + "&flag=" +flag+"\""%>><i
							class="icon-magnifier"></i>&nbsp;Profile</a></li>
					<li><a class="show-1 active homebutton"
						href=<%="\"map.jsp?id=" + id + "&flag=" +flag+"\""%>><i class="icon-user"></i>&nbsp;Find
							Me</a></li>
					<li><a href=<%="\"setting.jsp?id=" + id + "&flag=" +flag+"\""%>><i
							class="icon-settings"></i>&nbsp;Settings</a></li>
					<li><a href="logout.do?flag=logout"><i class="icon-logout"></i>&nbsp;Log
							Out</a></li>
				</ul>
				</nav>
				<nav class="main-navigation menu-responsive hidden-md hidden-lg">
				<ul class="main-menu">
					
					<li><a href=<%="\"selectuser.do?id=" + id + "&flag=" +flag+"\""%>><i
							class="icon-magnifier"></i>&nbsp;Profile</a></li>
					<li><a class="show-1 active homebutton" 
						href=<%="\"map.jsp?id=" + id + "&flag=" +flag+"\""%>><i class="icon-user"></i>&nbsp;Find
							Me</a></li>
					<li><a href=<%="\"setting.jsp?id=" + id + "&flag=" +flag+"\""%>><i
							class="icon-settings"></i>&nbsp;Settings</a></li>
					<li><a href="logout.do?flag=logout"><i
							class="icon-logout"></i>&nbsp;Log Out</a></li>
				</ul>
				</nav>
			</div>
		</div>
	</div>
	</header>



	<div id="menu-1" class="homepage home-section container">
		<div class="our-story">
			<div class="story-bg animated fadeIn"></div>
			<div class="row">
				<div class="col-md-16">
					<div id="mapContainer" style="width: 500px; height: 500px"
						align="center"></div>
					<br>
				</div>

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
	<script type="text/javascript">
		function GetMap(v1, v2) {
			console.log(v1);
			console.log(v2);
			var latlng = new google.maps.LatLng(v1, v2);
			var myOptions = {
				zoom :18,
				center : latlng,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			var container = document.getElementById("mapContainer");
			map = new google.maps.Map(container, myOptions);
			var marker = new google.maps.Marker({
				position : latlng,
			});
			marker.setMap(map);
		}
	</script>


</body>
</html>
 <%@page import="Models.Facilities"%>
<%@page import="java.util.*"%>
    <%@page import="DataBases.HotelsDB"%>
    <%@page import="Managers.HotelManager"%>
    <%@page import="Models.Hotel"%>
    <%@page import="Models.Room"%>
    <%@page import="Models.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/flaticon.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/Login.css">
<link rel="stylesheet" href="css/Post.css">
<link rel="stylesheet" href="css/hotelpage.css">
<script src="https://kit.fontawesome.com/13f325d0c5.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="js/home.js" type="text/javascript"></script>
<script src="js/register.js" type="text/javascript"></script>
</head>
<body>
	<div class="homepage"></div>
		<header class="header-section">
			<div class="header">
				<div class="site-logo">
					<a href="./home.html">BookinGeorgia</a>
				</div>
				
				<div class="user-panel">
					<div id="log-and-reg">
						<i class="flaticon-profile" style="color: white; margin-right: 15px; "></i>
					 <a class="reg" href="Register.html" id="lr-btn">Register</a> <a class="log" href="#" id="lr-btn"> Login </a> 			
					</div>			
					<div class="logged">
						<a class="menu-but" href="#" style="display: none"> Hello  <i class="arrow down"></i></a>
						<div class="menu">
						  <a href="#">Your page</a>
						  <a href="#">Settings</a>
						  <a href="#">Log out</a>
						</div>
					</div>					
				</div>
			</div>
			<nav class="navbar navbar-expand-sm bg-light navbar-light">
			  <ul class="navbar-nav">
			    <li class="nav-item active">
			      <a class="nav-link" href="#">Home</a>
			    </li>
			    <li class="nav-item active">
			      <a class="nav-link" href="#">Your Posts</a>
			    </li>
			    <li class="nav-item active">
			      <a class="nav-link" href="#">About us</a>
			    </li>
			    <li class="nav-item active">
			      <a class="nav-link" href="#">Contact us</a>
			    </li>
			  </ul>
			</nav>
		</header>
		<div class="image-info">
		    <% int id = Integer.parseInt(request.getParameter("id"));
		    HotelManager hm = HotelManager.getInstance();
		    Hotel h = hm.getHotel(id);
		    %>
			<figure class="img"> <img src="<%=h.getImage() %>"> </figure>
			<div class="info">
				<div class="name">
					<h2 class="hotel-name"><%=h.getName() %></h2>
					<%for(int i = 0; i < h.getRating(); i++) {%>
						<span class="fa fa-star checked"></span>
					<%}
					for(int i = h.getRating(); i < 5; i++){ %>
						<span class="fa fa-star"></span>
					<%} %>
				</div>
				<div class="status">
					<p><%=h.getStatus() %></p>
				</div>
				<% Facilities f = h.getFacilities(); %>
				<div class="facil">
					<div>	<i class="<% if(f.getWiFi()) {
							%> fa fa-check-circle <%} else {%>
							   fa fa-ban
							<%} %>
							"></i><h6>Wi-Fi</h6></div>
					<div>	<i class="<% if(f.getParking()) {
							%> fa fa-check-circle <%} else {%>
							   fa fa-ban
							<%} %>
							"></i><h6>Parking</h6></div>
					<div>	<i class="<% if(f.getBeachfront()) {
							%> fa fa-check-circle <%} else {%>
							   fa fa-ban
							<%} %>
							"></i><h6>Beachfront</h6></div>
					<div>	<i class="<% if(f.getWoodfront()) {
							%> fa fa-check-circle <%} else {%>
							   fa fa-ban
							<%} %>
							"></i><h6>Woodfront</h6></div>
				</div>
				<div class="text">
					<div class="location">
					<h4>Saburtalo</h4>
					</div>
					<div class="number">
					<h4><%=h.getNumber() %></h4>
					</div>
				</div>
			</div>
		</div>
		<table class="room">
		  <tr>
		    <th>Number of beds</th>
		    <th>Room information</th> 
		    <th>Reserved from - to</th>
		    <th>Available from - to</th>
		  </tr>
		  <% List<Room> l = hm.getRooms(id);
		  
		  %>
		  <tr>
		    <td>1 bedroom</td>
		    <td class="facil"><div> <i class="fa fa-check-circle"></i><h6><%=request.getAttribute("wifi") %></h6></div>
					<div> <i class="fa fa-ban" ></i><h6><%=request.getAttribute("parking") %></h6></div>
					<div> <i class="fa fa-check-circle"></i><h6><%=request.getAttribute("beachfront") %></h6></div>
					<div> <i class="fa fa-ban"></i><h6><%=request.getAttribute("woodfront") %></h6></div>
			</td>
		  </tr>
		</table>
</body>
</html>
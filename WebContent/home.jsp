<%@page import="Managers.AccountManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
    <%@page import="DataBases.HotelsDB"%>
    <%@page import="Managers.HotelManager"%>
    <%@page import="Models.Hotel"%>
    <%@page import="Models.Account"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/flaticon.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/Login.css">
<link rel="stylesheet" href="css/Post.css">
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
					<a href="./home.jsp">BookinGeorgia</a>
				</div>
				
				<div class="user-panel">
					<div id="log-and-reg">
						<i class="flaticon-profile" style="color: white; margin-right: 15px; "></i>
					 <a class="reg" href="Register.html" id="lr-btn">Register</a> <a class="log" href="#" id="lr-btn"> Login </a> 			
					</div>			
					<div class="logged">
						<%
						AccountManager am = AccountManager.getInstance();
						String user = (String) request.getSession().getAttribute("user");
						Account acc = am.getAccount(user);
						%>
						<a class="menu-but" href="#" style="display:none"> Hello <% out.print( acc != null ? acc.getUsername(): "");%> <i class="arrow down"></i></a>
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
		<section class="main">
			<section class="search-sec">
				<div class="container">
					<form action="#" method="post" novalidate="novalidate">
						<div class="row">
							<div class="col-lg-12">
								<div class="row">
									<div class="col-lg-3 col-md-3 col-sm-12 p-0">
										<select class="form-control search-slt"
											id="exampleFormControlSelect1">
											<option>Select City</option>
											<option>Example one</option>
											<option>Example one</option>
											<option>Example one</option>
											<option>Example one</option>
											<option>Example one</option>
											<option>Example one</option>
										</select>
									</div>
									
									<div class="col-lg-5 col-md-5 col-sm-12 p-0">
										<input type="text" class="form-control search-slt"
											placeholder="Enter Hotel Name">
									</div>
									
									<div class="col-lg-4 col-md-4 col-sm-12 p-0">
										<button type="button" class="btn btn-danger wrn-btn">Search</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</section>
		</section>
		<div class="filter">
			<div class="filter-wrap">
				<form action="#" method="get">
					<h2>Filter by</h2>
					<div class="check">
						<h5>Star rating</h5>
						<input type="checkbox">
						<span class="checkmark"> 5 stars </span> <br>
						<input type="checkbox">
						<span class="checkmark"> 4 stars </span> <br>
						<input type="checkbox">
						<span class="checkmark"> 3 stars </span> <br>
						<input type="checkbox">
						<span class="checkmark"> 2 stars </span> <br>
						<input type="checkbox">
						<span class="checkmark"> 1 stars </span> <br>
					</div>
					<div class="check">
						<h5>Facilities</h5>
						<input type="checkbox">
						<span class="checkmark"> Beachfront </span> <br>
						<input type="checkbox">
						<span class="checkmark"> Near forest </span> <br>
						<input type="checkbox">
						<span class="checkmark"> In the center </span> <br>
						<input type="checkbox">
						<span class="checkmark"> Wi-Fi </span> <br>
						<input type="checkbox">
						<span class="checkmark"> Pool </span> <br>
						<input type="checkbox">
						<span class="checkmark"> Parking </span> <br>
						<input type="checkbox">
						<span class="checkmark"> Air Conditioning </span> <br>
						<input type="checkbox">
						<span class="checkmark"> Heating </span> <br>
					</div>
					<input class="filter-btn" type="button" name="" value="Filter"> 
				</form>
			</div>
		</div>
		
		<div class="hotels">
		<% HotelsDB db = HotelsDB.getInstance();
		HotelManager hm = HotelManager.getInstance();
		//hm.addHotel("hotel", 5, "none", "ratingi 4 5", "599", 1);
			List<Integer> IDs = db.getAllHotelIDs();
			for(Integer hotel_id : IDs){
				Hotel hotel = db.getHotel(hotel_id);
				request.setAttribute("name", hotel.getName());
				request.setAttribute("rating", hotel.getRating());
				request.setAttribute("status", hotel.getStatus());
				request.setAttribute("img", hotel.getImage());
				request.setAttribute("number", hotel.getNumber());
				if(hotel.getFacilities() != null){
					request.setAttribute("facility", hotel.getFacilities().getFacility());
					request.setAttribute("wifi", hotel.getFacilities().getWiFi());
					request.setAttribute("parking", hotel.getFacilities().getParking());
					request.setAttribute("beachfront", hotel.getFacilities().getBeachfront());
					request.setAttribute("woodfront", hotel.getFacilities().getWoodfront());	
				}
			%>
				<jsp:include page="Post.jsp"/>
			<%}%>
		</div>
		<div class="log-in"> 
			<div class="login-box">
				<i class="fas fa-times-circle" id="close"></i>
				
				<h1 class="log-fail"> Login </h1>
				<div class="text-box">
					<i class="fas fa-user"></i>
					<input type="text" placeholder="Username" class="user">
				</div>
				
				<div class="text-box">
					<i class="fas fa-lock"></i>
					<input type="password" placeholder="Password" class="password" >
				</div>
				
				<input class="log-btn" type="button" name="" value="Sign in"> 
				
				<a class="go-to-reg" href="Register.html"> Register </a> <br>
			</div>
		</div>
		
		
</body>
</html>
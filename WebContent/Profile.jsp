<%@page import="Managers.HotelManager"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Managers.AccountManager"%>
    <%@page import="Models.Account"%>
    <%@page import="Models.Hotel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<link rel="stylesheet" href="css/Profile.css">
<script src="https://kit.fontawesome.com/13f325d0c5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="js/profile.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="Header.jsp"/>
	<%AccountManager manager = AccountManager.getInstance();
		String mail = (String) request.getSession().getAttribute("user");
		Account user = manager.getAccount(mail); %>
		
	<div class="prof"> 
		<img class="prof-img" src="images/profile.jpg">
		<h3> <%=user.getFirstName()%> </h3>
	</div>
	
	<div class="edit">
		<input id="edit-prof" type="button" name="" value="Edit Profile">
		<input id="edit-post" type="button" name="" value="Edit Posts">
		<input id="edit-resrv" type="button" name="" value="Edit Reservation">
	</div>	
	
	<div class="prof-box">
		<i class="fas fa-user-edit"></i> <h2> Edit Account Info </h2>
		<span> Edit Firstname: </span> <br> <input type="text" value="<%=user.getFirstName()%>"  id="firstname" required="required">
		<br><span> Edit Lastname: </span> <br> <input type="text" value="<%=user.getLastName()%>" id="lastname" required="required">
		<br><span> Edit Mail: </span> <br> <input type="text" value="<%=user.getEmail()%>" id="email" required="required">
		<br><span> Edit username: </span> <br> <input type="text" value="<%=user.getUsername()%>"  id="user" required="required">
		<br><span> Show password: </span> <br>  
			<input class="edit-pass" type="password" placeholder="new password?" required="required" id="pass"> <i class="fas fa-eye-slash" id="show"></i>
		<button class="save" id="save-prof"> Save Changes</button>
	</div>
	<div class="posts">
		<% HotelManager hm = HotelManager.getInstance();
		List<Hotel> hotels = hm.getHotels(Integer.parseInt(user.getId()));%>
		
		<h2> Your Posts </h2>
		 
		<%for(Hotel hotel : hotels){ %>
			 <a href="Profile.jsp?hotel_id=<%=hotel.getId()%>"><%=hotel.getName() %><i class="fas fa-pencil-alt" style="float:right"></i></a>  
		<%}%>
	</div>
		
	<div class="post-box">
		<%if(request.getParameter("hotel_id") != null){ %>
			<%Hotel hotel = hm.getHotel(Integer.parseInt(request.getParameter("hotel_id")));%>
			
			<h2> Edit Hotel Info </h2>
			<span> Edit Name: </span> <br> <input type="text" value="<%=hotel.getName()%>"  id="hotelname" required="required"> <br>
			<span> Edit Rating: </span> <br> <input type="text" value="<%=hotel.getRating()%>"  id="hotelrating" required="required"> <br>
			<span> Edit Status: </span> <br> <input type="text" value="<%=hotel.getStatus()%>"  id="hotelstatus" required="required"> <br>
			<span> Edit Phone Number: </span> <br> <input type="text" value="<%=hotel.getNumber()%>"  id="hotelnumber" required="required"> <br>
			<span> Edit City: </span> <br> <input type="text" value="<%=hotel.getLocation().getCity()%>"  id="city" required="required"> <br>
			<span> Edit Address: </span> <br> <input type="text" value="<%=hotel.getLocation().getAddress()%>"  id="address" required="required"> <br>
			<form action="addRooms.jsp?hotel_id=<%=request.getParameter("hotel_id")%>" method="post">
				<button type="submit" class="save" id="addRoom"> Add More Rooms </button>
			</form>
			<input type="hidden" id="hotel_id" value="<%=request.getParameter("hotel_id")%>">
			<button class="save" id="save-post"> Save Changes </button>
			<button class="save" id="delete-post"> Delete Post </button>
		<%}%>
	</div>
</body>
</html>
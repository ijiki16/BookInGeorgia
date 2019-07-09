<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Describe Rooms Below</title>
<link rel="stylesheet" href="css/CreatePost.css">
<script src="https://kit.fontawesome.com/13f325d0c5.js"></script>
</head>
<body>
	<jsp:include page="Header.jsp"/>
	<div class="post-facility">
		<h1> Add Room Facilities </h1>

		<input type="checkbox">
		<span class="checkmark"> tv </span> <br>
		
		<input type="checkbox">
		<span class="checkmark"> wifi </span> <br>
		
		<input type="checkbox">
		<span class="checkmark"> hot water </span> <br>
		
		<input type="checkbox">
		<span class="checkmark"> air conditioning </span> <br>
		
		<div class="text-box">
			<i class="fas fa-bed"></i>
			<input type="number" placeholder="Number of beds" name="" value="">
		</div>
		
		<div class="text-box">
			<i class="fas fa-calendar-day"></i>
			<label>Reservation start date</label>
			<input type="date" >
		</div>
		
		<div class="text-box">
			<i class="fas fa-calendar-day"></i>
			<label>Reservation end date</label>
			<input type="date" >
		</div>
		
		<div class="text-box">
			<i class="fas fa-dollar-sign"></i>
			<input type="number" placeholder="Price per Day" name="" value="">
		</div>
		
		<div class="text-box">
			<i class="fas fa-info"></i>
			<input type="text" placeholder="other facilities?" name="" value="">
		</div>	
		
		<div class="file-box">
			<i class="fas fa-camera"></i>
			<input type="file" placeholder="Upload Image" name="image" value="">
		</div>
		
		<form action="createPost" method="post" enctype="multipart/form-data"> <button id="but"> Submit </button> </form>	
	</div>
	
</body>
</html>
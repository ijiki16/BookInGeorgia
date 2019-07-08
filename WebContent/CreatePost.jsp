<%@page import="Models.Account"%>
<%@page import="Managers.AccountManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Post</title>
<link rel="stylesheet" href="css/CreatePost.css">
<link rel="stylesheet" href="css/Header.css">
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
	<jsp:include page="Header.jsp"/>
	<div class="post-hotel">
		<h1> Add Hotel Info </h1>
	
		<div class="text-box">
			<i class="fas fa-hotel"></i>
			<input type="text" placeholder="Hotel Name" name="name" value="">
		</div>
		
		<div class="text-box">
			<i class="fas fa-star"></i>
			<input type="text" placeholder="Stars" name="stars" value="">
		</div>
		
		<div class="text-box">
			<i class="fas fa-comment"></i>
			<input type="text" placeholder="About Hotel" name="status" value="">
		</div>
		
		
		<div class="text-box">
			<i class="fas fa-phone"></i>
			<input type="text" placeholder="Phone Number" name="phone" value="">
		</div>
		
		<div class="text-box">
			<i class="fas fa-map-marker-alt"></i>
			<input type="text" placeholder="City" name="city" value="">
		</div>
		
		<div class="text-box">
			<i class="fas fa-road"></i>
			<input type="text" placeholder="Street" name="street" value="">
		</div>
	
		<div class="file-box">
			<i class="fas fa-camera"></i>
			<input type="file" placeholder="Upload Image" name="image" value="">
		</div>
	
	</div>
	
	<div class="post-facility">
		<h1> Add Facilities </h1>

		<input type="checkbox" name="wi-fi">
		<span class="checkmark"> wi-fi </span> <br>
		
		<input type="checkbox" name="parking">
		<span class="checkmark"> parking </span> <br>
		
		<input type="checkbox" name="beach">
		<span class="checkmark"> near beach? </span> <br>
		
		<input type="checkbox" name="forest">
		<span class="checkmark"> near forest? </span> <br>
		
		<div class="text-box">
			<i class="fas fa-info"></i>
			<input type="text" placeholder="other facilities?" name="facility" value="">
		</div>	
	
		<form action="js/addHotel.jsp" method="post" enctype="multipart/form-data"> <button> Submit </button> </form>	
	</div>
	
</body>
</html>
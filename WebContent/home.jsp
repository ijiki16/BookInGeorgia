<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/flaticon.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="LoginDesign.css">
<link rel="stylesheet" href="RegisterDesign.css">
<script src="https://kit.fontawesome.com/13f325d0c5.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="js/home.js" type="text/javascript"></script>
</head>
<body>
	<header class="header-section">
		<div class="header">
		
			<div class="site-logo">
				
				<a href="./home.html">BookinGeorgia</a>
			</div>
			
			<div class="user-panel">
					<i class="flaticon-profile" style="color: white; margin-right: 15px; "></i>
					
						<a id="Login" class="log" href="#"> Login </a>  <a class="reg" href="#">Register</a>
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
	
	<div class="log-in"> 
		
		<div class="login-box">
			<h1> Login </h1>
			<div class="ltext-box">
				<i class="fas fa-user"></i>
				<input type="text" placeholder="Username" class="user">
			</div>
			
			<div class="ltext-box">
				<i class="fas fa-lock"></i>
				<input type="password" placeholder="Password" class="password" >
			</div>
			
			<input class="log-btn" type="button" name="" value="Sign in"> 
			
			<a class="go-to-reg"> Register </a> <br>
		</div>
	</div>
	
	<button id="minus">-</button><input type="number" id="count" value="1"  redaonly><button id="plus">+</button>
	
	<div class="reg">
		<div class="register-box"> 
		<h1> Register </h1>
		
		<div class="rtext-box">
			<i class="fas fa-user"></i>
			<input type="text" placeholder="First Name" name="" value="">
		</div>
		
		<div class="rtext-box">
			<i class="fas fa-user-plus"></i>
			<input type="text" placeholder="Last Name" name="" value="">
		</div>
		
		<div class="rtext-box">
			<i class="fas fa-envelope"></i>
			<input type="text" placeholder="Email" name="" value="">
		</div>
		
		<div class="rtext-box">
			<i class="fas fa-user-secret"></i>	
			<input type="text" placeholder="Username" name="" value="">
		</div>
		
		
		<div class="rtext-box">
			<i class="fas fa-lock-open"></i>
			<input type="password" placeholder="Password" name="" value="">
		</div>
	
		
		<div class="rtext-box">
			<i class="fas fa-lock"></i>
			<input type="password" placeholder="Repeat Password" name="" value="">
		</div>
		
			<select name="date-box">
				<option value="month"> Month </option>
				<option value="jan"> January </option>
				<option value="feb"> February </option>
				<option value="mar"> March </option>
				<option value="apr"> April </option>
				<option value="may"> May </option>
				<option value="jun"> June </option>
				<option value="jul"> July </option>
				<option value="aug"> August </option>
				<option value="sep"> September </option>
				<option value="oct"> October </option>
				<option value="nov"> November </option>
				<option value="dec"> December </option>	
			</select>
			
			<div class="date-box">
				<i class="fas fa-birthday-cake"></i>
				<input type="text" placeholder="day" name="" value="">
			</div>
			
			<div class="date-box">
				<input type="text" placeholder="year" name="" value="">
			</div>
		

		<input class="reg-btn" type="button" name="" value="Create Account"> 
		
	</div>
		
	</div>
		
	<script type="text/javascript">
		$('#minus').click(function(){
			let val = $('#count').val();
			if (val > 0) {
				--val;
				$('#count').val(val);
			}
		});
		$('#plus').click(function(){
			let val = $('#count').val();
			++val;
			$('#count').val(val);			
		});
	</script>
</body>
</html>
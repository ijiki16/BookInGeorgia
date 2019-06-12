<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/flaticon.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="css/home.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="js/home.js" type="text/javascript"></script>
</head>
<body>
	<header class="header-section">
		<div class="header">
		
			<!-- logo -->
			<div class="site-logo">
				
				<a href="./home.html">BookinGeorgia</a>
			</div>
			
			<div class="user-panel">
					<i class="flaticon-profile" style="color: white; margin-right: 15px; "></i>
					<a id="l" class="log" href="#">Login</a>  <a class="log" href="#">Register</a>
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
		<img id="welcome" src="images/welcome.jpg">
		<h1>Welcome to Georgia!</h1>
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
	
</body>
</html>
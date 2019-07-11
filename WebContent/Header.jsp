<%@page import="Models.Account"%>
<%@page import="Managers.AccountManager"%>
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
	<header class="header-section">
			<div class="header">
				<div class="site-logo">
					<a href="./home.jsp">BookinGeorgia</a>
				</div>
				<div class="user-panel">
					<div id="log-and-reg">
					<i class="flaticon-profile" style="color: white; margin-right: 15px; "></i>
					<a class="log" href="#" id="lr-btn"> Login </a> <a class="reg" href="Register.html" id="lr-btn">Register</a>
					</div>			
					<div class="logged">
						<%
						AccountManager am = AccountManager.getInstance();
						String user = (String) request.getSession().getAttribute("user");
						Account acc = am.getAccount(user);
						%>
						<a class="menu-but" href="#" style="display:none"> <% out.print( acc != null ? acc.getUsername(): "");%> 
						<i class="arrow down"></i></a>
						<div class="menu">
						  <a href="Profile.jsp">Manage Profile</a>
						  <a href="#" id="log-out">Log out</a>
						</div>
					</div>					
				</div>
			</div>
			<nav class="navbar navbar-expand-sm bg-light navbar-light">
			  <ul class="navbar-nav">
			    <li class="nav-item active">
			      <a class="nav-link" href="home.jsp">Home</a>
			    </li>
			    <li class="nav-item active">
			      <a class="nav-link" href="#" id="posts">Your Posts</a>
			    </li>
			    <li class="nav-item active">
			      <a class="nav-link" href="CreatePost.jsp" id="add">Add Post</a>
			    </li>
			    <li class="nav-item active">
			      <a class="nav-link" href="AboutUs.jsp">About us</a>
			    </li>
			    <li class="nav-item active">
			      <a class="nav-link" href="ContactUs.jsp">Contact us</a>
			    </li>
			  </ul>
			</nav>
	</header>
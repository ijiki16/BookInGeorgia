<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="Managers.AccountManager"%>
    <%@page import="Models.Account"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<link rel="stylesheet" href="css/Profile.css">
<link rel="stylesheet" href="css/Header.css">
<link rel="stylesheet" href="css/home.css">
<script src="https://kit.fontawesome.com/13f325d0c5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="js/profile.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="Header.jsp"/>
	<div class="edit-box">
		<h2> Edit Account Info </h2>
		<%AccountManager manager = AccountManager.getInstance();
		String mail = (String) request.getSession().getAttribute("user");
		Account user = manager.getAccount(mail); %>
		<span> Edit Firstname </span> <input class="edit" type="text" value="<%=user.getFirstName()%>"  id="firstname" required="required">
		<span> Edit Lastname </span> <input class="edit" type="text" value="<%=user.getLastName()%>" id="lastname" required="required">
		<span> Edit Mail </span> <input class="edit" type="text" value="<%=user.getEmail()%>" id="email" required="required">
		<span> Edit username </span> <input class="edit" type="text" value="<%=user.getUsername()%>"  id="user" required="required">
		<span> Show password </span> <i class="fas fa-eye" id="show-password"></i> 
			<input class="edit" type="password" placeholder="new password?" required="required" id="password">
		<button id="save"> Save Changes</button>
	</div>
</body>
</html>
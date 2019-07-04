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
<script src="https://kit.fontawesome.com/13f325d0c5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>

<script src="js/profile.js" type="text/javascript"></script>
</head>
<body>
	<div class="edit-box">
		<h2> Edit Account Info </h2>
		<%AccountManager manager = AccountManager.getInstance();
		String account_id = (String) request.getParameter("user_id");
		Account user = manager.getAccount("test"); %>
		<span> Edit Firstname </span> <input class="edit" type="text" value="<%=user.getFirstName()%>"  id="firstname">
		<span> Edit Lastname </span> <input class="edit" type="text" value="<%=user.getLastName()%>" id="lastname">
		<span> Edit Mail </span> <input class="edit" type="text" value="<%=user.getEmail()%>" id="email">
		<span> Edit username </span> <input class="edit" type="text" value="<%=user.getUsername()%>"  id="user">
		<span> Show password </span> <i class="fas fa-eye" id="show-password"></i> <input class="edit" type="password" placeholder="new password?" id="password">
		<button id="save"> Save Changes</button>
	</div>
</body>
</html>
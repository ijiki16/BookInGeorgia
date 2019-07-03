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
</head>
<body>
	<div class="edit-box">
		<h2> Edit Account Info </h2>
		<%AccountManager manager = AccountManager.getInstance();
		String account_id = (String) request.getParameter("user_id");
		Account user = manager.getAccount("test"); %>
		Edit Firstname <input class="edit" type="text" placeholder="<%=user.getFirstName()%>"  id="firstname" name="" value="">
		Edit Lastname <input class="edit" type="text" placeholder="<%=user.getLastName()%>" id="lastname" name="" value="">
		Edit Mail <input class="edit" type="text" placeholder="<%=user.getEmail()%>"  id="email" name="" value="">
		Edit username <input class="edit" type="text" placeholder="<%=user.getUsername()%>"  id="user" name="" value="">
		Show password <i class="fas fa-eye" id="show-password"></i> <input class="edit" type="password" placeholder="new password?" id="password">
		<button id="save"> Save Changes</button>
	</div>
</body>
</html>
<%@page import="Managers.AccountManager"%>
<%@page import="Models.Account"%>
<% String firstName = (String)request.getParameter("firstname");
String lastName = (String)request.getParameter("lastname");
String email = (String)request.getParameter("email");
String username = (String)request.getParameter("user");
String password = (String)request.getParameter("password");
System.out.println(firstName + "#" + lastName + "#" + email + "#" + username + "#" + password + "#");

AccountManager manager = AccountManager.getInstance();
String user = (String) request.getSession().getAttribute("user");
Account acc = manager.getAccount(user);
if(!firstName.equals(acc.getFirstName())) manager.updateAccount(acc, "firstname", firstName);
if(!lastName.equals(acc.getLastName())) manager.updateAccount(acc, "lastname", lastName);
if(!email.equals(acc.getEmail())) manager.updateAccount(acc, "email", email);
if(!username.equals(acc.getUsername())) manager.updateAccount(acc, "username", firstName);
if(!password.equals(acc.getPassword())) manager.updateAccount(acc, "password", firstName);
%><%="Changes Saved"%>
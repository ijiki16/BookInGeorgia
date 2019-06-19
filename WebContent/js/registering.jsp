<%@page import="Managers.AccountManager"%>
<%
String firstName = (String)request.getParameter("firstname");
String lastName = (String)request.getParameter("lastname");
String email = (String)request.getParameter("email");
String username = (String)request.getParameter("user");
String password = (String)request.getParameter("password");
String birthDate = (String)request.getParameter("bdate");

AccountManager manager = new AccountManager();
String res = "success";
if(!manager.createAccount(firstName, lastName, email, username, password, birthDate)) res = "fail";
%> <%= res %>
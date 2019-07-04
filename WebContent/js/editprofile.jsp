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

if(!firstName.equals(acc.getFirstName())) manager.updateAccount(acc, "first_name", firstName);
if(!lastName.equals(acc.getLastName())) manager.updateAccount(acc, "last_name", lastName);
if(!email.equals(acc.getEmail())) manager.updateAccount(acc, "email", email);
if(!username.equals(acc.getUsername())){
	System.out.print(manager.updateAccount(acc, "username", username));
	//manager.updateAccount(acc, "username", firstName);
}
if(!password.isEmpty() && !password.equals(acc.getPassword())){
	System.out.print("aeagfadigfhoaisadhf");
	System.out.print(manager.updateAccount(acc, "password", firstName));
}
%><%="Changes Saved"%>
<%@page import="Managers.AccountManager"%>
<% String user = (String)request.getParameter("user");
String password = (String)request.getParameter("password");
AccountManager manager = new AccountManager();
boolean log = manager.loginAccount(user, password);
String res;
if(log) res = manager.getAccount(user).getFirstName();
else res = "fail"; %> 
<%=res%>
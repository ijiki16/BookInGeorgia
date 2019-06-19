<%@page import="Managers.AccountManager"%>
<% String user = (String)request.getParameter("user");
String password = (String)request.getParameter("password");
AccountManager manager = new AccountManager();
String res = "success";
if(!manager.loginAccount(user, password)) res = "fail"; %> 
<%=res%>
<%@page import="Managers.AccountManager"%>
<% String user = (String)request.getParameter("user");
String password = (String)request.getParameter("password");
AccountManager manager = AccountManager.getInstance();
boolean log = manager.loginAccount(user, password);
String res = "fail";
if(log){
	request.getSession().setAttribute("user", manager.getAccount(user).getEmail());
	res = manager.getAccount(user).getFirstName();
}%> 
<%=res%>
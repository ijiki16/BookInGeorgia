<%String user = (String) request.getSession().getAttribute("user");
if(user == null) user = "fail";	
if(request.getParameter("todo").equals("logout")) request.getSession().removeAttribute("user");
%> <%=user%>
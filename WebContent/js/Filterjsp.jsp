<%boolean[] mass = new boolean[5];
String arr = "";
for(int i = 1; i <= 5; i++){
	String s = request.getParameter("s" + i);
	mass[i - 1] = s.equals("true") ? true : false;
	arr += (mass[i-1]) ? "1" : "0";
}

request.getSession().setAttribute("wifi", request.getParameter("wifi"));
request.getSession().setAttribute("parking", request.getParameter("parking"));
request.getSession().setAttribute("beach", request.getParameter("beach"));
request.getSession().setAttribute("wood", request.getParameter("forest"));
request.getSession().setAttribute("arr", arr);%>
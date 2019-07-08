 <%@page import="Managers.HotelManager"%>
     <%@page import="java.util.*"%>
<%	boolean[] mass = new boolean[5];
	for(int i = 1; i <= 5; i++){
		String s = request.getParameter("s" + i);
		mass[i - 1] = s.equals("true") ? true : false;
	}
	boolean wifi = request.getParameter("wifi").equals("true") ? true : false;
	boolean parking = request.getParameter("parking").equals("true") ? true : false;
	boolean beach = request.getParameter("beach").equals("true") ? true : false;
	boolean forest = request.getParameter("forest").equals("true") ? true : false;
	HotelManager hm = HotelManager.getInstance();
	List<Integer> l = hm.getFilteredHotels(mass, beach, forest, wifi, parking);
	request.getSession().setAttribute("searched", l);
 %>
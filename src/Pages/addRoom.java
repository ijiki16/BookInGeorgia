package Pages;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.AccountManager;
import Managers.RoomManager;

/**
 * Servlet implementation class addRoom
 */
@WebServlet("/addRoom")
public class addRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoomManager rmM = RoomManager.getInstance();
		Integer htId;
//		Integer htId = (Integer) request.getSession().getAttribute("hotelId");
		String str1 = request.getParameter("hotel_id");
		//String str2 = (Integer) request.getSession().getAttribute("hotelId");
		
		if(!str1.equals("null")) { 
			htId = Integer.parseInt(request.getParameter("hotel_id"));
		} else {
			htId = (Integer) request.getSession().getAttribute("hotelId");
		}

		boolean wifi = request.getParameter("wifi2").equals("true");
		boolean tv = request.getParameter("tv2").equals("true");
		boolean hotWater = request.getParameter("hotWater2").equals("true");
		boolean airCo = request.getParameter("airCo2").equals("true");
		Integer numBeds = Integer.parseInt(request.getParameter("numBeds2"));
		Integer rPrice = Integer.parseInt(request.getParameter("rPrice2"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = new Date();
		Date eDate = new Date();
		try {
			sDate = format.parse(request.getParameter("sDate2"));
			eDate = format.parse(request.getParameter("eDate2"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer rmId = rmM.addRoom(sDate, eDate, rPrice, "", htId, numBeds, wifi, tv, hotWater, airCo);
		request.getSession().setAttribute("roomId", rmId);
		if(rmId == -1) response.getWriter().print("fail");
	}

}

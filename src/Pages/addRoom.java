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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		if(!request.getParameter("hotel_id").equals("null")) { 
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

//		System.out.println(htId);
//
//		System.out.println(request.getParameter("sDate2"));
//		System.out.println(request.getParameter("eDate2"));
//		System.out.println(wifi);
//		System.out.println(tv);
//		System.out.println(hotWater);
//		System.out.println(airCo);
//		System.out.println(numBeds);
//		System.out.println(rPrice);

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
	}

}

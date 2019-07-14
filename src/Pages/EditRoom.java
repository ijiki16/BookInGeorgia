package Pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Managers.RoomManager;
import Models.Room;

/**
 * Servlet implementation class EditRoom
 */
@WebServlet("/EditRoom")
public class EditRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer hotel_id =  Integer.parseInt(request.getParameter("hotel_id"));
		Integer room_id =  Integer.parseInt(request.getParameter("room_id"));
		Room r = RoomManager.getInstance().getRoom(room_id);
		
		boolean wifi = request.getParameter("wifi").equals("true");
		boolean tv = request.getParameter("tv").equals("true");
		boolean hotWater = request.getParameter("hotWater").equals("true");
		boolean airCo = request.getParameter("airCo").equals("true");
		Integer numBeds = Integer.parseInt(request.getParameter("numBeds"));
		Integer rPrice = Integer.parseInt(request.getParameter("rPrice"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = new Date();
		Date eDate = new Date();
		System.out.println(hotel_id + " " + room_id + " " + wifi+tv+hotWater+airCo + " " + numBeds + " " + rPrice);
		try {
			sDate = format.parse(request.getParameter("sDate"));
			eDate = format.parse(request.getParameter("eDate"));	
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
			
		System.out.println(sDate);
		System.out.println(eDate);
		RoomManager.getInstance().updateRoom(room_id, sDate, eDate, rPrice, r.getImage(), hotel_id, numBeds, wifi, tv, hotWater, airCo);
		request.removeAttribute("room_id");
	}

}

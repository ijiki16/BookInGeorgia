package Pages;

import Managers.AccountManager;
import Managers.RoomManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Integer room_id = Integer.parseInt(request.getParameter("roomid"));
		Date sDate = new Date();
		Date eDate = new Date();
		try {
			sDate = format.parse(request.getParameter("syear") + "-" + request.getParameter("smonth") + "-" + request.getParameter("sday"));
			eDate = format.parse(request.getParameter("eyear") + "-" + request.getParameter("emonth") + "-" + request.getParameter("eday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		RoomManager rm = RoomManager.getInstance();
		
		boolean res = rm.bookRoom(room_id, Integer.parseInt(AccountManager.getInstance().getAccount((String)request.getSession().getAttribute("user")).getId()), sDate, eDate);
		PrintWriter out = response.getWriter();
		if(res == true) out.print("success"); 
		else  out.print("fail");
	}

}

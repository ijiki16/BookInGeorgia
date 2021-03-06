package Pages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.AccountManager;
import Managers.HotelManager;
import Models.Account;
import Models.Hotel;

/**
 * Servlet implementation class EditPost
 */
@WebServlet("/EditPost")
public class EditPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPost() {
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
		HotelManager hm = HotelManager.getInstance();
		AccountManager am = AccountManager.getInstance();
		String user = (String) request.getSession().getAttribute("user");
		Account acc = am.getAccount(user);
		Hotel hotel = hm.getHotel(Integer.parseInt(request.getParameter("hotel_id")));
		
		String name = (String)request.getParameter("name");
		String rating = (String)request.getParameter("rating");
		String status = (String)request.getParameter("status");
		String number = (String)request.getParameter("number");
		String city = (String)request.getParameter("city");
		String address = (String)request.getParameter("address");
		
		hm.updateHotel(hotel.getId(), name.equals("") ? hotel.getName() : name, rating.equals("") ? hotel.getRating() : Integer.parseInt(rating),
			hotel.getImage(), status.equals("") ? hotel.getStatus() : status, 
			number.equals("") ? hotel.getNumber() : number, Integer.parseInt(acc.getId()));
		
		hm.updateLocation(hotel.getId(), city.equals("") ? hotel.getLocation().getCity() : city,
				address.equals("") ? hotel.getLocation().getAddress() : address);
		
		request.removeAttribute("hotel_id");
		response.getWriter().print("Changes Saved");
	}

}

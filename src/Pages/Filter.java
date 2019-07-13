package Pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.HotelManager;

/**
 * Servlet implementation class Filter
 */
@WebServlet("/Filter")
public class Filter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean[] mass = new boolean[5];
		for(int i = 1; i <= 5; i++){
			String s = request.getParameter("s" + i);
			System.out.println("s" + i);
			mass[i - 1] = s.equals("true") ? true : false;
		}
		boolean wifi = request.getParameter("wifi").equals("true") ? true : false;
		boolean parking = request.getParameter("parking").equals("true") ? true : false;
		boolean beach = request.getParameter("beach").equals("true") ? true : false;
		boolean forest = request.getParameter("forest").equals("true") ? true : false;
		HotelManager hm = HotelManager.getInstance();
		List<Integer> l = hm.getFilteredHotels(mass, beach, forest, wifi, parking);
		request.getSession().setAttribute("searched", hm.intersectLists((List<Integer>) request.getSession().getAttribute("searched"), l));
	}
}

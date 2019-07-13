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
		request.getSession().setAttribute("arr", arr);
	}
}

package Pages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.AccountManager;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String success = "success";
    private final String fail = "fail";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = (String)request.getParameter("firstname");
		String lastName = (String)request.getParameter("lastname");
		String email = (String)request.getParameter("email");
		String username = (String)request.getParameter("user");
		String password = (String)request.getParameter("password");
		String birthDate = (String)request.getParameter("bdate");

		String res = success;
		AccountManager manager = AccountManager.getInstance();
		if(!manager.createAccount(firstName, lastName, email, username, password, birthDate)) res = fail;
		else request.getSession().setAttribute("user", username);
		response.getWriter().print(res);
	}

}

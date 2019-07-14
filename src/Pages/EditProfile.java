package Pages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.AccountManager;
import Models.Account;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
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
		
		AccountManager manager = AccountManager.getInstance();
		String user = (String) request.getSession().getAttribute("user");
		Account acc = manager.getAccount(user);

		if(!firstName.isEmpty() && !firstName.equals(acc.getFirstName())) manager.updateAccount(acc, "first_name", firstName);
		if(!lastName.isEmpty() && !lastName.equals(acc.getLastName())) manager.updateAccount(acc, "last_name", lastName);
		if(!email.isEmpty() && !email.equals(acc.getEmail())) manager.updateAccount(acc, "email", email);
		if(!username.isEmpty() && !username.equals(acc.getUsername())) manager.updateAccount(acc, "username", username);
		if(!password.isEmpty() && !password.equals(acc.getPassword())) manager.updateAccount(acc, "password", password);
		response.getWriter().print("Changes Saved");
	}

}

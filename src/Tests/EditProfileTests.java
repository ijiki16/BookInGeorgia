package Tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import Managers.AccountManager;
import Models.Account;
import Pages.EditProfile;

class EditProfileTests extends Mockito {

	@Test
	void test() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		Account acc = mock(Account.class);
		
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("firstname")).thenReturn("devi");
		when(request.getParameter("lastname")).thenReturn("khos");
		when(request.getParameter("email")).thenReturn("dkhos17");
		when(request.getParameter("user")).thenReturn("dkhos");
		when(request.getParameter("password")).thenReturn("0406");
		
		when(session.getAttribute("user")).thenReturn("dkhos17");
		when(acc.getFirstName()).thenReturn("devi");
		when(acc.getLastName()).thenReturn("khos");
		when(acc.getEmail()).thenReturn("dkhos17");
		when(acc.getUsername()).thenReturn("dkhos");
		when(acc.getPassword()).thenReturn("0406");
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        EditProfile server = new EditProfile();
        server.doPost(request, response);
        
        assertTrue(stringWriter.toString().equals("Changes Saved"));
	}

}

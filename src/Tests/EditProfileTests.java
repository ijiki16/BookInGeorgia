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

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import Managers.AccountManager;
import Models.Account;
import Pages.EditProfile;

class EditProfileTests extends Mockito {

	@Test
	@Order (1)
	void testNoUpdate() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("firstname")).thenReturn("devi");
		when(request.getParameter("lastname")).thenReturn("khos");
		when(request.getParameter("email")).thenReturn("dkhos17");
		when(request.getParameter("user")).thenReturn("dkhos");
		when(request.getParameter("password")).thenReturn("0406");
		
		when(session.getAttribute("user")).thenReturn("dkhos17");
		assertTrue(AccountManager.getInstance().createAccount("devi", "khos", "dkhos17", "dkhos","0406", "1999-04-06"));
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        EditProfile server = new EditProfile();
        server.doPost(request, response);
        
        assertTrue(stringWriter.toString().equals("Changes Saved"));
        
        Account acc = AccountManager.getInstance().getAccount("dkhos17");
        assertTrue(acc.getFirstName().equals("devi"));
        assertTrue(acc.getLastName().equals("khos"));
        assertTrue(acc.getEmail().equals("dkhos17"));
        assertTrue(AccountManager.getInstance().deleteAccount("dkhos17", "0406"));
        server.doGet(request, response);
	}
	
	
	@Test
	@Order (2)
	void testUpdate() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("firstname")).thenReturn("Devi");
		when(request.getParameter("lastname")).thenReturn("khose");
		when(request.getParameter("email")).thenReturn("dkhos17@");
		when(request.getParameter("user")).thenReturn("dkhose");
		when(request.getParameter("password")).
			thenReturn("d24f391adc39de4a4c73826ec53208151cf1e226b67e9a6d26bcd286771112bd1a4bc6388a18b62d723f2a332d3489ec83793242cec07ba3ece63320b449555a");
		
		when(session.getAttribute("user")).thenReturn("dkhos17");
		assertTrue(AccountManager.getInstance().createAccount("devi", "khos", "dkhos17", "dkhos","0406", "1999-04-06"));
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        EditProfile server = new EditProfile();
        server.doPost(request, response);

        assertTrue(stringWriter.toString().equals("Changes Saved"));
        
        Account acc = AccountManager.getInstance().getAccount("dkhos17@");
        assertTrue(acc.getFirstName().equals("Devi"));
        assertTrue(acc.getLastName().equals("khose"));
        assertTrue(acc.getEmail().equals("dkhos17@"));
        assertTrue(AccountManager.getInstance().deleteAccount("dkhos17@", "0406"));
	}
	
	@Test
	@Order (3)
	void testUpdateEmpty() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("firstname")).thenReturn("");
		when(request.getParameter("lastname")).thenReturn("");
		when(request.getParameter("email")).thenReturn("");
		when(request.getParameter("user")).thenReturn("");
		when(request.getParameter("password")).thenReturn("");
		
		when(session.getAttribute("user")).thenReturn("dkhos17");
		assertTrue(AccountManager.getInstance().createAccount("devi", "khos", "dkhos17", "dkhos","0406", "1999-04-06"));
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        EditProfile server = new EditProfile();
        server.doPost(request, response);
        
        assertTrue(stringWriter.toString().equals("Changes Saved"));
        
        Account acc = AccountManager.getInstance().getAccount("dkhos17");
        assertTrue(acc.getFirstName().equals("devi"));
        assertTrue(acc.getLastName().equals("khos"));
        assertTrue(acc.getEmail().equals("dkhos17"));
        assertTrue(acc.getUsername().equals("dkhos"));
        assertTrue(AccountManager.getInstance().deleteAccount("dkhos17", "0406"));
	}
}

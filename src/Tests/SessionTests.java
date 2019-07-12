package Tests;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Pages.Session;

public class SessionTests extends Mockito {
	
	@Test
    public void correctInput() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn("dkhos17");
        when(request.getParameter("todo")).thenReturn("not-logout");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        Session server = new Session();
        server.doPost(request, response);

        assertEquals(session.getAttribute("user"), "dkhos17");
        assertTrue(stringWriter.toString().equals("dkhos17"));
        
	}
	
	@Test
    public void correctInput2() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn("dkhos17");
        when(request.getParameter("todo")).thenReturn("logout");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        Session server = new Session();
        server.doPost(request, response);
        
        //verify(session, null).removeAttribute("user");
        assertTrue(stringWriter.toString().equals("dkhos17"));
        server.doGet(request, response);
	}
	
	@Test
    public void incorrectInput() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response  = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(null);
        when(request.getParameter("todo")).thenReturn("not-log-out");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        Session server = new Session();
        server.doPost(request, response);
        
        assertTrue(stringWriter.toString().equals("fail"));
	}
}

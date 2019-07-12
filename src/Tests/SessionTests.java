package Tests;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import Pages.Session;

public class SessionTests extends Mockito {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	@Before
	public void setUp() {
		request = mock(HttpServletRequest.class);
	    response  = mock(HttpServletResponse.class);
	    session = mock(HttpSession.class);
	}
	
	@Test
    public void correctInput() throws Exception {
        
        when(session.getAttribute("user")).thenReturn("dkhos17");
        when(session.getAttribute("todo")).thenReturn("not-logout");
        
        Session server = new Session();
        server.doPost(request, response);
        
        assertEquals(session.getAttribute("user"), "dkhos17");
        verify(response).getWriter().print("dkhos17");
        
        when(session.getAttribute("user")).thenReturn("dkhos17");
        when(session.getAttribute("todo")).thenReturn("logout");
        
        server.doPost(request, response);
        
        assertEquals(session.getAttribute("user"), null);
        verify(response).getWriter().print("dkhos17");
        
	}
	
	@Test
    public void incorrectInput() throws Exception {

        when(session.getAttribute("user")).thenReturn(null);
        when(session.getAttribute("todo")).thenReturn("not-log-out");
        
        Session server = new Session();
        server.doPost(request, response);
        
        verify(response).getWriter().print("fail");
	}
}

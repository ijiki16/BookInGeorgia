package Tests;


import Managers.AccountManager;
import Pages.Login;
import Pages.Register;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
public class RegistrationAndLoginTests extends Mockito{
	
	@Test
	@Order(1)
	// Registration success.
	public void test1() throws ServletException, IOException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getParameter("firstname")).thenReturn("nika");
        when(request.getParameter("lastname")).thenReturn("basiashvili");
        when(request.getParameter("email")).thenReturn("nbasi");
        when(request.getParameter("user")).thenReturn("nika97");
        when(request.getParameter("password")).thenReturn("123456");
        when(request.getParameter("bdate")).thenReturn("1997-12-19");
        when(request.getSession()).thenReturn(session);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new Register().doPost(request, response);
        verify(request, atLeast(1)).getParameter("user");
        writer.flush();
        assertTrue(stringWriter.toString().equals("success"));
        
	}
	
	@Test
	@Order(2)
	//Registration fail
	public void test2() throws ServletException, IOException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getParameter("firstname")).thenReturn("nika");
        when(request.getParameter("lastname")).thenReturn("basiashvili");
        //Can't add because user with this email already exists.
        when(request.getParameter("email")).thenReturn("nbasi");
        when(request.getParameter("user")).thenReturn("user928826");
        when(request.getParameter("password")).thenReturn("123456");
        when(request.getParameter("bdate")).thenReturn("1997-12-19");
       
        when(request.getSession()).thenReturn(session);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new Register().doPost(request, response);
        
        verify(request, atLeast(1)).getParameter("user");
        writer.flush(); 
        assertTrue(stringWriter.toString().equals("fail"));
        new Register().doGet(request, response);
	}
	
	@Test
	@Order(3)
	//Login success
	public void test3() throws ServletException, IOException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getParameter("user")).thenReturn("nbasi");
        when(request.getParameter("password")).thenReturn("123456");
        when(request.getSession()).thenReturn(session);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new Login().doPost(request, response);
        
        verify(request, atLeast(1)).getParameter("user");
        writer.flush(); 
        assertTrue(stringWriter.toString().equals("nika"));
        new Login().doGet(request, response);
	}
	
	@Test
	@Order(4)
	//Login fail
	public void test4() throws ServletException, IOException {
	
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getParameter("user")).thenReturn("nbasi");
        when(request.getParameter("password")).thenReturn("arasworiparoli");
        when(request.getSession()).thenReturn(session);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new Login().doPost(request, response);
        
        verify(request, atLeast(1)).getParameter("user");
        writer.flush(); 
        assertTrue(stringWriter.toString().equals("fail"));
	}
	
	@Test
	@Order(5)
	//deleting from DB.
	public void test5() {
		AccountManager manager = AccountManager.getInstance();
		assertTrue(manager.deleteAccount("nbasi", "123456"));
	}
}

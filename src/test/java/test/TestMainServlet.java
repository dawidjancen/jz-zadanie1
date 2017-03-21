package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import servlets.MainServlet;

public class TestMainServlet extends Mockito {

	@Test
	public void servlet_should_not_submit_form_if_anything_is_null() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		MainServlet servlet = new MainServlet();
		
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		
		when(request.getParameter("kredyt")).thenReturn(null);
		when(request.getParameter("raty")).thenReturn(null);
		when(request.getParameter("oprocentowanie")).thenReturn(null);
		when(request.getParameter("oplata")).thenReturn(null);
		when(request.getParameter("rodzaj")).thenReturn(null);
		
		servlet.doGet(request, response);
		
		verify(response).sendRedirect("/");
	}
	
	@Test
	public void servlet_should_not_submit_form_if_anything_is_empty() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		MainServlet servlet = new MainServlet();
		
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);
		
		when(request.getParameter("kredyt")).thenReturn("");
		
		servlet.doGet(request, response);
		
		verify(response).sendRedirect("/");
	}

}

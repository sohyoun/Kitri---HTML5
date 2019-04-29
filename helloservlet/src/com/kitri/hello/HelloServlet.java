package com.kitri.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hs") //이게 servlet-name 아까 그부분 알아서 해주는 것
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println(" 	<body>");
		out.println(" 	Hello Servlet3.0!!!<br>");
		out.println(" 	안녕 서블릿3.0!!!!!");
		out.println(" 	</body>");
		out.println("</html>");
	}


}

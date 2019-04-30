package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ggd")
public class GGB extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GGB() {
       
    }
    
    @Override
	public void init() throws ServletException {

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<table width=\"600\" height=\"400\" border=\"1\" align=\"center\">");
		out.println("	<caption>**구구단**</caption>");
		String ggbresult="";
		for(int j=1;j<10;j++) {
			out.println("	<tr align=\"center\">");
			for(int i=2;i<10;i++) {
				ggbresult=i+"*"+j+"="+(i*j);
				String bgc = i%2==0 ? "pink" : "steelblue";
				out.println("		<td bgcolor=\""+bgc+"\">"+ggbresult+"</td>");
			}
			out.println("	</tr>");
			
		}
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}


}

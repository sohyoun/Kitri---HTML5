package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/counter")
public class Counter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Counter() {
       
    }

	int cnt;//cnt는 전역변수여야한다. doGet method안에 있으면 항상 0값으로 되어버림.
	public void init() {
		cnt=0;
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cnt++;
		String cnts=cnt+"";
		int length=cnts.length();
		System.out.println(cnts.length());
		for(int i=0; i<(8-length); i++) {
			cnts="0"+cnts;
		}
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		out.println("당신은 ");
		for(int i=0;i<8;i++) {
		char c = cnts.charAt(i);
		out.println("<img src=\"/basicservlet/img/"+c+".png\" width=\"50\">");
		}
		out.println("번째 방문자입니다.");
		out.println("	</body>");
		out.println("</html>");
	}


}

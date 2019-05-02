package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/multiparam")
public class MultiParameterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		1.data get
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] fruits = request.getParameterValues("fruit");
		
//		2.logic
		String color = age == 10 ? "pink" : "cyan";
		
//		3.response
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(name+"(");
		out.println("<font color=\""+color+"\">");
		out.println(id);
		out.println("</font>");
		out.println(")님 안녕하세요.");
		
		if (fruits == null) {
			out.println("좋아하는 과일이 없습니다.");//println은 공백이 있음
		} else {
			out.print("당신이 좋아하는 과일은 ");//print는 공백X
			for (int i = 0; i < fruits.length; i++) {
				if(i==fruits.length-1) {
					out.print(fruits[i]);
				} else {
					out.print(fruits[i]+", ");
				}
			}
			out.println("입니다.");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}

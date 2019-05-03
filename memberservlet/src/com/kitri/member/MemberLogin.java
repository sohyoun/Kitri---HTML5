package com.kitri.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {//3)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//4)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//보안때문에 post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;			//5)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		1. data get (아이디, 비번)
		request.setCharacterEncoding("utf-8");//2)
		
		String id = request.getParameter("id");		//1)
		String pass = request.getParameter("pass");
		
//		2. Logic : 1의 결과에 따라 data select
//		select name
//		from member
//		where id = ?
//		and pass = ?
		
		String name = null;//13)
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");//6)
			StringBuffer sql = new StringBuffer();//7)
			sql.append("select name\n");//8)
			sql.append("from member\n");
			sql.append("where id = ?\n");
			sql.append("and pass = ?\n");
			
			pstmt = conn.prepareStatement(sql.toString()); //9)
			pstmt.setString(1, id);		//10)
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();//11)
			if(rs.next()) {//12)
				name = rs.getString("name");//14)
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//15)
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
//		3. response : 2의 결과에 따라
//			3-1. name != null : 홍길동님 안녕하세요
//			3-2. name == null : 아이디 또는 비밀번호를 다시 확인하세요.
//								 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
//								 로그인
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println(" 	<body>");
		if(name != null) {
			out.println("<strong>"+name+"</strong>님 안녕하세요.<br>");
		}else {
			out.println("<font size=\"13\" color=\"red\">");
			out.println("아이디 또는 비밀번호를 다시 확인하세요.<br>");
			out.println("등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
			out.println("</font>");
			out.println("<a href=\"/memberservlet/user/login.html\">로그인</a>");
		}
		out.println(" 	</body>");
		out.println("</html>");
	}

}

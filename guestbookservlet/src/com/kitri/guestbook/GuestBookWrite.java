package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gbwrite")
public class GuestBookWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@Override
	public void init(ServletConfig config) throws ServletException {//3)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//4)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
//		1. data get ( name, subject, content )
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");	
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
//		2. Logic
		int cnt=0;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			StringBuffer sql = new StringBuffer();
			sql.append("insert into guestbook(seq, name, subject, content, logtime)"
					+ "values(guestbook_seq.nextval, ?, ?, ?, sysdate)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
//		3. response : 저장되었습니다.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println(" 	<body>");
		if(cnt !=0 ) {
			out.println("글 등록 완료");
			
		} else {
			
		}
		out.println(" 	</body>");
		out.println("</html>");
	}

}

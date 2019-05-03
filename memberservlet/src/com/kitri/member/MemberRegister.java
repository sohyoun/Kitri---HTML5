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


@WebServlet("/register")
public class MemberRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {//3)init override 할 때 config 가져오는 놈으로 하기!
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//4)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//보안때문에 post로 해야함
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//5)
		Connection conn = null;//선언은 맨 위에
		PreparedStatement pstmt = null;

//		1. data get (이름, 아이디, 비번, 이메일1, 이메일2, 전번1, 전번2, 전번3, 우편번호, 주소, 상세주소)
		request.setCharacterEncoding("UTF-8");//한글 깨짐 방지	2)
		//1)
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String emailid = request.getParameter("emailid");
		String emaildomain = request.getParameter("emaildomain");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("address_detail");

//		2. Logic : 1의 data를 DB에 insert
//		insert all
//			into member(id,name,pass,emailid,emaildomain,joindate)
//			values(?,?,?,?,?,sysdate)
//			into memberdetail(id,zipcode,address,address_detail,tel1,tel2,tel3)
//			values(?,?,?,?,?,?,?)
//		select * from dual
		int cnt=0; //8)
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			StringBuffer sql = new StringBuffer(); //가능하면 sql은 String buffer로!
			sql.append("insert all\n");//6) editplus에서 작성 
			sql.append("	into member(id,name,pass,emailid,emaildomain,joindate)\n");
			sql.append("	values(?,?,?,?,?,sysdate)\n");
			sql.append("	into member_detail(id,zipcode,address,address_detail,tel1,tel2,tel3)\n");
			sql.append("	values(?,?,?,?,?,?,?)\n");
			sql.append("select * from dual\n");
			pstmt = conn.prepareStatement(sql.toString());//preparedstatement는 sql문장을 먼저 가져간다. StringBuffer를 String으로 바꿔야함.
			int idx=0;
			pstmt.setString(++idx, id);//7)
			pstmt.setString(++idx, name);
			pstmt.setString(++idx, pass);
			pstmt.setString(++idx, emailid);
			pstmt.setString(++idx, emaildomain);
			pstmt.setString(++idx, id);
			pstmt.setString(++idx, zipcode);
			pstmt.setString(++idx, address);
			pstmt.setString(++idx, addressdetail);
			pstmt.setString(++idx, tel1);
			pstmt.setString(++idx, tel2);
			pstmt.setString(++idx, tel3);//물음표 갯수와 일치해야함
			
			cnt = pstmt.executeUpdate();//9) preparedstatement는 여기선 sql문장 안가져감
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//10)
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
//		3. response page : 2의 결과에 따라
//			3-1. !0 : (성공) 홍길동님 회원가입을 환영합니다.	>>쿼리가 몇개일 지 모름으로 1일때,2일때 하는 것이 아니라 0이 아닐 때
//			3-2. 0	: (실패) 서버 문제로 회원 가입이 실패하였습니다.
//							다음에 다시 시도하세요.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println(" 	<body>");
		if(cnt!=0) {
			out.println(name+"님 회원가입을 환영합니다.<br>");
			out.println("로그인 후 모든 서비스를 이용할 수 있습니다.<br>");
			out.println("<a href=\"/memberservlet/user/login.html\">로그인</a>");
		}else {
			out.println("<font size=\"13\" color=\"red\">");
			out.println("서버문제로 회원가입이 실패하였습니다.<br>");
			out.println("다음에 다시 시도하세요.");
			out.println("</font>");
		}
		out.println(" 	</body>");
		out.println("</html>");
		
	}

	

}

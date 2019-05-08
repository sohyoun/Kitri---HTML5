<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*,java.net.URLEncoder"%>

<%!
public void init() {//3)init override 할 때 config 가져오는 놈으로 하기!
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//4)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
}%>
<%
Connection conn = null;//선언은 맨 위에
PreparedStatement pstmt = null;

//	1. data get (이름, 아이디, 비번, 이메일1, 이메일2, 전번1, 전번2, 전번3, 우편번호, 주소, 상세주소)
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

int cnt = 0; //8)
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
	int idx = 0;
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
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
String root=request.getContextPath();
if (cnt != 0) {
	response.sendRedirect(root+"/user/registerok.jsp?name=" + URLEncoder.encode(name, "UTF-8")); //한글깨짐 (우리가 직접적으로 쿼리스트링 만들 때)
} else {
	response.sendRedirect(root+"/user/registerfail.jsp");
}%>

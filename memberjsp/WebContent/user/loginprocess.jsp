<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.sql.*,java.net.URLEncoder"%>
    <%@ include file="/templete/header.jsp" %>
    <%!
    public void init() {//3)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//4)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}%>
	<%
    Connection conn = null;			//5)
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
//	1. data get (아이디, 비번)
	request.setCharacterEncoding("utf-8");//2)
	
	String id = request.getParameter("id");		//1)
	String pass = request.getParameter("pass");
    
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

if(name != null) {
	response.sendRedirect(root+"/user/loginok.jsp?name=" + URLEncoder.encode(name, "UTF-8"));
}else {
	response.sendRedirect(root+"/user/loginfail.jsp");
}%>

<%@ include file="/templete/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.sql.*"%>

<%@ page import="javax.servlet.ServletException"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%!
int cnt;
public void init() {
	cnt=0;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
}
%>
<%
Connection conn = null;
Statement stmt= null;
ResultSet rs = null;
try {
	conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
	String sql="";
	sql += "select no \n";
	sql	+= "from counter";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	rs.next();
	cnt = rs.getInt(1);
	
	sql = "update counter \n";
	sql	+= "set no = no + 1 \n";
	stmt.executeUpdate(sql);
	
} catch (SQLException e) {
	e.printStackTrace();
} finally {
	try {
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(conn!=null)
			conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
String cnts=cnt+"";
int length=cnts.length();
System.out.println(cnts.length());
for(int i=0; i<(8-length); i++) {
	cnts="0"+cnts;
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
당신은 
<%
for(int i=0;i<8;i++) {%>
<img src="/basicjsp/img/<%=cnts.charAt(i)%>.png" width="50">
<%}%>
번째 방문자입니다.
</body>
</html>
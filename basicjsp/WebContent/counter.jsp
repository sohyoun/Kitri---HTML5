<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
int cnt;
public void init() {
	cnt=0;
	
}
%>
<%
cnt++;
		String cnts=cnt+"";
		int length=cnts.length();
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
<%for(int i=0;i<8;i++) {%>
<img src="/basicjsp/img/<%=cnts.charAt(i)%>.png" width="50">
<%}%>
번째 방문자입니다.
</body>
</html>
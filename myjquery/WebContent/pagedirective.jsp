<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page buffer="100kb" %><!-- buffer를 크게줘서 1~10000까지 충분히 가능 -->
<%@page errorPage="err.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//1~10000출력
for(int i=1; i<=10000; i++){%>
<%=i%>
<%}%>

<%
//a.txt파일을 byte단위로 읽기 위한 스트림 객체 생성
FileInputStream fis = null;
fis = new FileInputStream("a.txt");
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
//1.data get
String name = request.getParameter("name");
String id = request.getParameter("id");
int age = Integer.parseInt(request.getParameter("age"));
String color = age == 10 ? "pink" : "cyan";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=
//3.response page : 안효인(java2)님 안녕하세요. > 10대 밑이면 아이디 빨간색으로 아니면 파란색으로
name+"("%>
<font color="<%=color%>">
<%=id%>
</font>
<%=")님 안녕하세요."%>
</body>
</html>
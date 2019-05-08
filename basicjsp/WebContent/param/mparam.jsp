<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");
String id = request.getParameter("id");
int age = Integer.parseInt(request.getParameter("age"));
String[] fruits = request.getParameterValues("fruit");

//2.logic
String color = age == 10 ? "pink" : "cyan";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=name+"("%>
<font color="<%=color%>">
<%=id%>
</font>
<%=")님 안녕하세요."%>
<%if(fruits == null) {%>
<%="좋아하는 과일이 없습니다."%>
<%} else {%>
<%="당신이 좋아하는 과일은 "%>
<%for (int i = 0; i < fruits.length; i++) {
				if(i==fruits.length-1) {%>
					<%=fruits[i]%>
				<%} else {%>
					<%=fruits[i]%>
					<%="," %>
				<%}
			}%>
			<%="입니다."%>
		<%}
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>포함지시자</h3>>>똑같이 포함되야할 때
<%@include file="loginresult.jsp" %>
<hr>
<h3>포함태그</h3>>>동적으로 변해야할 때 >>이걸 많이 씀
<jsp:include page="loginresult.jsp"/>
</body>


</html>
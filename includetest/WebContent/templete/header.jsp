<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!String name="박소현"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<table width="1000" border="1">
<tr>
	<td colspan="2" height="120">로고가 나와요...<br>
	<%if(true) { %>
	로그인해야해
	<%} else { %>
	박소현님 안녕하세요
	<%} %>
	</td>
</tr>
<tr height="700">
	<td width="150">
	<a href="/includetest/board/board.jsp">게시판1</a><br>
	<a href="/includetest/board/album.jsp">사진게시판2</a>
	</td>
	<td>
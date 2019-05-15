<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
int totalp=0;//총요청횟수
int totalstar=0;//별점합
%>
<%
totalp++;
int star = Integer.parseInt(request.getParameter("star"));
totalstar += star;
%>
참여자수 : <%=totalp%>명<br>
총 별점 : <%=totalstar%>점

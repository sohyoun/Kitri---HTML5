<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%request.setCharacterEncoding("UTF-8"); %>
<%
String id = request.getParameter("id"); 
String name = request.getParameter("name");
%>
<%
Thread.sleep(10);
%>
<%=id %>(<%=name %>)님 반갑습니다.
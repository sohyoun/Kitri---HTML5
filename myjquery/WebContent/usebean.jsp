<%@page import="com.kitri.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usebean.jsp</title>
</head>
<body>
<%-- <%
//1. request의 속성(이름:"c", 타임:com.kitri.dto.Customer)얻기
Customer c = (Customer)request.getAttribute("c");

//2. 1속성이 null인 경우
//	 Customer 객체생성하여 c참조변수에 대입
//	 c참조변수를 request의 속성(이름:"c")로 추가
if(c == null){
	c = new Customer();
	request.setAttribute("c", c);
}
%> --%>
<!-- 위의 다섯줄 코드를 아래의 useBean을 이용하여 한줄로 할 수 있다. -->
<jsp:useBean id="c" class="com.kitri.dto.Customer" scope="request"/>


<%-- <%
c.setId("id1");
%> --%>
<jsp:setProperty name="c" property="id" value="id1" />


<%-- <%
c.setName(request.getParameter("n"));
%> --%>
<jsp:setProperty name="c" property="name" param="n"/>


<%-- <%=c.getId() %> --%>
<jsp:getProperty name="c" property="id"/>

</body>
</html>
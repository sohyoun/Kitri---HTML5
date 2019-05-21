<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--JSTL : JspStandardTagLib --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%--외부 library쓸 때 taglib사용 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl.jsp</title>
</head>
<body>
<c:set var="num" value="99"/> 										<%-- == <%int num=99; %> --%>
<c:set var="num2" value="${param.num}"></c:set>						<%-- == <%int num2 = Integer.parseInt(request.getParameter("num"));%> --%>
<c:if test="${num%2==0}"><!--  >>el문법기반으로 test의 조건문을 써야 한다. -->	<%-- == <%if(num%2 ==0){} %> --%>
짝수입니다.<br>	 <!-- >>if는 있으나 else if는 없다. >>c:choose / c:when 사용 -->
</c:if>	

<hr>

<c:if test="${num2%2==0}"> <!-- >>num2는 빈문자열이었지만 산술연산자인 %를 만나면서 자동으로 숫자타입으로 바뀌며 0으로 된다. (nullpointerexception이 나지 X) -->
짝수입니다.<br>	
</c:if>	

<hr>

<c:choose>
	<c:when test="${num%2==0}">
		짝수입니다.
	</c:when>
	<c:otherwise>
		홀수입니다.
	</c:otherwise>
</c:choose>

<hr>

<c:forEach begin="1" end="10" step="1" var="i">	<%-- >>반복문 : 1부터 10까지 1씩 증가하며 i에 담음			== <%for(int i=1; i<=10; i++){} %> --%>
	${i}
</c:forEach>

<hr>

<c:set var="total" value="0"/>
<c:forEach begin="1" end="10" var="i">
	<c:set var="total" value="${total+i}"/> <%--변수가 기존에 있으면 재사용됨 --%>
</c:forEach>
1~10합 : ${total}

<hr>

<%
List<String> list = new ArrayList<>();
list.add("one");
list.add("two");
list.add("three");
request.setAttribute("list", list);
%>
<%--for(String e:(String)request.getAttribute("list")){} --%>
<c:forEach var="e" items="${requestScope.list}">
	${e}<br>
</c:forEach>
<hr>
<c:forEach var="e" items="${requestScope.list}" varStatus="obj"> <%--varStatus는 obj(이름 아무거나 상관X)라는 for문의 정보를 갖고있는 객체를 만들게 된다. obj.index하게 되면 for문의 index값(items의 몇번 요소를 사용하고 있는지!) 나타남 --%>
	${obj.index} - ${e} : ${obj.count}회<br> <%--obj.count는 for문의 현재 횟수 --%>
</c:forEach>
</body>
</html>
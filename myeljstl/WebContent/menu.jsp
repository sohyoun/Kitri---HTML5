<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
<c:set var="id" value="${sessionScope.loginInfo}"/>
<c:choose>
	<c:when test="${empty id}">	<%--empty : el만의 null이거나 빈문자열임을 표시하는 문법 --%>	<%--로그인을 안한경우--%>	<%--주석은 !--이(html) 아니라 %--으로(jsp) 하는 것이 안전 --%>
		<li><a href="user/login.html">로그인</a></li>
  		<li><a href="user/signup.html">가입</a></li>  
	</c:when>
	<c:otherwise>	<%--로그인 한 경우 --%>
		<li><a href="logout">로그아웃</a></li>
	</c:otherwise>
</c:choose>

  <li><a href="productlist">상품목록</a>
  <li><a href="productlistjson">상품목록JSON</a>
  <li><a href="viewcart">장바구니 보기</a>

<c:choose>
	<c:when test="${!empty id}">	<%--로그인을 한 경우--%>
		<li><a href="vieworder">주문내역 보기</a>
	</c:when>
</c:choose>
</ul>
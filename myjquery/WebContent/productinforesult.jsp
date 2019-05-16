<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Product p = (Product)request.getAttribute("detailresult");%>
<table>
	<tr>
		<td>
		<img src="/myjquery/img/<%=p.getProd_no()%>.jpg">
		</td>
		<td>
		<div>
		상품이름 : <%=p.getProd_name()%><br>
		상품설명 : <%=p.getProd_detail()%><br>
		상품번호 : <%=p.getProd_no()%><br>
		상품가격 : <%=p.getProd_price()%>
		</div>
		</td>
	</tr>
</table>

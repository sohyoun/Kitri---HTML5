<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.kitri.dto.Product"%>
<%!List<Product> list = new ArrayList();
%>
<%
list = (List<Product>)request.getAttribute("listresult");
for (Product p : list) { %>
<span id="<%=p.getProd_no()%>">
<img src="/myjquery/img/<%=p.getProd_no()%>.jpg"><br>
카테고리:	<%=p.getProductCategory().getCate_name()%><br>
상품번호:	<%=p.getProd_no()%><br>
상품명:	<%=p.getProd_name()%><br>
가격: 	<%=p.getProd_price()%><br>
</span>
<%
}
%>

<script>
$(function(){
	var aSpan = $("span");
	$(aSpan).click(function() {
		//console.log("proresult: click");
		$.ajax({
			url : "/myjquery/productinfo",
			method : "get",
			data : "no="+$(this).attr("id"),
			success : function(result){
				$("section").html(result);
			},
			error :  function() {
				console.log("에러남");
			}
		});
		
		return false;
	});
	
});
</script>
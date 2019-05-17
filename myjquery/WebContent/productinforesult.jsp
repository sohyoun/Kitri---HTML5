<%@page import="com.kitri.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(function(){
	var $bt = $(".submit dl>dt>button");
	$bt.click(function(){
		$.ajax({
			url : 'addcart',
			method : 'get',
			data : 'no='+$("input[name=no]").val()+'&quantity='+$("input[name=quantity]").val(),
			success : function(result){
				//$("section").html(result.trim());
				$("div.addcartresult").remove();
				$("section").append(result.trim());
			}
		});
		return false;
	});
});

</script>
<% Product p = (Product)request.getAttribute("detailresult");%>
		<!-- <form action="addcart" method="get"> //이렇게하면 화면이 아예 바뀜 >> ajax로 바꾸자-->
		<input type="hidden" name="no" value="<%=p.getProd_no()%>">
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
		
		<li class="quantity">
		<dl><dt>수량</dt>
			<dd><input type="number" name="quantity" value="1" min="1" max="99"></dd>
		</dl>
		</li>
		<li class="submit">
		<dl><dt><button>장바구니넣기</button></dt></dl>
		</li>
		</div>
		</td>
	</tr>
</table>
		<!-- </form> -->
		
		<section>
		
		</section>

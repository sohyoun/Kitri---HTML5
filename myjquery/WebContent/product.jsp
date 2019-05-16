<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
$(function(){ //window.onload랑 비슷
	//console.log("ajax호출");
	$.ajax({ //화면의 일부분이 바뀔 때 사용(아예 새 화면으로 전환되는 것은 ajax사용 X)
		url : "/myjquery/productlist", 
		method : "get",
		/* data : $("form > div").serialize(), <!--productresult의 div를 가져 와라--><!--이 페이지에 받아서 가져갈 data가 없음으로 없어도 됨.()--> */
		success : function(result){
			$("#foodlist").html(result);
		},
		error : function() {
			//console.log("에러남");
		}
	});
	
});

</script>

<div id="foodlist">
</div>

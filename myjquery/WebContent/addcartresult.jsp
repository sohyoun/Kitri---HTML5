<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(function(){
	var arr = $("div.addcartresult>button");
	//arr[0]//첫번째 버튼 : 상품목록으로 가기
	$(arr[0]).click(function(){
		alert("상품목록으로 가기 클릭했습니다.");
		//메뉴즁 상품목록메뉴 찾기
		//강제 클릭이벤트 발생시키기
		$("nav>ul>li>a[href=productlist]").trigger("click");
		return false;
	});
});

</script>
<div class ="addcartresult" style="position:absolute;top:200px;left:100px;width:250;height:100;padding-top:50;border:1px solid;background-color:steelblue;text-align:center;">
장바구니에 넣었습니다!
<button>상품목록으로 가기</button>
<button>장바구니 보기</button>
</div>
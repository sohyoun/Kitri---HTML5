<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="pb" value="${requestScope.pb}"/>

<style>
table{
	border: 1px solid;
	text-align: center;
	
}
.contents{
	margin-left: 40%;
}
</style>
<script>
/* $(function(){	
	$(".gonum>span").click(function(){
		$(this).val() = $(pb.currentPage);
	});
}); */
</script>
<div class="contents">
<h3>게시글 목록</h3>
<h6>현재페이지 : ${pb.currentPage}총페이지: ${pb.totalPage}</h6>
<table>
	<tr>
		<th>글번호</th>
		<th>글제목</th>
		<th>작성자</th>
		<th>조회수</th>
	</tr>
<c:forEach var="repBoard" items="${pb.list}">
	<tr>
		<td>${repBoard.board_seq}</td>
		<td>${repBoard.board_subject}</td>
		<td>${repBoard.board_writer}</td>
		<td>${repBoard.board_viewcount}</td>
	</tr>
</c:forEach>
</table>
<!-- 
현재 페이지가 1이면	1,2,3
현재페이지가 2이면	1,2,3
현재페이지가 3이면	1,2,3

현재페이지가 4이면	4,5,6
현재페이지가 5이면	4,5,6
현재페이지가 6이면	4,5,6
1/4/7 >>(currentPage+2)/3 ==0 >>currentPage / currentPage+1 / currentPage+2
2/5/8 >>(currentPage+1)/3 ==0 >>currentPage-1 / currentPage / currentPage+1
3/6/9 >>currentPage/3 ==0 >> currentPage-2 / currentPage-1 / currentPage
현재페이지 = 1이면 >> 뒤로가기표시X
현재페이지 = 총페이지수면 >> 앞으로가기표시X
 -->
<span class="goback">
<c:if test="${pb.currentPage != 1 }">
	<a href="${pb.startPage - 1}">◀</a>
</c:if>
</span>
<span class="gonum">
<c:forEach begin="${pb.startPage}" end="${pb.endPage}" var="i">
     <c:choose>
       <c:when test="${pb.currentPage == i}">
         <span>${i}</span>
       </c:when>
       <c:otherwise>  
         <span><a href="${i}">${i}</a></span> 
       </c:otherwise>
     </c:choose>  
   </c:forEach>
</span>
<span class="gofront"> 
<c:if test="${pb.currentPage != pb.totalPage}">
	<a href="${pb.endPage+1}">▶</a>
</c:if>
</span>
</div>
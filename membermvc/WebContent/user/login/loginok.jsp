<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDto,com.kitri.util.MoveUrl"%>
<%@ include file="/templete/header.jsp" %>
<%
MemberDto memberDto = (MemberDto)session.getAttribute("userInfo"); // request가 아닌 session
//request에 userinfo를 담았더니 로그인을 하고 다른페이지를 갔다가 다시오면 로그아웃이 되어있다. request는 controller에서 어디로 가라고 할 때 까지만 유지가 된다.
//하지만 session은 내가 session에서 지우거나 특정 시간(30)분이 지나기 전까지 유지가 된다. >> session은 바구니다.session 사용해야함. 
//근데 이렇게 하나씩 session 지정해주기보다 templete에 넣어놓고 나머지 jsp에는 include file="/template/header.jsp" 등으로 해주면 된다!

if(memberDto != null){
%>
<script type="text/javascript">
function deleteMember() {
	if(confirm("정말 탈퇴??")) {
		document.location.href = "<%=root%>/user?act=deletemember";
	}
}
</script>
<strong><%=memberDto.getName()%>(<%=memberDto.getId()%>)</strong>님 안녕하세요.<br>
<a href="<%=root%>/user?act=logout">로그아웃</a>
<a href="<%=root%>/user?act=mvmodify">정보수정</a>
<a href="#" onclick="javascript:deleteMember();">회원탈퇴</a>
<%
	if("java2".equals(memberDto.getId())) {
%>
<a href="<%=root%>/admin?act=memberlist">관리자</a>
<%
	}
} else {
	MoveUrl.redirect(request, response, "/user?act=mvlogin");//jsp에서 jsp로 바로 안가고 Controller 들려야함. 
															//따라서 user/login.jsp가 아님!
}
%>

<%@ include file="/templete/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/templete/header.jsp"></jsp:include> jsp:include는 page이다. 즉 실행된 결과화면을 단순히 출력하는 것이다.>>단순히 화면만 포함하는 경우 
<%@ include file="/templete/header.jsp"%> >>파일을 통째로 복사해서 써야하는 경우 (name과 같은 변수를 써야하는 경우) --일반적으로 이 방법을 쓴다.
											>>변수 중복되지 않게 주의해야함
<%=name%>사진이나온다	>>header에 쓴 변수를 여기서 쓸 수 있다.
<%@ include file="/templete/footer.jsp"%>
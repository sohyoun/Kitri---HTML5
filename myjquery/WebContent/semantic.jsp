<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<title>semantic.html</title>
<style>
header {
	background-image:
		url("https://ditto-phinf.pstatic.net/20181226_128/1545803146647AnpmJ_JPEG/b556d1792ec8f4c15c22f12acbe513f7.jpeg");
	background-repeat: no-repeat;
	background-position: center;
}

nav>ul {
	list-style: none;
	padding: 0px;
}

nav>ul>li {
	display: inline-block;
}

nav>ul>li>a:hover {
	text-decoration: none;
	background-color: yellow;
	color: blue;
}

footer {
	position: fixed;
	bottom: 0px;
	width: 100%;
	background-color: gray;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(function() {
		//dom트리에서 nav>ul>li>a 객체들 찾기
		var aArr = $("nav>ul>li>a");
		$(aArr).click(function() {
			var vurl = $(this).attr("href"); //login.html or signup.html
			if (vurl == 'logout') {
				$.ajax({
					url : vurl,//요청할 url
					method : 'get',//요청방식
					success : function(result) {
						alert(result.trim());
						location.href="semantic.jsp";
					}//결과를 응답받은 응답내용이 function의 인자값으로 받아짐.  
				});
			} else {
				$.ajax({
					url : vurl,//요청할 url
					method : 'get',//요청방식
					success : function(result) {
						$("section").html(result);
					}//결과를 응답받은 응답내용이 function의 인자값으로 받아짐.  
				});
			}
			return false;
		});
	});
</script>
</head>
<body>
	<header>
		<h1>My Web</h1>
	</header>
	<nav>
		메뉴
		<jsp:include page="menu.jsp"/>
	</nav>
	<section>본문</section>
	<footer>사업자 : 현대자동차(주) | 대표 : 박소현</footer>
</body>
</html>
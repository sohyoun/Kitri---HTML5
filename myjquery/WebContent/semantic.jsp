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
	height : 200px;
	text-align: center;
	background-image:
		url("https://www.narita-airport.jp/img/original/3599");
	background-repeat: no-repeat;
	background-position: left;
	background-size: 200px;
}
header>h1 {
	padding-top: 70px;
}

nav>ul {
	background-color:#04643c;
	list-style: none;
	padding-top: 10px;
	height:50px;
}

nav>ul>li {
	display: inline-block;
}
nav>ul>li>a {
	color:white;
	font-size: 20px;
	font-weight: bold;
	margin-left: 8px;
}
nav>ul>li>a:hover {
	text-decoration: none;
	background-color: white;
	color: black;
}

footer {
	position: fixed;
	bottom: 0px;
	width: 100%;
	background-color: #04643c;
	color: white;
	padding-left: 10px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
////////////////////////////////////logout//////////////////////////////////////
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
	
////////////////////////////productlist////////////////////////////////////////	
	$(function() {//window.onload랑 비슷
		//console.log("ajax호출");
		//dom트리에서 nav>ul>li>a 객체들 찾기
		var aArr = $("nav>ul>li>a");
		$(aArr).click(function() {
			var vurl = $(this).attr("href");		
			$.ajax({ //화면의 일부분이 바뀔 때 사용(아예 새 화면으로 전환되는 것은 ajax사용 X)
				url : vurl, 
				method : "get",
				/* data : $("form > div").serialize(), <!--productresult의 div를 가져 와라--><!--이 페이지에 받아서 가져갈 data가 없음으로 없어도 됨.()--> */
				success : function(result){
					$("section").html(result);
				},
				error : function() {
					//console.log("에러남");
				}
			});
		});
	});
</script>
</head>
<body>
	<header>
		<h1>KKiruk's Starbucks</h1>
	</header>
	<nav>
		<jsp:include page="menu.jsp"/>
	</nav>
	<section></section>
	<footer>사업자 : 스타벅스코리아(주)  |  대표 : 박소현</footer>
</body>
</html>
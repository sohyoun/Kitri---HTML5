<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
<h3>**구구단**</h3>
<table border="1">
	<%!String ggdresult="";%>
	<% for(int i=2;i<10;i++){%>
 		<tr>
		 <%for(int j=1; j<10; j++){%>
			
		 <%ggdresult = i+"*"+j+"="+(i*j);%>
		 <td>
		 <%out.println(ggdresult);%>
		</td>
		<%}%>
		 </tr>
	<%}%>
</table>

<hr>
<h3>**구구단**</h3>
<table border="1">
	<%!String ggdresult2="";%>
	<% for(int i=2;i<10;i++){%>
 		<tr>
		 <%for(int j=1; j<10; j++){%>
			
		 <%ggdresult2 = i+"*"+j+"="+(i*j);%>
		 <td>
		 <%=ggdresult2%>
		</td>
		<%}%>
		 </tr>
	<%}%>
</table>
<hr>
<h3>**구구단**</h3>
</div>
</body>
</html>
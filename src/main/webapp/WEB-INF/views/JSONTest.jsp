<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(function(){
	$("#checkJson").click(function(){
		var member = {id:"park", name:"박지성", pwd:"1234", email:"park@test.com"};
			// 회원정보를 JSON으로 생성함
		$.ajax({
			type:"post",
			url:"${contextPath}/test/info",
			contentType:"application/json",
			data:JSON.stringify(member), // 회원정보를 문자열로 변환함
			success:function(data, textStatus) {
				
			},
			error:function(data, textStatus) {
				alert("에러가 발생");
			},
			complete:function(data, textStatus) {
				
			}
		});
	});
});
</script>
</head>
<body>
	<input type="button" id="checkJson" value="회원정보 보내기" /><br /><br />
	<div id="output"></div>
</body>
</html>
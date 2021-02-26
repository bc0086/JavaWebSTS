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
		var article = {articleNO:"114", writer:"박지성", title:"안녕하세요", content:"상품 소개글입니다."};
			// 새 글 정보를 JSON으로 생성함
		$.ajax({
			//type:"post",
			//url:"${contextPath}/boards", // 새글을 등록하는 메서드를 호출함
			type:"put",
			url:"${contextPath}/boards/114", // 글 번호 114에 대해 수정을 요청함
			contentType:"application/json",
			data :JSON.stringify(article), // 글정보를 문자열로 변환함
			success:function(data, textStatus) {
				alert(data);
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
	<input type="button" id="checkJson" value = "새글 쓰기" /><br /><br />
	<div id="output"></div>
</body>
</html>
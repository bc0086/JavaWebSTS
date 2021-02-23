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
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	var cnt = 1;
	function fn_addFile() {
		$("#d_file").append(" <br>"+"<input type='file' name='file"+cnt+"' /> ");
		cnt++;
	} // 파일추가를 클릭하면 동적으로 파일 업로드를 추가. 
	  // name속성값으로 'file'+cnt를 설정함으로 값을 다르게 해줌.
</script>
<title>Insert title here</title>
</head>
<body>
	<h1>파일 업로드 하기</h1>
	<form action="${contextPath }/upload" enctype="multipart/form-data" method="post">
		<label>아이디:</label>
		<input type="text" name="id" /><br />
		<label>이름:</label>
		<input type="text" name="name" /><br />
		<input type="button" value="파일 추가" onClick="fn_addFile()" /><br />
		<div id="d_file"> <!-- 자바스크립트를 이용해 div태그 안에 파일 업로드를 추가 -->
		</div>
		<input type="submit" value="업로드" />
	</form>
</body>
</html>
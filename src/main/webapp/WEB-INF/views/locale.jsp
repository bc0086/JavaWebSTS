<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*"
    isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
	<!-- spring message 태그를 사용할 수 있도록 설정함 -->
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
<!-- spring message태그를 이용해 code속성이 프로퍼티 파일의 site.title값을 표시함 -->
<title><spring:message code="site.title" text="Member Info" /></title>
</head>
<body>
	<a href="${contextPath }/test/locale.do?locale=ko">한국어</a>
	<a href="${contextPath }/test/locale.do?locale=en">ENG</a>
	<h1><spring:message code="site.title" text="Member Info" /></h1>
	
	<p><spring:message code="site.name" text="no name" /> :
		<spring:message code="name" text="no name" /></p>
		
	<p><spring:message code="site.job" text="no job" /> :
		<spring:message code="job" text="no job" /></p>
		
	<input type=button value="<spring:message code='btn.send' />" />
	<input type=button value="<spring:message code='btn.cancel' />" />
	<input type=button value="<spring:message code='btn.finish' />" />
</body>
</html>
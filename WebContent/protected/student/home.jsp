<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<%@include file="/protected/common/header.jsp" %>
	<div>
		Name: ${currentUser.name}<br />
		<form method="post" action="${pageContext.request.contextPath}/protected/student/enrollment">
			<label for="clazz">Class:</label>
			<select id="clazz" name="clazz">
				<c:forEach var="clazz" items="${classes}">			
					<option value="${clazz.id}">[${clazz.subject.type.code}] ${clazz.subject.name} | ${clazz.schedule.period} | ${clazz.teacher.name}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Enroll Class">
		</form>
	</div>
	<div>
		<span>${message}</span>
	</div>
	<div>
		<table border="1">
			<c:forEach var="enrolledClazz" items="${currentUser.enrolledClasses}">
				<tr>
					<td>[${enrolledClazz.subject.type.code}] ${enrolledClazz.subject.name}</td>
					<td>${enrolledClazz.schedule.period}</td>
					<td>${enrolledClazz.teacher.name}</td>
				</tr>
			</c:forEach>
		</table>
		<a href="${pageContext.request.contextPath}/protected/student/tuition">Calculate Tuition</a>
	</div>
</body>
</html>
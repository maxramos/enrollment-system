<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Available Classes</title>
</head>
<body>
	<%@include file="/protected/common/header.jsp" %>
	<div>
		<form method="post" action="${pageContext.request.contextPath}/protected/administration">
			<label for="subject">Subject:</label>
			<select id="subject" name="subject">
				<c:forEach var="subject" items="${sessionScope['subjects']}">			
					<option value="${subject.id}">[${subject.type.code}] ${subject.name}</option>
				</c:forEach>
			</select>
			<label for="schedule">Schedule:</label>
			<select id="schedule" name="schedule">
				<c:forEach var="schedule" items="${sessionScope['schedules']}">			
					<option value="${schedule.id}">${schedule.period}</option>
				</c:forEach>
			</select>
			<label for="teacher">Teacher:</label>
			<select id="teacher" name="teacher">
				<c:forEach var="teacher" items="${sessionScope['teachers']}">			
					<option value="${teacher.id}">${teacher.name}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Add Class">
		</form>
	</div>
	<div>
		<span>${sessionScope['message']}</span>
	</div>
	<div>
		<table border="1">
			<c:forEach var="clazz" items="${sessionScope['classes']}">
				<tr>
					<td>[${clazz.subject.type.code}] ${clazz.subject.name}</td>
					<td>${clazz.schedule.period}</td>
					<td>${clazz.teacher.name}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tuition Fee</title>
</head>
<body>
	<%@include file="/protected/common/header.jsp" %>
	<div>
		Tuition Fee
		<table border="1">
			<c:forEach var="courseFee" items="${tuition.courseFeeList}">
				<tr>
					<td>[${courseFee.key.subject.type.code}] ${courseFee.key.subject.name} | ${courseFee.key.schedule.period} | ${courseFee.key.teacher.name}</td>
					<td>Php ${courseFee.value}</td>
				</tr>
			</c:forEach>
			<tr>
				<td>Miscellaneous</td>
				<td>Php ${tuition.miscellaneous}</td>
			</tr>
			<tr>
				<td>Total</td>
				<td>Php ${tuition.total}</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/protected/student/home.jsp">Back</a>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>struktuur</title>
</head>
<body>
	<h1>
		<spring:message code="heading.strukt" />
	</h1>

	<jsp:include page="lang.jsp" />

	<form:form commandname="stukt" method="post" action="strukt">

		<table border=1>
			<tr>
				<td><c:forEach var="each" items="${aylRows}">
						<span style="padding-left:${each.tasand}4px"></span>
						<c:out value="${each.nimetus}"></c:out>
						<br>
					</c:forEach></td>
			</tr>
		</table><br/>
		<input type="submit"
			value='<spring:message code="link.home" />' />
	</form:form>


</body>
</html>
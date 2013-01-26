<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Piirivalve</title>
</head>
<body>
<h1><spring:message code="heading.home"/></h1>

    <jsp:include page="lang.jsp" />

    <p><spring:message code="label.user"/>: ${username}</p>


        <sec:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
        <p><a href="tree"><spring:message code="link.createTestTypes"/></a><p>
    </sec:authorize>
    
    
       <sec:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
        <p><a href="aaRap"><spring:message code="link.aaRap"/></a><p>
    </sec:authorize>
    
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <p><a href="admin/ayl"><spring:message code="link.typeEditor"/></a><p>
    </sec:authorize>
           <sec:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
        <p><a href="strukt"><spring:message code="link.strukt"/></a><p>
    </sec:authorize>

    <a href="logout"><spring:message code="link.logout"/></a>

</body>
</html>
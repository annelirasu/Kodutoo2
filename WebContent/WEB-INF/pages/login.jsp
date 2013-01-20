<%-- 
    Document   : login
    Created on : Jan 20, 2013, 10:01:16 AM
    Author     : reget.kalamees
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logileht</title>
    </head>
    <body>
        <body>

  <jsp:include page="lang.jsp" />

  <c:if test="${not empty error}">
        <spring:message code="${error}"/>
           
           
  </c:if>

  <form action="<c:url value='j_spring_security_check' />" method="POST">
    <input type="text" name="j_username" value="admin"><br>
    <input type="password" name="j_password" value="1" /><br>
    <input type="submit" value="<spring:message code='button.login' />"  />
  </form>

    </body>
</html>

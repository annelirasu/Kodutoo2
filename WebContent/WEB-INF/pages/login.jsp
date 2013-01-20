<%-- 
    Document   : login
    Created on : Jan 20, 2013, 10:01:16 AM
    Author     : reget.kalamees
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
           
            <p>${error}</p>
  </c:if>

  <form action='<c:url value="j_spring_security_check" />' method="POST">
    <input type="text" name="j_username" value="admin"><br>
    <input type="password" name="j_password" value="1" /><br>
    <input type="submit" value='Loginn' />
  </form>

    </body>
</html>

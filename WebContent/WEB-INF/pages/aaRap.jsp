<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administratiivüksuste alluvusraport</title>
    </head>
    <body>
         <jsp:include page="lang.jsp" />
        <h1>Administratiivüksuste alluvusraport!</h1>
        <form name="varskenda" method="post" action="">
            <table>
                <tr>
                    <td>Kuupäev</td>
                    <td>Liik</td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="date" name="kuupaev" value="" /></td>
                    <td>
                        <select name="ayLiik">
                            <c:forEach var="each" items="${admYksused}">
							<c:set var="selected" value="" />
							
							<option value="${each.id}">${each.nimetus}</option>
			   </c:forEach>
                            

                        </select>
                    </td>
                    <td>
                        <input type="submit" value='<spring:message code="button.refresh" />' />  
                    </td>  

                </tr>
            </table>
            
        </form>
    </body>
</html>

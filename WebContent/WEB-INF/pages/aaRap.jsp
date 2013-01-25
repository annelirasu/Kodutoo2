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
                        <select name="aYksus">
                            <c:forEach var="each" items="${admYksused}">
							<c:set var="selected" value="" />
                                                        <c:if test="${each.id == vaYksus.id}">
								<c:set var="selected" value="selected=\"selected\"" />
							</c:if>
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
			 </c:forEach>
                            

                        </select>
                    </td>
                    <td>
                        <input type="submit" value='<spring:message code="button.refresh" />' />  
                    </td> 
                    
                    <!-- esmalt ülemüksuse nimi -->
                    
                        <table border='1'><tr><td colspan='2'><h5>${vaYksus}</h5></td></tr>
                        <!-- seejärel alluvad -->
                        <c:forEach var="each" items="${alluvad}">
					<tr>
                                        <td> ${each.nimetus}</td>
                                        <td><input type='submit' name='nupp${each.id}' value='Vaata' /></td>
                                        </tr>               
							
			 </c:forEach>
                        </table>

                </tr>
            </table>
            
        </form>
    </body>
</html>

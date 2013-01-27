<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!-- et vigade tagi lugeda -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administratiivüksuste alluvusraport</title>
    </head>
    <body>
         <jsp:include page="lang.jsp" />
        <h1><spring:message code="heading.ayr" /></h1>
        <form:form commandname="vaYksus" method="post" action="aaRap">
             <form:errors path="*" cssClass="errorblock" element="div" />
            <table>
                <tr>
                    
                    <td><spring:message code="label.ayr.type" /></td>
                    <td></td>
                </tr>
                <tr>
                    
                    <td>
                        <select name="aYksus">
                            <c:forEach var="each" items="${viiv.liigid}">
							<c:set var="selected" value="" />
                                                        <c:if test="${each.id == viiv.valitudLiik.id}">
								<c:set var="selected" value="selected=\"selected\"" />
							</c:if>
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
			 </c:forEach>
                            

                        </select>
                    </td>
                    <td>
                        <input type="submit" value='<spring:message code="button.ayr.refresh" />' />  
                    </td> 
                    
                    <!-- esmalt ülemüksuse nimi -->
                     <c:forEach var="each" items="${viiv.yksused}">
                        <table border='1'><tr>
                                <td colspan='2'><h5>${each.nimetus}</h5></td></tr>
                        <!-- seejärel alluvad -->
                        <c:forEach var="alluv" items="${each.alluvad}">
					<tr>
                                        <td> ${alluv.nimetus}</td>
                                        <td><input type='submit' name='nupp${alluv.id}' value='<spring:message code="button.ayr.watch" />' /></td>
                                        </tr>               
							
			 
                        </table>
                        </c:forEach>
                    </c:forEach>
                </tr>
            </table>
            
        </form:form>
    </body>
</html>

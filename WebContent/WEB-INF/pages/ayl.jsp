<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> <!-- vajalik keelte vahetamiseks -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!-- et vigade tagi lugeda -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administratiivüksuste liigi redaktor</title>
</head>
<body>
	<jsp:include page="lang.jsp" />
	<h1><spring:message code="heading.ayl" /></h1>
		<form:errors path="*" cssClass="errorblock" element="div" />
	<form:form method="POST" action="ayl" name="ayl" modelAttribute="adw" commandName="adw">
	
	<table>
		<tr>
				<td bgcolor="silver">
				<spring:message code="label.ayl.code" />
				<form:input path="current.kood" value="${adw.current.kood}"/><br /> 
				<form:errors path="current.kood" cssClass="errorblock" element="div" />
				
				<spring:message code="label.ayl.name" /> 
				<form:input path="current.nimetus" value="${adw.current.nimetus}"/><br />
				<form:errors path="current.nimetus" cssClass="errorblock" element="div" />
				
				 <spring:message code="label.ayl.comment" /> <br /> 
				 <form:textarea path="current.kommentaar" value="${adw.current.kommentaar}" cols="30" rows="7"></form:textarea><br />
				 <form:errors path="current.kommentaar" cssClass="errorblock" element="div" />
					
					<spring:message code="label.ayl.superior" /> 
					
					<form:select path="current.yl_id">
										<form:options
											items="${adw.ylemad}"
											itemValue="id" itemLabel="nimetus" />
									</form:select>
			</td>
				<td valign="top" bgcolor="silver"><spring:message
						code="label.ayl.subordinates" /><br /> 
                                    
                                    	<c:if test="${not empty adw.valitudalluvad}">
						<table>
							<c:forEach var="each" items="${adw.valitudalluvad}">
								<tr>
									<td><c:out value="${each.nimetus}"></td>
									</c:out>
									<td><input type="submit" name="eemalda_${each.id}"
										value='<spring:message code="button.ayl.remove" />'>
									<td>
								</tr>
							</c:forEach>

						</table>
					</c:if>
                                    
                                    
                                    <select
					name="ayl_alluv_ID">
						<c:forEach var="each" items="${adw.alluvad}">
							<c:set var="selected" value="" />
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
						</c:forEach>
				</select> <input type="submit" name="lisa_alluv"
					value='<spring:message code="button.ayl.add" />'><br>

				</td>
			<tr bgcolor="#99CCFF">

				<td colspan="2" align="right"><input type="submit"
					name="save_ayl" value='<spring:message code="button.ayl.save" />'>
					<input type="submit" name="cancel_ayl"
					value='<spring:message code="button.ayl.cancel" />'></td>
			</tr>
		
	</table>
	</form:form>
	<c:if test="${not empty adw.errormessage}"> 
               <c:out value="${adw.errormessage}" ></c:out>
                                </c:if>
<a href="../home"><spring:message code="link.home"/></a>
</body>
</html>
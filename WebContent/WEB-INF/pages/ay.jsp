<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> <!-- vajalik keelte vahetamiseks -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <!-- et vigade tagi lugeda -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value='/static/styles.css' />" type="text/css" rel="stylesheet">
<title>Administratiivüksuste redaktor</title>
</head>
<body>
	<jsp:include page="lang.jsp" />
	<h1><spring:message code="heading.ay" /></h1>
		<form:errors path="*" cssClass="errorblock" element="div" />
	<form:form method="POST" action="ay" name="ay" modelAttribute="ayw" commandName="ayw">
	
	<table width="400">
		<tr>
				<td bgcolor="silver">
				<spring:message code="label.ayl.code" />
				<form:input path="current.kood" size="10" value="${ayw.current.kood}" /><br /> 
				<form:errors path="current.kood" cssClass="error" element="div" />
				
				<spring:message code="label.ayl.name" /> 
				<form:input path="current.nimetus" size="20" value="${ayw.current.nimetus}"/><br />
				<form:errors path="current.nimetus" cssClass="error" element="div" />
				
				 <spring:message code="label.ayl.comment" /> <br /> 
				 <form:textarea path="current.kommentaar" value="${ayw.current.kommentaar}" cols="20" rows="5"></form:textarea><br />
				 <form:errors path="current.kommentaar" cssClass="error" element="div" />
					
                   <spring:message code="label.ay.type" /> 
					
					<form:select path="current.liik_id">
										<form:options
											items="${ayw.liigid}"
											itemValue="id" itemLabel="nimetus" />
												</form:select>
							<input type="submit" name="vali_liik" value='<spring:message code="button.ay.type" />'>
                   <br /> 
                      
                                 
					<spring:message code="label.ayl.superior" /> 
					
					<form:select path="current.yl_id">
										<form:options
											items="${ayw.ylemad}"
											itemValue="id" itemLabel="nimetus" />
									</form:select>
			</td>
				<td valign="top" bgcolor="silver"><spring:message
						code="label.ayl.subordinates" /><br /> 
                                    
                                    	<c:if test="${not empty ayw.valitudalluvad}">
						<table>
							<c:forEach var="each" items="${ayw.valitudalluvad}">
								<tr>
									<td><c:out value="${each.nimetus}"></c:out></td>
									
									<td><input type="submit" name="eemalda_${each.id}"
										value='<spring:message code="button.ayl.remove" />'>
									<td>
								</tr>
							</c:forEach>

						</table>
					</c:if>
                                    
                                    
                                    <select
					name="ay_alluv_ID">
						<c:forEach var="each" items="${ayw.alluvad}">
							<c:set var="selected" value="" />
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
						</c:forEach>
				</select> <input type="submit" name="lisa_alluv"
					value='<spring:message code="button.ayl.add" />'><br>

				</td>
			<tr bgcolor="#99CCFF">

				<td colspan="2" align="right"><input type="submit"
					name="save_ay" value='<spring:message code="button.ayl.save" />'>
					<input type="submit" name="cancel_ay"
					value='<spring:message code="button.ayl.cancel" />'></td>
			</tr>
		
	</table>
	</form:form>
	<c:if test="${not empty ayw.errormessage}"> 
               <c:out value="${adw.errormessage}" ></c:out>
                                </c:if>
<a href="../home"><spring:message code="link.home"/></a>
</body>
</html>
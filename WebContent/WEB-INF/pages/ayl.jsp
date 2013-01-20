<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administratiivüksuste liigi redaktor</title>
</head>
<body>
   <jsp:include page="lang.jsp" />
	<h1><spring:message code="heading.ayl" /></h1>


	<table>
		<form method="POST" action="ayl">
			<tr>
				<td bgcolor="silver">
				<spring:message code="label.ayl.code"/> 
				<input name="kood" value="${adw.current.kood}"><br /> 
					<spring:message code="label.ayl.name"/> 
					<input name="nimetus" value="${adw.current.nimetus}"><br /> 
					<spring:message code="label.ayl.comment"/>
					<br /> <textarea name='kommentaar' id='textAreaId'>
					     ${adw.current.kommentaar}
                        </textarea><br /> 
                        
                       <spring:message code="label.ayl.superior"/> <select name="yl_id">
						<c:forEach var="each" items="${adw.ylemad}">
							<c:set var="selected" value="" />
							<c:if test="${each.id == adw.current.yl_id}"><!-- kui tuleb alluvate lisamisel tagasi -->
								<c:set var="selected" value="selected=\"selected\"" />
							</c:if>
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
						</c:forEach>
				</select>


				</td>
				<td valign="top" bgcolor="silver">
				<spring:message code="label.ayl.subordinates"/><br />
				
				 <select name="ayl_alluv_ID">
						<c:forEach var="each" items="${adw.alluvad}">
							<c:set var="selected" value="" />
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
						</c:forEach>
				</select>

				
                <input type="submit" name="lisa_alluv" value='<spring:message code="button.ayl.add" />'><br>
				
                <c:if test="${not empty adw.valitudalluvad}"> 
                <c:forEach var="each" items="${adw.valitudalluvad}"> 
                
	             <c:out value="${each.nimetus}">
	             <input type="submit" name="eemalda_alluv" id="${each.id}" value='<spring:message code="button.ayl.remove" />'></c:out><br>
                </c:forEach>
                </c:if> 
				</td>
			<tr bgcolor="#99CCFF">

				<td colspan="2" align="right">
				<input type="submit" name="save_ayl" value='<spring:message code="button.ayl.save" />'>
				<input type="submit" name="cancel_ayl" value='<spring:message code="button.ayl.cancel" />'></td>
			</tr>
		</form>
	</table>

</body>
</html>
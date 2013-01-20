<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administratiivüksuste liigi redaktor</title>
</head>
<body>
	<h1>Administratiivüksuste liigi redaktor</h1>


	<table>
		<form method="POST" action="ayl">
			<tr>
				<td bgcolor="silver">
				Kood: <input name="kood"
					value="${adw.kood}"><br /> Nimetus: <input
					name="nimetus" value="${adw.nimetus}"><br /> 
					Kommentaar:
					<br /> <textarea name='kommentaar' id='textAreaId'>
					     ${adw.kommentaar}
                        </textarea><br /> 
                        
                        Allub: <select name="yl_id">
						<c:forEach var="each" items="${adw.ylemad}">
							<c:set var="selected" value="" />
							<c:if test="${each.id == adw.yl_id}"><!-- siin kahtlane, mida see õigupoolest tähendab -->
								<c:set var="selected" value="selected=\"selected\"" />
							</c:if>
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
						</c:forEach>
				</select>


				</td>
				<td valign="top" bgcolor="silver">Alluvad<br />
				
				 <select name="ayl_alluv_ID">
						<c:forEach var="each" items="${adw.alluvad}">
							<c:set var="selected" value="" />
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
						</c:forEach>
				</select>

				
                <input type="submit" name="save_alluv" value="Lisa"><br>
				
                <c:if test="${not empty adw.valitudalluvad}"> 
                <c:forEach var="each" items="${adw.valitudalluvad}"> 
                
	             <c:out value="${each.nimetus}"></c:out><br>
                </c:forEach>
                </c:if> 
				</td>
			<tr bgcolor="#99CCFF">

				<td colspan="2" align="right"><input type="submit"
					name="save_ayl" value="Salvesta"><input type="submit"
					name="cancel_ayl" value="Loobu"></td>
			</tr>
		</form>
	</table>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<form method="POST">
			<tr>
				<td bgcolor="silver">Kood: <input name="ayl_kood"
					value="curr_kood"><br /> Nimetus: <input
					name="ayl_nimetus" value="curr_nimetus"><br /> Kommentaar:
					<br /> <textarea name='ay_liik_komm' id='textAreaId'>
                        </textarea><br /> 
                        
                        Allub: <select name="ayl_ylemus_ID">
						<c:forEach var="each" items="${adw.ylemad}">
							<c:set var="selected" value="" />
							<c:if test="${each.id == adminYksLiik.id}">
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
							<c:if test="${each.id == adminYksLiik.id}">
								<c:set var="selected" value="selected=\"selected\"" />
							</c:if>
							<option value="${each.id}" ${selected}>${each.nimetus}</option>
						</c:forEach>
				</select>

				</select> <input type="submit" name="save_alluv" value="Lisa">

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
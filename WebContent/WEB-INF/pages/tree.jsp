<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<p>Oled sisestanud järgnevad testandmed</p>
<table border="1">
    <tr>
        <th>Kood</th>
        <th>Alluvusüksuse liigi nimetus</th>
        <th> Kommentaar</th>
    </tr>
    
    
        
<c:forEach var="each" items="${adw.ylemad}"> 
   <tr> 
       <td><c:out value="${each.kood}"></c:out></td>
       <td><c:out value="${each.nimetus}"></c:out></td> 
       <td><c:out value="${each.kommentaar}"></c:out></td>
   </tr>
</c:forEach>
   
</table>
</body>
</html>
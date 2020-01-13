<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.util.List"%>
	<%@ page import ="model.Test" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<Test> storico = (List<Test>)request.getAttribute("storico"); %>
	<table>
		<tr>
			<th>Data</th>
			<th>numero domande</th>
			<th>punteggio</th>
		</tr>
		<%for(Test u : storico){ %>
			<tr>
				<td>
					<%= u.getData()%>
				</td>
				<td>
					<%= u.getNDomande()%>
				</td>
				<td>
					<%= u.getPunteggio()%>
				</td>
			</tr>
		<%} %>
		
	</table>
	<form method="POST" action="opzionistudente">
					<input type="hidden"  name="tornaIndietro">
					<input type="submit" value="torna alla home">
				</form>

</body>
</html>
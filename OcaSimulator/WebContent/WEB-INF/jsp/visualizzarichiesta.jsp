<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.List"%>
<%@ page import ="model.Utente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- faccio il get attribute con la visualizzarichiesta e la salvo in una variabile
nell'html faccio una tabella e per ogni row io apro il foreach in cui ciclo la visualizza richiesta
per ogni riga metto un bottone con scritto "accetta" o "rifiuta" mi metto un set parameter per la info dello studente e lo mando nel POST di visualizzarichiesta
-->
<%List<Utente> richiestaRegistrazione = (List<Utente>)request.getAttribute("richiesta"); %>
	<table>
		<tr>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Email</th>
			<th>Elimina Richiesta</th>
			<th>Accetta Richiesta</th>
		</tr>
		<%for(Utente u : richiestaRegistrazione){ %>
			<tr>
				<td>
					<%= u.getNome()%>
				</td>
				<td>
					<%= u.getCognome()%>
				</td>
				<td>
					<%= u.getEmail()%>
				</td>
			
				<td>
					<form action="visualizzarichiesta?val=elimina" method="POST">
					  <input type="submit" value="Elimina Richiesta">
					  <input type="hidden" name="value" value="<%=u.getId()%>">
				    </form>
				</td>
				<td>
					<form action="visualizzarichiesta?val=accetta" method="POST">
					  <input type="submit" value="Accetta Richiesta">
					  <input type="hidden" name="value" value="<%=u.getId()%>">
				    </form>
				</td>
			</tr>
		<%} %>
	</table>

</body>
</html>
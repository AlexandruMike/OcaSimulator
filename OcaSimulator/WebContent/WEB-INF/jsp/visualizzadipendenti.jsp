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
<!-- faccio il get attribute con la lista dei dipendenti e la salvo in una variabile
nell'html faccio una tabella e per ogni row io apro il foreach in cui ciclo la lista dipendenti
per ogni riga metto un bottone con scritto "elimina" che mi manda l'informazione del dipendente 
mi metto un set parameter per la info del dipendente e lo mando nel POST di VisualizzaDipendenti
-->
	<%List<Utente> lista = (List<Utente>)request.getAttribute("lista"); %>
	<table>
		<tr>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Email</th>
			<th>Password</th>
			<th>Elimina Dipendente</th>
		</tr>
		<%for(Utente u : lista){ %>
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
					<%= u.getPassword()%>
				</td>
				<td>
					<form action="visualizzadipendenti" method="POST">
					  <input type="submit" value="Elimina">
					  <input type="hidden" name="utente" value="<%=u.getId()%>">
				    </form>
				</td>
			</tr>
		<%} %>
	</table>

</body>
</html>
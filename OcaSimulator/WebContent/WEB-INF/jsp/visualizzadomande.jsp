<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.Utente" %>
    <%@ page import="java.util.List" %>
    <%@ page import="daofactory.DAOFactory" %>
    <%@ page import="model.Domande" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<Domande> listaDomande=(List<Domande>) request.getAttribute("listaDomande");%>

<table class="table table-bordered table-striped text-center">
<tr>

<th>testo</th>
<th>nome</th>
<th>cognome</th>
</tr>
  <%for(int i=0; i < listaDomande.size();i++){%>
            <tr>
                <td><%=listaDomande.get(i).getTesto() %></td>
                
                <td><%=listaDomande.get(i).getUtente().getNome()%></td>
                <td><%=listaDomande.get(i).getUtente().getCognome()%></td>
             	<td>
             	<form method="POST" action="VisualizzaDomande">
					<input type="hidden" value=<%=listaDomande.get(i).getId()%> name="idSet">
					<input type="submit" value="elimina">
				</form>
				</td>	
            </tr>
            <%} %>

</body>
</html>
<%@page import="model.Opzioni"%>
<%@page import="model.Domande"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<%Domande d=(Domande)session.getAttribute("domanda"); 

	%>
	
	<p><%=d.getTesto() %></p>
	<%if(d.getOpzionis()!=null&&d.getOpzionis().size()>0){
		for(Opzioni o:d.getOpzionis()){
		%>
			<p><%=o.getTesto() %>    <%=o.getCorretto()==1?"Corretta":"Errata" %></p>
		<%
		}
	}
		%>
	<form action="inserimentorisposte" method="POST">
		Inserisci le risposte alla domanda:<br>
		<textarea rows="4" cols="30" name="risposta">
 	</textarea>
		<input type="radio" name="corretta" value="1" checked>
		Corretta
		 <input type="radio" name="corretta" value="0">
		Sbagliata<br>
		<input type="checkbox" name="ulterioreRisposta" value="si" checked>
		aggiungi ulteriore risposta<br>
		<input type="submit" value="aggiungi">
		<!-- -------//-------------------CAMBIATO--------------------------- -->
	</form>
</body>
</html>
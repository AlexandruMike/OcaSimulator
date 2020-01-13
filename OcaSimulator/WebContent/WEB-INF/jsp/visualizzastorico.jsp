<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.util.List"%>
	<%@ page import ="model.Test" %>
	<%@ page import ="model.Utente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  <style type="text/css">
  <%@include file="/../resource/css/bootstrap.min.css" %>
  <%@include file="/resource/css/style.css" %>
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class= "col-12 mt-4">
				<h1>Ecco i tuoi risultati</h1>
			</div>
		</div>	
		<div class="row">
			<div class="col-12 mt-3">
<%List<Test> storico = (List<Test>)request.getAttribute("storico"); %>
	<table class="bg-light text-dark">
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
	</div>
		<div class="row">
			<div class="col-12 mt-3">
			<form method="POST" action="opzionistudente">
				<%Utente u=((Utente)(session.getAttribute("utente")));
	if(u.getRuolo().equals("stud")) {%>
	<form method="POST" action="opzionistudente">
	<%}else{ %>
	<form method="POST" action="dipendente">
	
	<%
	}
		%><input type="submit" value="torna alla home" class="ml-3"></form>
			</div>	
		</div>
	</div>
</body>
</html>
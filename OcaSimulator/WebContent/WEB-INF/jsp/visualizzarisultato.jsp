<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Risultato</title>
  <style type="text/css">
  <%@include file="/../resource/css/bootstrap.min.css" %>
  <%@include file="/resource/css/style.css" %>
	</style>
</head>
<body>
	<div class="container">
	<div class="row">
		<div class="col-12  mt-3">
			<h1>Risultati</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12 mt-3">
<%int domande=(Integer)request.getAttribute("domande");
int punteggio=(Integer)request.getAttribute("punteggio");
%>

<p>hai risposto a <%=domande %> domande</p>
<p>il tuo punteggio è del <%=punteggio %> %</p>
<p><%=punteggio>=65?"":"non " %>hai superato il test</p>
		</div>
	</div>
	<div class="row">
		<div class="col-12 pl-2 mt-3 col-md-6 ml-2 mt-3">
<form method="POST" action="opzionistudente">
<input type="submit" value="torna alla home">
</form>
		</div>
		</div>
	<div class="row">	
	<div class="col-12 mt-3 col-md-6 mt3">	
<form method="GET" action="visualizzastorico">
<input type="submit" value="visualizza storico">
</form>
	</div>
	</div>
<!-- -------//-------------------CAMBIATO--------------------------- -->
	</div>
</body>
</html>
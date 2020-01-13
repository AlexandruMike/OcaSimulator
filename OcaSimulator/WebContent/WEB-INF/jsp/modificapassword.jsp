<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "model.Utente" %>
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
	<div class="col-12 mt-3">
<h1>Modifica Password</h1>

<form action="modificapassword" method="POST">
<h3>Vecchia password</h3>
<input type="text" name="vecchia"> 
<br>
<h3>Nuova Password</h3>
<input type="text" name="nuova">
<br><br>
<input type="submit" value="invia">

</form>
<%if(request.getParameter("errore")!=null){%>
<p><%request.getParameter("errore"); %></p>
<%} %>
	</div>
	</div>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Area Amministratore</h1>
<form action="admin?name=crea" method="POST">

<input type="submit" value="Crea Dipendente" name="crea">
<br>
<br>
</form>
<form action="admin?name=dipendenti" method="POST">
<input type="submit" value="Visualizza Dipendenti" name="dipendenti">
<br>
<br>
</form>
<form action="admin?name=domande" method="POST">
<input type="submit" value="Visualizza Domande" name="domande">


</form>

</body>
</html>
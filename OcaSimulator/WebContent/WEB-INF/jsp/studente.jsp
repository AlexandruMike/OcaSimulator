<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<div class="col-12 text-center mt-3">
			<h1>Area Studente</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-12 text-center pt-2 col-md-6 mt-4">
			<form action="opzionistudente?name=storico" method="POST">

				<input type="submit" value="Visualizza Storico" name="storico">
			</form>
		</div>	
		<div class="col-12 text-center pt-2 col-md-6 mt-4">
			<form action="opzionistudente?name=modifica" method="POST">
				<input type="submit" value="Modifica Password" name="modifica">
			</form>
 		</div>
 	</div>	
 	<div class="row">
 	    <div class="col-12 text-center  mt-5">
 	    	<h2>Seleziona il numero delle domande:</h2>
 	    </div>
 	</div>
 	<div class="row">
 		<div class="col-12 text-center mt-4"> 	
				<form action="svolgetest" method="GET">
					<select  name="numero">	
						<%for(int i=1; i<=100; i++){%>
						<option  value=<%=i %>><%=i%></option>
						<%} %>
					</select>
					<input type="submit" value="Esegui Test" name="test" id="etest">
				</form>
		</div>
	</div>	  
</div>
</body>
</html>
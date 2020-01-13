<%@page import="model.Opzioni"%>
<%@page import="model.Domande"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.util.List"%>
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


<!-- 
Prendo la lista di domande, dopodiché preparo il form su cui ciclo ogni domanda e presento tutte le opzioni flaggabili
 -->
 	<div class="container">
 		<div class="row">
 			<div class="col-12 mt-3">
 <%
 	List<Domande> listaDomande = (List<Domande>)session.getAttribute("listaDomande");
	int  risposteDate=(int)session.getAttribute("risposteDate");
	if(listaDomande!=null&&listaDomande.size()>risposteDate){
		char i='a';
		Domande d=listaDomande.get(risposteDate);
		%>
		<p><%=d.getTesto() %></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12 mt-4">
				<form action="svolgetest" method="POST">
			<%for(Opzioni o:d.getOpzionis()){%>
				<input type="checkbox" name=<%="opzione"+o.getId()%> value="1"><%=i++ %> - <%=o.getTesto() %><br> 
			<% }%>
			<input type="submit" value="Conferma" class="mt-3">
		</form>
		<%

	}
 %>
 			</div>
 		</div>
 </div>
 
</body>
</html>
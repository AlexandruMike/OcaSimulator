<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style type="text/css">
  <%@include file="/../resource/css/bootstrap.min.css" %>
  <%@include file="/resource/css/style.css" %>
	</style>
</head>
<body>
    <div class="container">
        <div class= "row">
            <div class="col-12 text-center mt-4">
                <h1>Benvenuti al Test OCA simulator</h1>
            </div>
         </div>   
        <div class="row">
            <div class="col-12 mt-4 col-md-6">
                <form id="accedi" method="POST" action="login">
                    <div class="form-group">
                      <label for="exampleInputEmail1">Email</label>
                      <input type="email" class="form-control"  name="email" aria-describedby="emailHelp">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Password</label>
                      <input type="password" class="form-control"  name="password">
                    </div>
                    <button type="submit" class="btn btn-primary">Accedi</button>
                  </form>
             <%if(request.getAttribute("queue")!=null) { %>
			<p><%=request.getAttribute("queue")%></p>
			<%} %>
            </div>
            <div class="col-12 mt-4 col-md-6">
                <form method="POST" action="registrazione">
                    <div class="form-group">
                      <label for="nome">Nome</label>
                      <input type="text" class="form-control" name="nome">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Cognome</label>
                      <input type="text" class="form-control" name = "cognome">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email</label>
                        <input type="email" class="form-control" id="inputEmail2" aria-describedby="emailHelp" name="email">
                      </div>
                      <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" id="inputPassword2" name="password">
                      </div>
                    <button type="submit" class="btn btn-primary">Registrati</button>
                  </form>
                  
             <%if(request.getAttribute("invalid")!=null) { %>
			<p><%=request.getAttribute("invalid")%></p>
			<%} %>
			
			<%if(request.getAttribute("success")!=null) { %>
			<p><%=request.getAttribute("success")%></p>
			<%} %>
			
			<%if(request.getAttribute("present")!=null) { %>
			<p><%=request.getAttribute("present")%></p>
			<%} %>
            </div>
        </div>
    </div>
</body>
</html>
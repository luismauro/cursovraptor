<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet" type="text/cs" href="../bootstrap/css/bootstrap.css">
<title>Novo produto</title>
</head>
<body>
    
    <div class="container">
    
    <h1> Adicionar Produto </h1>
    <form action="<c:url value = '/produto/adiciona'/>" method = "post">
    Nome:
    <input class="form-control" type="text" name = "produto.nome"/>
    
    Valor:
    <input class="form-control"  type="text" name = "produto.valor"/>
    
    Quantidade:
    <input class="form-control"  type="text" name = "produto.quantidade" />
    <br>
    <input type="submit" class="btn btn-primary"  value="Adicionar"/>
     
    
    </form>
    </div>
    <!-- Mensagem de erro, configurado em messages.properties -->
    <c:forEach var="error" items="${errors}">
    ${error.category} - ${error.message}<br/>
</c:forEach>
    
</body>
</html>
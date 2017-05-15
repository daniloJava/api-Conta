<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="../estilo.jsp" />
</head>
<body>

 <nav class="navbar navbar-inverse">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">HubFintech</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li ><a href="${s:mvcUrl('CC#form').build() }">Nova Conta</a></li>
              <li ><a href="${s:mvcUrl('CC#listar').build() }">Lista Contas</a></li>
              <li ><a href="${s:mvcUrl('PC#form').build() }">Nova Pessoa</a></li>
              <li ><a href="${s:mvcUrl('PC#listar').build() }">Lista Pessoas</a></li>
              <li ><a href="${s:mvcUrl('TC#form').build() }">Nova Transferenia</a></li>
              <li ><a href="${s:mvcUrl('TC#listar').build() }">Lista Transferenias</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </nav>


</body>
</html>
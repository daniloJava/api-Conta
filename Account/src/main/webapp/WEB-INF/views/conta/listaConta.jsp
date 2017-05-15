<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/resources/css/" var="cssPath"></c:url>
<link rel="stylesheet" href="${cssPath }bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath }bootstrap-theme.min.css" />
<script src="resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<c:import url="../estilo.jsp" />

</head>
<body>
<c:import url="../menu.jsp" />
<div class="container col-sm-8	">
<h1>Listagem das contas</h1>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Tipo da Conta</th>
				<th>Data Criação</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contas}" var="conta">
				<tr>
					<td>${conta.nome}</td>
					<td>${conta.tipoConta}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${conta.dataCriacao.time}" /></td>
					<td>${conta.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>
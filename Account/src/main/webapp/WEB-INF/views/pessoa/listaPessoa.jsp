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
<title>Lista Pessoa Fisica</title>
<c:import url="../estilo.jsp" />

</head>
<body>
	<c:import url="../menu.jsp" />
	<div class="container col-sm-8	">
		<h1>Lista Pessoa Fisica</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Nome</th>
					<th>CPF</th>
					<th>Data de Nascimento</th>
					<th>Conta</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pessoasFisica}" var="pf">
					<tr>
						<td>${pf.nome}</td>
						<td>${pf.cpf}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${pf.dataNascimento.time}" /></td>
						<td>${pf.conta.nome}</td>
					</tr>
				</c:forEach>
			</tbody>
		
		</table>
	</div>
	<br>
	<div class="container col-sm-8	">
		<h1>Lista Pessoa Juridica</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Nome Fantasia</th>
					<th>Razão Social</th>
					<th>CNPJ</th>
					<th>Conta</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${pessoasJuridica}" var="pj">
					<tr>
						<td>${pj.nomeFantasia}</td>
						<td>${pj.razaoSocial}</td>
						<td>${pj.cnpj}</td>
						<td>${pj.conta.nome}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
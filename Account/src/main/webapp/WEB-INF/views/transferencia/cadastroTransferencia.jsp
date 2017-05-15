<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>          
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="../estilo.jsp" />
<title>Nova transferencia</title>
</head>
<body>
	<c:import url="../menu.jsp" />
	<div class="container col-sm-6">
		<div class="well well-lg">
		<h1>Nova transferencia </h1>
		
			<form:form action="${s:mvcUrl('TC#adicionaTransferencia').build() }" method="POST"
					commandName="transferencia" cssClass="form-horizontal">
				<div class="form-group">
					<label class="control-label">Selecione Conta para Debito</label>
					<select name="contaDebito" class="form-control">
					<c:forEach items="${todasContas }" var="conta">
						<option value="${conta.id}">${conta.nome}</option>	
					</c:forEach>
					</select>
				</div>	
				<div class="form-group">
					<label class="control-label">Selecione Conta para Credito</label>
					<select name="contaCredito" class="form-control">
					<c:forEach items="${todasContas }" var="conta">
						<option value="${conta.id}">${conta.nome}</option>	
					</c:forEach>
					</select>
				</div>		
				<div class="form-group">
					<label class="control-label">Valor:</label>
					<form:input path="valor"  cssClass="form-control"/>
				</div>
				<input type="submit" class="btn btn-primary" value="Cadastrar">
			</form:form>	
		</div>
	</div>
</body>
</html>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js" ></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="../estilo.jsp" />
<title>Cadastro de Pessoas</title>
<style type="text/css">
.well{
	margin-top: 0% !important;
}
.well-lg{
	margin-top: 0% !important;
}
h2{
margin-top: 0% !important;
}
</style>

<script type="text/javascript">
function habilitaFisica(){
	$("#pessoaFisica").css("display", "block");
	$("#pessoaJuridica").css("display", "none");
}

function habilitaJuridica(){
	$("#pessoaFisica").css("display", "none");
	$("#pessoaJuridica").css("display", "block");
}


$(document).ready(function(){
	
	var fisica = $("#pessoaFisica").find("span").length;
	var juridica = $("#pessoaJuridica").find("span").length;
	if(fisica != 0)
		$("#pessoaFisica").css("display","block");
	if(juridica != 0)
		$("#pessoaJuridica").css("display","block");
});

</script>

</head>
<body>
	<c:import url="../menu.jsp" />
	
	<div class="container col-sm-6">
		<div class="well well-lg">
			<h2>Cadastro Nova Pessoa</h2>
			
			<br>
			<div class="radio">
				<label>Tipo de Pessoa:</label>
			 	<label onclick="habilitaFisica()">
				    <button class="btn btn-sm btn-info">
				   		Pessoa Fisica 
				   	</button>
			  	</label>
			  	<label onclick="habilitaJuridica()">
				    <button class="btn btn-sm btn-info">
				    	Pessoa Juridica
				    </button> 
			  </label>
			</div>
			<div id="pessoaFisica" style="display: none;">
				<form:form action="${s:mvcUrl('PC#adicionarPf').build() }" method="POST"
						commandName="pessoaFisica" modelAttribute="pessoaFisica" cssClass="">
						<h3>Pessoa Fisica</h3>
						<div class="form-group ">
							<label class="control-label">Nome:</label>
							<form:input path="nome" cssClass="form-control "/>
							<form:errors  path="nome" cssClass="alert-danger" >
								<c:set var="inputCls" value="block" scope="page" />
							</form:errors>
							
						</div>	
						<div class="form-group">
							<label class=" control-label">CPF:</label>
							<form:input path="cpf" cssClass="form-control "/>
							<form:errors path="cpf" cssClass="alert-danger"/>
						</div>	
						<div class="form-group">
							<label class=" control-label">Data de Nascimento:</label>
							<form:input  path="dataNascimento" cssClass="form-control date-picker"/>
							<form:errors path="dataNascimento" cssClass="alert-danger"/>
						</div>
						<div class="form-group">
							<label class=" control-label">Selecione a Conta</label>
							<select name="conta" class="form-control">
							<c:forEach items="${todasContas }" var="conta">
								<option value="${conta.id}">${conta.nome}</option>	
							</c:forEach>
							</select>
						</div>	
					<input type="submit" class="btn btn-primary" value="Cadastrar" name="saveFisica">
				</form:form>	
			</div>
			<div id="pessoaJuridica" style="display: none;" >
				<form:form action="${s:mvcUrl('PC#adicionarPj').build() }" method="POST"
						commandName="pessoaJuridica" modelAttribute="pessoaJuridica">
						<h3>Pessoa Juridica</h3>${inputCls}
						<div class="form-group">
							<label class="control-label">Razão Social:</label>
							<form:input path="razaoSocial" cssClass="form-control input-small"/>
							<form:errors path="razaoSocial" cssClass="alert-danger"/>
						</div>	
						<div class="form-group">
							<label class="control-label">CNPJ:</label>
							<form:input path="cnpj" cssClass="form-control "/>
							<form:errors path="cnpj" cssClass="alert-danger"/>
						</div>	
						<div class="form-group">
							<label class="control-label">Nome Fantasia:</label>
							<form:input path="nomeFantasia" cssClass="form-control"/>
							<form:errors path="nomeFantasia" cssClass="alert-danger"/>
						</div>
						<div class="form-group">
							<label class="control-label">Selecione a Conta</label>
							<select name="conta" class="form-control">
							<c:forEach items="${todasContas }" var="conta">
								<option value="${conta.id}">${conta.nome}</option>	
							</c:forEach>
							</select>
							<form:errors path="conta" cssClass="alert-danger" />
						</div>	
					<input type="submit" class="btn btn-primary" value="Cadastrar" name="saveJuridica">
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
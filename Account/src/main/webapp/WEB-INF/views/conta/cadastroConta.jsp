<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>          
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:import url="../estilo.jsp" />

</head>
<body>
	<c:import url="../menu.jsp" />

	<div class="container col-sm-6">
		<div class="well well-lg">
		<h1>Cadastro Nova conta</h1>
		<br>
			<form:form action="${s:mvcUrl('CC#gravar').build() }" method="POST"
				commandName="conta" cssClass="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">Nome:</label>
					<form:input path="nome" cssClass="form-control "/>
					<form:errors path="nome"/>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">Tipo de Conta:</label>
				  <div class="radio">
					  <label>
					   <form:radiobutton path="tipoConta" value="MATRIZ" />
					    MATRIZ
					  </label>
					  <label>
					    <form:radiobutton  checked="checked" path="tipoConta" value="FILIAL" />
					    FILIAL
					  </label>
					  <form:errors path="tipoConta"/>
				    </div>
				</div>
				
				<div  class="form-group ">
					<label class="col-sm-2 control-label">Status:</label>
					<div class="radio">
						  <label>
						    <form:radiobutton path="status" checked="checked" value="ATIVA"/>
						    ATIVA
						  </label>
						  <label>
						    <form:radiobutton path="status" value="BLOQUEADA"/>
						    BLOQUEADA
						  </label>
						  <label>
						    <form:radiobutton path="status" value="CANCELADA"/>
						    CANCELADA
						  </label>
				   		 <form:errors path="status"/>
				    </div>
				</div>
				<div  class="form-group">
						<form:label path="ReportaParaConta" cssClass="control-label">Selecione a Conta Dependente</form:label><br>
						<div class="col-xs-6">
						<form:select multiple="false" path="ReportaParaConta" cssClass="form-control "> 
							<form:options items="${todasContas }" itemValue="id" itemLabel="nome" />
						</form:select>
					</div>
				</div>
				<div  class="form-group">
					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</div>
				
						
			</form:form>
		</div>
	</div>
</body>
</html>
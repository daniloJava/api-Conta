<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<c:import url="../estilo.jsp" />
<title>Lista de Transferencias</title>

</head>
<body>
	<c:import url="../menu.jsp" />
	<div class="container col-sm-8	">
		<h1>Lista transferencias</h1>
		<% 
			if(request.getParameter("codigoInvalido") != null){
		%>		 
			<div class="alert alert-danger" role="alert"><%= request.getParameter("codigoInvalido") %></div>
		<%
			}
		%>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Data / Hora</th>
					<th>Nome Conta Debito</th>
					<th>Nome Conta Credito</th>
					<th>valor</th>
					<th>Tipo Da conta Credito</th>
					<th>Codigo Aporte</th>
					<th>Status</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${transferencias}" var="transf">
					<tr>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${transf.data.time}" /></td>
						<td>${transf.contaDebito.nome}</td>
						<td>${transf.contaCredito.nome}</td>
						<td>${transf.valor}</td>
						<td>${transf.contaCredito.tipoConta}</td>
						<td>${transf.aporte}</td>
						<td>${transf.status}</td>
						<td>
							<c:if test="${transf.contaCredito.tipoConta eq 'MATRIZ' and transf.status eq 'EFETUADO'}">
								<button type="button" class="btn btn-primary" data-cod="${transf.id}" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">ESTORNAR</button>
								<c:set var='idMatriz' value='${transf.id}'/>
							</c:if>
							<c:if test="${transf.contaCredito.tipoConta eq 'FILIAL'}">
								<a href="${s:mvcUrl('TC#estorno').arg(0, transf.id).build()}">
									${transf.status eq 'EFETUADO' ? '<button type="button" class="btn btn-primary"> ESTORNAR </button>' : ''} 
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">Insira o Código Aporte para Transferencia</h4>
      </div>
      <div class="modal-body">
        <form:form action="${s:mvcUrl('TC#estorno').arg(0, idMatriz).build() }" method="POST"
					commandName="transferencia" >
          <div class="form-group">
            <label for="recipient-name" class="control-label">Codigo Aport:</label>
            <input type="text" class="form-control" id="recipient-name" name="aport">
          </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <input type="submit" class="btn btn-primary" value="Estornar"/>
	      </div>
        </form:form>
    	 </div>
    </div>
  </div>
</div>
	
</body>
</html>
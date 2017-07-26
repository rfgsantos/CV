<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
DBconnection db = new DBconnection();
ArrayList<Object> ingredientes = db.listaIngredientes();

db.disconnectSQL();


%>

<h3>
	<i class="fa fa-angle-right"></i> Registe um Pedido
</h3>
<div class="row">

	<div class="col-md-3">

		<div class="content-panel">
			<h4>
				<i class="fa fa-angle-right"></i>Escolha o Ingrediente
			</h4>

			<select class="form-control" name="restaurantes"
				onchange="fornecedoresIngrediente(this.value)">
				
				<%
				out.println("<option value='empty'> </option>");
				for(int i=0; i<ingredientes.size();i++){
					out.println("<option value='"+ingredientes.get(i)+"'>"+ingredientes.get(i)+"</option>");
				}
				%>
			</select>

		</div>

	</div>
	
	<div class="col-md-3">

		<div class="content-panel" id="fornecedorIng" style="display:none">
		

		</div>

	</div>
	
	
	<div class="col-md-4">

		<div class="content-panel" id="quantidade" style="display:none">
		

		</div>

	</div>
	<!-- /col-md-12 -->
</div>
<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	String ingrediente = request.getParameter("ingrediente");
	DBconnection db = new DBconnection();
	ArrayList<Object> fornecedoresIng = db.listaFornecedoresIng(ingrediente);

	db.disconnectSQL();
%>

<h4>
	<i class="fa fa-angle-right"></i>Escolha o Fornecedor
</h4>
<%
out.println("<select class='form-control' name='restaurantes' onchange='precoFornecedor(this.value,\""+ingrediente+"\")'>");

	
		out.println("<option value='empty'> </option>");
		for (int i = 0; i < fornecedoresIng.size(); i++) {
			out.println("<option value='" + fornecedoresIng.get(i) + "'>" + fornecedoresIng.get(i) + "</option>");
		}
	%>
</select>
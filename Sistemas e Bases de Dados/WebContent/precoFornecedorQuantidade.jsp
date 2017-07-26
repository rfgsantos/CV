<%@page import="org.apache.jasper.tagplugins.jstl.core.Redirect"%>
<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	String ingrediente = request.getParameter("ingrediente");
	String fornecedor = request.getParameter("fornecedor");
	String restaurante = (String)session.getAttribute("restaurante");
	DBconnection db = new DBconnection();
	int preco = db.precoIngFornecedor(ingrediente, fornecedor);

	db.disconnectSQL();
%>
<table class="table">
	<thead>
		<tr>
			<th>Preco</th>
			<th>Quantidade</th>
			<th> </th>
			
		</tr>
	</thead>

	<tbody>
	<%
	
	out.println("<tr>");
	out.println("<td>");
	out.println(preco);
	out.println("</td>");
	out.println("<td>");
	out.println("<input class='form-control' type='text' name='quantidadePedida'/>");
	out.println("</td>");
	out.println("<td>");
	out.println("<button class='btn btn-primary' onclick='encomendar(\""+ingrediente+"\",\""+fornecedor+"\",\""+restaurante+"\")' > Encomendar </button>");
	out.println("</td>");
	out.println("</tr>");
	%>
		
<!-- 	out.println -->
	
	
	
	</tbody>
</table>
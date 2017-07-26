<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	//falta tartar exceçãol ----------- SE NÃO ENCONTRA ID DO PEDIDO INDICA QUE NÃO FOI EFEETUADO UM PEDIDO.
	int pedidoID = Integer.parseInt((String) session.getAttribute("pedidoID"));

	ArrayList<Object> elementosPedidos = db.consultaPedido(pedidoID);

	db.disconnectSQL();
%>


<h3>
	<i class="fa fa-angle-right"></i>Consulta Pedido
</h3>
<div class="row">
	<div class="col-md-6">
		<div class="content-panel">
			<h4>
				<i class="fa fa-angle-right"></i>Elementos Pedidos
			</h4>
			<hr>
			<table class="table">
				<thead>
					<tr>
						<th>Produtos</th>
						<th>Estado</th>
					</tr>
				</thead>

				<tbody>
					<%
						for (int i = 0; i < elementosPedidos.size(); i += 2) {
							out.print("<tr>");
							out.print("<td>");
							out.print(elementosPedidos.get(i));
							out.print("</td>");
							out.print("<td>");
							out.print(elementosPedidos.get(i + 1));
							out.print("</td>");
							out.print("</tr>");
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<!-- /col-md-12 -->
</div>
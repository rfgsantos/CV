<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
DBconnection db = new DBconnection();
String mesaID = request.getParameter("mesa");
ArrayList<Object> listaPedidos = db.listPedidosDaMesa(mesaID);
db.disconnectSQL();
%>




<h4>
	<i class="fa fa-angle-right"></i>Pedidos Totais
</h4>

<table class="table">
	<thead>
		<tr>
			<th>ID Pedidos</th>
		</tr>
	</thead>

	<tbody>
		<%
			for (int i = 0; i < listaPedidos.size(); i++) {
				out.print("<tr>");
				out.print("<td>");
				out.println("<button class='btn btn-primary' onclick='showItemsPedido(\"" + listaPedidos.get(i)
						+ "\")'>Pedido " + listaPedidos.get(i) + "</button>");
				out.print("</td>");
				out.print("</tr>");
			}
		%>
	</tbody>
</table>
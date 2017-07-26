<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	String restaurante = (String) session.getAttribute("restaurante");
	ArrayList<Object> IDmesas = db.listaMesas(restaurante);
	
	

	db.disconnectSQL();
%>


<h3>
	<i class="fa fa-angle-right"></i> Altera Pedidos
</h3>
<div class="row">

	<div class="col-md-2">

		<div class="content-panel">
			<h4>
				<i class="fa fa-angle-right"></i>Mesas Totais
			</h4>

			<table class="table">
				<thead>
					<tr>
						<th>ID Mesa</th>
					</tr>
				</thead>

				<tbody>
					<%
						for (int i = 0; i < IDmesas.size(); i++) {
							out.print("<tr>");
							out.print("<td>");
							out.println("<button class='btn btn-primary' onclick='showPedidosMesa(\"" + IDmesas.get(i)
									+ "\")'>MESA "+IDmesas.get(i)+"</button>");
							out.print("</td>");
							out.print("</tr>");
						}
					%>
				</tbody>
			</table>

		</div>

	</div>


	<div class="col-md-2">

		<div class="content-panel" id="pedidos" style='display: none'></div>
	</div>

	<div class="col-md-6">

		<div class="content-panel" id="items" style='display: none'></div>
	</div>
</div>
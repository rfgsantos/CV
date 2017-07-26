<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	String restaurante = request.getParameter("nomeRestaurante");
	ArrayList<Object> elementos = db.currentMenu(restaurante);
	ArrayList<Integer> elementosID = new ArrayList<Integer>();

	for (int i = 0; i < elementos.size(); i += 3) {
		elementosID.add(db.getItemID((String) elementos.get(i)));
	}

	db.disconnectSQL();
%>


<h3>
	<i class="fa fa-angle-right"></i> Ementa Atual
</h3>

<div class="row">
	<div class="col-md-4">

		<div class="content-panel">

			<table class="table">
				<thead>
					<tr>
						<th>Nome Prato</th>
						<th>Precos</th>
					</tr>
				</thead>

				<tbody>
					<%
						if (!(elementos.size() == 0)) {
							for (int i = 0; i < elementos.size(); i += 3) {
								out.print("<tr>");
								out.print("<td>");
								out.println(elementos.get(i));
								out.print("</td>");
								out.print("<td>");
								out.println(elementos.get(i + 1) + "$");
								out.print("</td>");
								out.print("<td>");
								out.println("<button class='btn btn-primary' onclick='showReceita(\"" + elementosID.get(i / 3)
										+ "\")'>receita</button>");
								out.print("</td>");
								out.print("</tr>");
							}
						} else {
							out.print("<tr>");
							out.print("<td>");
							out.println("nada apresentar");
							out.print("</td>");
							out.print("<td>");
							out.println("nada apresentar");
							out.print("</td>");
							out.print("<td>");
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-4">

		<div class="content-panel" id="ingredientes" style='display:none'></div>
	</div>

	<div class="col-md-4">

		<div class="content-panel" id="preparos" style='display:none'></div>
	</div>


</div>

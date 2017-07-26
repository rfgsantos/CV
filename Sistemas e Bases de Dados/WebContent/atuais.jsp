<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	String restaurante = (String) session.getAttribute("restaurante");
	ArrayList<Object> ingredientesAtuais = db.listaStocksRestaurante(restaurante);
	db.disconnectSQL();
%>


<h3>
	<i class="fa fa-angle-right"></i> Stock Atual
</h3>


<div class="row">

	<div class="col-md-3">

		<div class="content-panel">
			<table class="table">
				<thead>
					<tr>
						<th>Ingrediente</th>
						<th>Quantidade</th>
					</tr>
				</thead>

				<tbody>
					<%
						if (!(ingredientesAtuais.size() == 0)) {
							for (int i = 0; i < ingredientesAtuais.size(); i += 2) {
								out.print("<tr>");
								out.print("<td>");
								out.println(ingredientesAtuais.get(i));
								out.print("</td>");
								out.print("<td>");
								out.println(ingredientesAtuais.get(i + 1));
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
	<!-- /col-md-12 -->
</div>

<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="java.util.ArrayList"%>

<%
	String date = request.getParameter("date");
	String time = request.getParameter("time");
	String restaurante = request.getParameter("rest");

	DBconnection db = new DBconnection();
	ArrayList<Object> ementa = db.currentMenuDataHora(date, time, restaurante);
	ArrayList<Integer> elementosID = new ArrayList<Integer>();

	for (int i = 0; i < ementa.size(); i += 2) {
		elementosID.add(db.getItemID((String) ementa.get(i)));
	}
	db.disconnectSQL();
%>
<h3>
	<i class="fa fa-angle-right"></i> Consulta Ementa
</h3>
<div class="row">
	<div class="col-md-4">
		<div class="content-panel">
			<h4>
				<i class="fa fa-angle-right"></i>Ementa Atual
			</h4>
			<hr>
			<table class="table">
				<thead>
					<tr>
						<th>Nome Prato</th>
						<th>Precos</th>
						<!-- 		                            <th>Sobremesas</th> -->
					</tr>
				</thead>

				<tbody>
					<%
						if (!(ementa.size() == 0)) {
							for (int i = 0; i < ementa.size(); i+=2) {
								out.print("<tr>");
								out.print("<td>");
								out.println(ementa.get(i));
								out.print("</td>");
								out.print("<td>");
								out.println(ementa.get(i+1) + "$");
								out.print("</td>");
								out.print("<td>");
								out.println("<button class='btn btn-primary' onclick='showReceita(\"" + elementosID.get(i / 2)
										+ "\")'>receita</button>");
								out.print("</td>");
								out.print("</tr>");
							}
						}else{
							out.print("<tr>");
							out.print("<td>");
							out.println("nada apresentar");
							out.print("</td>");
							out.print("<td>");
							out.println("nada apresentar");
							out.print("</td>");
							out.print("<td>");
							out.println("nada apresentar");
							out.print("</td>");
							out.print("</tr>");
						}
					%>
				</tbody>
			</table>
		</div>
		<! --/content-panel -->
	</div>
	
		<div class="col-md-4">

		<div class="content-panel" id="ingredientes" style='display:none'></div>
	</div>

	<div class="col-md-4">

		<div class="content-panel" id="preparos" style='display:none'></div>
	</div>
	<!-- /col-md-12 -->
</div>
<!-- row -->
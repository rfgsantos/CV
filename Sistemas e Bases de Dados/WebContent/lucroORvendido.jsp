<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	String escolha = request.getParameter("escolha");
	ArrayList<Object> items = new ArrayList<Object>();
	if(escolha.compareTo("lucro")==0){
		items = db.itemsMaisLucro();
	}
	else{
		items = db.itemsMaisVendidos();
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
						<th>Total Lucro</th>
					</tr>
				</thead>

				<tbody>
					<%
						if (!(items.size() == 0)) {
							for (int i = 0; i < items.size(); i += 2) {
								out.print("<tr>");
								out.print("<td>");
								out.println(items.get(i));
								out.print("</td>");
								out.print("<td>");
								out.println(items.get(i + 1));
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
</div>
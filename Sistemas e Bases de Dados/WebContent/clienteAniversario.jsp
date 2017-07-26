<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
DBconnection db = new DBconnection();
String restaurante = (String)session.getAttribute("restaurante");
ArrayList<Object> cliente = db.getClienteAnos(restaurante);
db.disconnectSQL();
%>

<h3>
	<i class="fa fa-angle-right"></i> Clientes Aniversariantes
</h3>

<div class="row">
	<div class="col-md-5">

		<div class="content-panel">

			<table class="table">
				<thead>
					<tr>
						<th>Nome Cliente</th>
						<th>Mesa atual</th>
					</tr>
				</thead>

				<tbody>
				<%
				
				if(!(cliente.size()==0)){
					for(int i=0; i<cliente.size();i+=2){
						out.println("<tr>");
						out.println("<td>");
						out.println(cliente.get(i));
						out.println("</td>");
						out.println("<td>");
						out.println(cliente.get(i+1));
						out.println("</td>");
						out.println("</tr>");
					}
				}else{
					out.println("<tr>");
					out.println("<td>");
					out.println("nada apresentar");
					out.println("</td>");
					out.println("<td>");
					out.println("nada apresentar");
					out.println("</td>");
					out.println("</tr>");
				}
				
				%>
					
				</tbody>
			</table>
		</div>
	</div>

</div>
		


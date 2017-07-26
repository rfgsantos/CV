<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	//
	DBconnection db = new DBconnection();
	String pedidoID = request.getParameter("pedido");
	ArrayList<Object> listaItems = db.listItemsPedido(pedidoID);
	
	ArrayList<Integer> listaID = new ArrayList<Integer>();
	ArrayList<Integer> uniqueID = new ArrayList<Integer>();
	
	for(int i=0;i<listaItems.size();i+=2){
		listaID.add(db.getItemID((String)listaItems.get(i)));
		uniqueID.add(db.getUniqueID(pedidoID, db.getItemID((String)listaItems.get(i))));
	}

	db.disconnectSQL();
%>




<h4>
	<i class="fa fa-angle-right"></i>Items de um Pedido
</h4>

<table class="table">
	<thead>
		<tr>
			<th>Descricao</th>
			<th>Estado Atual</th>
			<th>Escreva</th>
			<th>Altere</th>
		</tr>
	</thead>

	<tbody>
		<%
			for (int i = 0; i < listaItems.size(); i += 2) {
				out.print("<tr>");
				out.print("<td>");
				out.println(listaItems.get(i));
				out.print("</td>");
				out.print("<td>");
				out.println(listaItems.get(i + 1));
				out.print("</td>");
				out.print("<td>");
				out.println("<input class='form-control' type='text' name ='estado'>");
				out.print("</td>");
				out.print("<td>");
				out.println("<button class='btn btn-primary' onclick='mudaEstadoItem(\"" +pedidoID+ "\",\"" +listaID.get(i/2)+ "\",\"" +uniqueID.get(i/2)+ "\")'>Submit </button>");
				out.print("</td>");
				out.print("</tr>");
			}
		%>
	</tbody>
</table>
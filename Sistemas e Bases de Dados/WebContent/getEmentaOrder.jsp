<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>


<%
	//instancia da conexão à DB
	DBconnection db = new DBconnection();

	//obter o ID do cliente
	int clientID = Integer.parseInt((String)session.getAttribute("id"));
	
	//atributo usado para obter o cardapio, atual
	String restaurante = (String)session.getAttribute("restaurante");
	
	//receber mesa como parametro de escolha do utilizador ou takeaway
	int mesaID = Integer.parseInt(request.getParameter("mesaID"));
	
	if(mesaID ==0){ 
		//ir buscar mesa ID lugares 0 no restaurante
		mesaID = db.getMesaTakeaway(restaurante);
	}
	
	session.setAttribute("mesa", mesaID);
	
	//adicionar um pedido geral
	boolean aux = db.adicionarPedido(clientID);
	
	//ir buscar o id do pedido do cliente
	int pedidoID = db.getClientPedidoID(clientID);
	
	//adicionar id pedido à sessão
	session.setAttribute("pedidoID", Integer.toString(pedidoID));

	//atribuir mesa ao pedido
	int mesa_id_checked = db.adicionarPedidoMesa(pedidoID, mesaID);
	
	//caso exista um pedido numa mesa, é alterado o ID da sessao da mesa
	if(mesa_id_checked != -1){
		session.setAttribute("mesa", mesa_id_checked);
	}
	
	//obter a ementa atual para PEDIR ITEMS
	ArrayList<Object> elementos = db.currentMenu(restaurante);
	
	//obter ID de cada item
	ArrayList<Object> idItems = new ArrayList<Object>();
	int currentID=0;
	for(int i=0; i<elementos.size();i+=3){
		currentID = db.getItemID((String)elementos.get(i));
		idItems.add(currentID);
	}
	
	
	db.disconnectSQL();
%>
<h3>
	<i class="fa fa-angle-right"></i> Faça o seu Pedido
</h3>

<div class="row">
	<div class="col-md-5">

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
								out.println("<button class='btn btn-primary' onclick='order(\""+elementos.get(i)+"\",\""+idItems.get(i/3)+"\",\""+elementos.get(i+2)+"\")'>Pedir</button>");
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





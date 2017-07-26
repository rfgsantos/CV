<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.util.ArrayList"%>

<%
//registar um cliente
String nome = request.getParameter("nome");
String data = request.getParameter("data");
String restaurante = request.getParameter("restaurantes");

session.setAttribute("restaurante", restaurante);
DBconnection db = new DBconnection();

boolean bool = db.clienteExists(nome);
int id =0;

if(!bool){
	db.registerClient(nome, data);
	id = db.getClientId(nome);
}else{
	id = db.getClientId(nome);
}

//verificar se existe pedido do cliente

boolean hasPedido = db.adicionarPedido(id);

if(hasPedido){
	int pedidoID = db.getClientPedidoID(id);
	session.setAttribute("pedidoID", Integer.toString(pedidoID));
}

db.disconnectSQL();

//define atributos da sessão
session.setAttribute("nome", nome);
session.setAttribute("data", data);
session.setAttribute("id", Integer.toString(id));


response.sendRedirect("index.jsp");


%>

<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
DBconnection db = new DBconnection();
String descricao = (String) request.getParameter("descricao");
//apanha o id do pedido
int pedidoID = Integer.parseInt((String)session.getAttribute("pedidoID"));
// id do item pedido
int itemID = db.getItemID(descricao);
// efetua pedido para DB para a tabela item_pedido ID DO ITEM MAIS ID DO PEDIDO
boolean efetuado = db.adicionarItemPedido(itemID, pedidoID);


out.print("pedido efetuado: " + efetuado);
%>
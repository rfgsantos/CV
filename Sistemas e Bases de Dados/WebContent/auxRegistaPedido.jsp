<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
DBconnection db = new DBconnection();
String restaurante= request.getParameter("restaurante");
String ingrediente = request.getParameter("ingrediente");
String fornecedor = request.getParameter("fornecedor");
String quantidade = request.getParameter("quantidade");
int ingID = db.getIDingrediente(ingrediente);
boolean aux = db.registaPedidoFornecedor(ingID, fornecedor, quantidade, restaurante);

db.disconnectSQL();

out.println(aux);
%>
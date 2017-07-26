<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>


<%

DBconnection db = new DBconnection();

int pedido = Integer.parseInt(request.getParameter("pedido"));
int item = Integer.parseInt(request.getParameter("item"));
String estado = request.getParameter("estado");
String uniqueID = request.getParameter("uniqueID");


db.modidificaEstadoItemPedido(pedido,item,estado,uniqueID);

out.println("successfull");
%>
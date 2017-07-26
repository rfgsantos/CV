<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	//instancia da conexão à DB
	DBconnection db = new DBconnection();

	String menuID = request.getParameter("menuID");
	String itemID = request.getParameter("itemID");
	int clientID = Integer.parseInt((String)session.getAttribute("id"));
	
	db.atribuiDividaCliente(clientID, itemID, menuID);

	
	db.disconnectSQL();
	
%>
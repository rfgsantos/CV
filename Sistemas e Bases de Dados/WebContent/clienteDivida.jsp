<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	//instancia da conex�o � DB
	DBconnection db = new DBconnection();

	int clientID = Integer.parseInt((String)session.getAttribute("id"));
	
	int divida = db.clienteDivida(clientID);

	
	db.disconnectSQL();
	
	out.println(divida);
	
%>
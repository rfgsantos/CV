<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	String menuID = request.getParameter("menuID");
	String itemID = request.getParameter("itemID");
	
	db.registarVenda(menuID, itemID);
	db.disconnectSQL();
%>
<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	String item = request.getParameter("item");
	ClienteTCP cl;
	String cenas;
	String sess_key = (String) session.getAttribute("ID");
	System.out.println("debug item placeorder: " + item);
	cl = new ClienteTCP("4" + sess_key + item +":");
	cenas = cl.respostaServidor();
	out.println(cenas);
%>

	
	



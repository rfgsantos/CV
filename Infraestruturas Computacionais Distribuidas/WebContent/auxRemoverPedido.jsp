<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	String cliente = request.getParameter("cliente");
	ClienteTCP cl;
	String cenas;
	cl = new ClienteTCP("r"+cliente);
	cenas = cl.respostaServidor();
	out.println(cenas);
%>
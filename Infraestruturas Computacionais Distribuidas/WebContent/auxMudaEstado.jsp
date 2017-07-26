<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	String cliente = request.getParameter("cliente");
	String estado = request.getParameter("estado");
	ClienteTCP cl;
	String cenas;
	cl = new ClienteTCP("m1" + cliente + "_" + estado);
	cenas = cl.respostaServidor();
	out.println(cenas);
%>

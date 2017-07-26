<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	String valueEstrela = request.getParameter("estrela");
	String empregado = request.getParameter("empregado"); 
	ClienteTCP cl;
	String cenas;
	cl = new ClienteTCP("7" + valueEstrela + empregado);
	cenas = cl.respostaServidor();
	out.println(cenas);
%>

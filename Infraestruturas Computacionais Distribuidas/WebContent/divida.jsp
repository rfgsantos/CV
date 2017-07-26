<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	ClienteTCP cl;
	String cenas;
	String sess_key = (String) session.getAttribute("ID");
	cl = new ClienteTCP("6" + sess_key);
	cenas = cl.respostaServidor();
	
	if(cenas == null){
		cenas = "0";
	}
	
	
	// out.println(cenas);
	
	// out.println(rekt.length);
	out.println(cenas);
%>

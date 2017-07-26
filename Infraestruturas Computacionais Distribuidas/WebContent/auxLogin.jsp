
<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>
<%


String user = request.getParameter("nome");
String pass = request.getParameter("password");

String resposta;
ClienteTCP cl;
cl = new ClienteTCP("9" + user + "_" +pass);
resposta = cl.respostaServidor();

if(!resposta.equals("true")){
	response.sendRedirect("indexFuncionario.jsp");
	
}else{
	response.sendRedirect("login.jsp");


}
%>









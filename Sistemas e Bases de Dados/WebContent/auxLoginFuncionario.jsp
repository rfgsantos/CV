<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.Scanner"%>
<%


String user = request.getParameter("nome");
String pass = request.getParameter("password");
String restaurante = null;
DBconnection db = new DBconnection();

boolean aux = db.loginAuthentication(user, pass);
if(aux){
	response.sendRedirect("indexFuncionario.jsp");
	restaurante = db.getFuncionarioRest(user, pass);
	session.setAttribute("restaurante", restaurante);
	
}else{
	response.sendRedirect("login.jsp");
}


db.disconnectSQL();
	
%>









<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	String ingQuant = request.getParameter("ingQuant");
	
	String[] ingredientes = ingQuant.split("_");
	int[] ingredienteID = new int[ingredientes.length];
	int[] quantidade = new int[ingredientes.length];
	String current="";
	String restaurante = (String)session.getAttribute("restaurante");
	
	for(int i =0; i<ingredientes.length;i++){
		current = ingredientes[i];
		for(int j =0; j<current.length();j++){
			if(current.charAt(j)=='-'){
				ingredienteID[i] = Integer.parseInt(current.substring(0, j));
				quantidade[i] = Integer.parseInt(current.substring(j+1));
			}
		}
	}
	
	//metodo que regista a quantidade
	for(int x=0;x<ingredienteID.length;x++){
		db.registaQuantidadeIngrediente(ingredienteID[x], quantidade[x], restaurante);
	}

	

	

%>
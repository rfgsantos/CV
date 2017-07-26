<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	ClienteTCP cl;
	String cenas;
	cl = new ClienteTCP("m0");
	cenas = cl.respostaServidor();
	// out.println(cenas);
	String[] ementaPrecos = cenas.split(":");
	String[] clientes = new String[ementaPrecos.length];
	String[] estados = new String[ementaPrecos.length];
	
	for(int i=0; i<ementaPrecos.length;i++){
		String current = ementaPrecos[i];
		for(int j=0; j<current.length();j++){
			if(ementaPrecos[i].charAt(j) == '_'){
				clientes[i] = ementaPrecos[i].substring(0,j);
				estados[i] = ementaPrecos[i].substring(j+1);
			}
		}
	}
	

%>
          	<h3><i class="fa fa-angle-right"></i> Consulta Ementa</h3>
				<div class="row">
	                  <div class="col-md-8">
	                  	  <div class="content-panel">
	                  	  	  <h4><i class="fa fa-angle-right"></i>Ementa Atual</h4>
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                          	<th>Cliente</th>
		                            <th>Estado</th>
		                            <th>Mudar</th>
		                            <th>Mudar</th>
		                            <th>Mudar</th>
		                            <th>Mudar</th>
		                          </tr>
		                          </thead>
		                          
		                          <tbody>
									<%
									for (int i = 0; i < ementaPrecos.length-1; i++) {
										out.print("<tr>");
										out.print("<td>");
										out.println(clientes[i]);
										out.print("</td>");
										out.print("<td>");
										out.println(estados[i]);
										out.print("</td>");
										out.print("<td><button class='btn btn-primary' onclick='mudarEstado(\""+clientes[i]+"\" , \"Aceitar\")'>Aceitar</button></td>");
										out.print("<td><button class='btn btn-primary' onclick='mudarEstado(\""+clientes[i]+"\" , \"Preparacao\")'>Preparacao</button></td>");
										out.print("<td><button class='btn btn-primary' onclick='mudarEstado(\""+clientes[i]+"\" , \"Pronto\")'>Pronto</button></td>");
										out.print("<td><button class='btn btn-primary' onclick='mudarEstado(\""+clientes[i]+"\" , \"Entregue\")'>Entregue</button></td>");
										out.print("</tr>");
									}
									%>
		                          </tbody>
		                      </table>
	                  	  </div><! --/content-panel -->
	                  	  
	                  	  
	                  </div><!-- /col-md-12 -->
				</div><!-- row -->
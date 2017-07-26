<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	ClienteTCP cl;
	String cenas;
	cl = new ClienteTCP("1");
	cenas = cl.respostaServidor();
	// out.println(cenas);
	String[] ementaPrecos = cenas.split(":");
	String[] pratos = new String[cenas.length()];
	String[] precos = new String[cenas.length()];
	
	for(int i=0; i<ementaPrecos.length;i++){
		String current = ementaPrecos[i];
		for(int j=0; j<current.length();j++){
			if(ementaPrecos[i].charAt(j) == '_'){
				pratos[i] = ementaPrecos[i].substring(0,j);
				precos[i] = ementaPrecos[i].substring(j+1);
			}
		}
	}

%>
          	<h3><i class="fa fa-angle-right"></i> Consulta Ementa</h3>
				<div class="row">
	                  <div class="col-md-6">
	                  	  <div class="content-panel">
	                  	  	  <h4><i class="fa fa-angle-right"></i>Ementa Atual</h4>
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                          	<th>Nome Prato</th>
		                            <th>Preco</th>
<!-- 		                            <th>Sobremesas</th> -->
		                          </tr>
		                          </thead>
		                          
		                          <tbody>
									<%
									for (int i = 0; i < ementaPrecos.length-1; i++) {
										out.print("<tr>");
										out.print("<td>");
										out.println(pratos[i]);
										out.print("</td>");
										out.print("<td>");
										out.println(precos[i] + "$");
										out.print("</td>");
										out.print("<td><button class='btn btn-primary' onclick='order(\""+pratos[i]+"\")'>Pedir</button></td>");
										out.print("</tr>");
									}
									%>
		                          </tbody>
		                      </table>
	                  	  </div><! --/content-panel -->
	                  </div><!-- /col-md-12 -->
				</div><!-- row -->
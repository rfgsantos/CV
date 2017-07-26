<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
String date = request.getParameter("date");
System.out.println("DEBUG DATA  AUX DATA : " + date);
	ClienteTCP cl;
	String cenas;
	cl = new ClienteTCP("3"+date);
	cenas = cl.respostaServidor();
	String[] ementaPrecos = cenas.split(":");
	//ultima posicao do array é NULL por causa do comando string ex: prato:vazio
	
	String[] pratos = new String[ementaPrecos.length];
	String[] precos = new String[ementaPrecos.length];

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

          	<h3><i class="fa fa-angle-right"></i> Ementas</h3>
				<div class="row">
	                  <div class="col-md-6">
	                  	  <div class="content-panel">
	                  	  	  <h4><i class="fa fa-angle-right"></i>Ementa Almoco</h4>
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                          	<th>Nome Prato</th>
		                            <th>Precos</th>
<!-- 		                            <th>Sobremesas</th> -->
		                          </tr>
		                          </thead>
		                          
		                          <tbody>
									<%
									
									for (int i = 0; i < ementaPrecos.length-9; i++) {
										out.print("<tr>");
										out.print("<td>");
										out.println(pratos[i]);
										out.print("</td>");
										out.println("<td>");
										out.println(precos[i] + "$");
										out.println("</td>");
										out.print("</tr>");
									}
									%>
		                          </tbody>
		                      </table>
	                  	  </div><! --/content-panel -->
	                  </div><!-- /col-md-12 -->
	                  		<div class="row">
	                  <div class="col-md-6">
	                  	  <div class="content-panel">
	                  	  	  <h4><i class="fa fa-angle-right"></i>Ementa Jantar</h4>
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                          	<th>Nome Prato</th>
		                            <th>Precos</th>
<!-- 		                            <th>Sobremesas</th> -->
		                          </tr>
		                          </thead>
		                          
		                          <tbody>
									<%
									
									for (int i = ementaPrecos.length-9; i < ementaPrecos.length-1; i++) {
										out.print("<tr>");
										out.print("<td>");
										out.println(pratos[i]);
										out.print("</td>");
										out.println("<td>");
										out.println(precos[i] + "$");
										out.println("</td>");
										out.print("</tr>");
									}
									%>
		                          </tbody>
		                      </table>
	                  	  </div><! --/content-panel -->
	                  </div><!-- /col-md-12 -->
				</div><!-- row -->
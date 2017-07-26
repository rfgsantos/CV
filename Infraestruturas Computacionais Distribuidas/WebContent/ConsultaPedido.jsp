<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	ClienteTCP cl;
	String cenas;
	String sess_key = (String) session.getAttribute("ID");
	cl = new ClienteTCP("5"+ sess_key);
	cenas = cl.respostaServidor();
	// out.println(cenas);
	String[] pedido = cenas.split(":");
	// out.println(rekt.length);
	String estadoPedido = pedido[pedido.length-1];

%>


          	<h3><i class="fa fa-angle-right"></i>Consulta Pedido</h3>
				<div class="row">
	                  <div class="col-md-6">
	                  	  <div class="content-panel">
	                  	  	  <h4><i class="fa fa-angle-right"></i>Elementos Pedidos</h4>
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                          	<th>Produtos</th>
		                          	<th>Estado</th>
		                          	<th>1</th>
		                          	<th>2</th>
		                          	<th>3</th>
		                          	<th>4</th>
		                          	<th>5</th>
<!-- 		                            <th>Pratos</th> -->
<!-- 		                            <th>Sobremesas</th> -->
		                          </tr>
		                          </thead>
		                          
		                          <tbody>
									<%
									for (int i = 0; i < pedido.length-1; i++) {
										out.print("<tr>");
										out.print("<td>");
										out.print(pedido[i]);
										out.print("</td>");
										out.print("<td>");
										out.print(estadoPedido);
										out.print("</td>");
										out.print("<td>");
										out.print("<input type='radio' name=\""+pedido[i]+"\" value='1' onclick='atribuiEstrela(\""+pedido[i]+"\")'/>");
										out.print("</td>");
										out.print("<td>");
										out.print("<input type='radio' name=\""+pedido[i]+"\" value='2' onclick='atribuiEstrela(\""+pedido[i]+"\")'/>");
										out.print("</td>");
										out.print("<td>");
										out.print("<input type='radio' name=\""+pedido[i]+"\" value='3' onclick='atribuiEstrela(\""+pedido[i]+"\")'/>");
										out.print("</td>");
										out.print("<td>");
										out.print("<input type='radio' name=\""+pedido[i]+"\" value='4' onclick='atribuiEstrela(\""+pedido[i]+"\")'/>");
										out.print("</td>");
										out.print("<td>");
										out.print("<input type='radio' name=\""+pedido[i]+"\" value='5' onclick='atribuiEstrela(\""+pedido[i]+"\")'/>");
										out.print("</td>");
										out.print("</tr>");
									}
									%>
		                          </tbody>
		                      </table>
	                  	  </div><! --/content-panel -->
	                  	  
	                  	  
	                  </div><!-- /col-md-12 -->
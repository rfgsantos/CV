<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="DOM.casaApplet"%>


<%
	ClienteTCP cl;
	String cenas;
	// 	cl = new ClienteTCP("1");
	// 	cenas = cl.respostaServidor();
	// 	// out.println(cenas);
	// 	String[] ementa = cenas.split(":");
	// 	// out.println(rekt.length);
%>


<h3>
	<i class="fa fa-angle-right"></i> APPLET EMPREGADO DO MES
</h3>
<div class="row">
	<div class="col-md-6">
		<div class="content-panel">
			<embed src="build/classes/DOM/casaApplet.class" width="400" height="400">
			</embed>
<!-- <embed src="DOM.casaApplet.class" -->
<!--   width="200" height="200" -->
<!--   type="application/x-java-applet;version=1.8.0_101"/> -->

</div>
	</div>
</div>
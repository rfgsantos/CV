<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.util.Random" %>

<%
	Random rnd = new Random();
	
	
	int n = 100000 + rnd.nextInt(900000);
	if (session.getAttribute("ID") == null){
		session.setAttribute("ID",  Integer.toString(n));
	}
	String sess_key = (String) session.getAttribute("ID");
	
	//atribuir o numero do ID de um empregado
	int idempregado = rnd.nextInt(3)+1;
	ClienteTCP cl;
	String empregado;
	cl = new ClienteTCP("8" + idempregado);
	System.out.println("RESPOSTA SERVIDOR : ----" + cl.respostaServidor());
	
	empregado = cl.respostaServidor();
	
	System.out.println("STRING EMPREGADO : " + empregado);
	
	%>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>IECD - Trabalho pratico 2</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/style-responsive.css" rel="stylesheet">

</head>

<body>

	<section id="container"> <!-- **********************************************************************************************************************************************************
												TOP BAR CONTENT & NOTIFICATIONS
												*********************************************************************************************************************************************************** -->
	<!--header start--> <header class="header black-bg">
	<div class="sidebar-toggle-box">
		<div class="fa fa-bars tooltips" data-placement="right"
			data-original-title="Toggle Navigation"></div>
	</div>
	<!--logo start--> <a href="#" class="logo"><b>Casa de
			Pasto Restaurante</b>
			<b style="margin-left: 50px">divida: <span id="divida">0</span></b></a> <!--logo end--> </header> <!--header end--> <!-- **********************************************************************************************************************************************************
													MAIN SIDEBAR MENU
													*********************************************************************************************************************************************************** -->
	<!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">

			<p class="centered">
				<a href="index.jsp"><img src="assets/nasty.png"
					class="img-circle" width="60"></a>
			</p>
			<h5 class="centered">Casa de Pasto</h5>

			<li class="mt"><a href="#" onclick="loadDoc()"> <i
					class="fa fa-dashboard"></i> <span>Consulta Ementa</span>
			</a></li>

			<li class="sub-menu"><a href="#" onclick="loadDH()"> <i
					class="fa fa-book"></i> <span>Consulta Ementa DH</span>
			</a></li>
			
			<li class="sub-menu"><a href="#" onclick="loadData()"> <i
					class="fa fa-book"></i> <span>Consulta Ementa Data</span>
			</a></li>

			<li class="sub-menu"><a href="#" onclick="loadConsultaPedido()"> <i
					class="fa fa-book"></i> <span>Consulta Pedido</span>
			</a></li>
			<li class="sub-menu"><a href="#" onclick="loadApplet()"> <i
					class="fa fa-desktop"></i> <span>Empregado do Mes</span>
			</a></li>
			<li class="sub-menu"><a href="#" onclick="loadDocOrders()">
					<i class="fa fa-tasks"></i> <span>Registar Pedido</span>
			</a></li>

		</ul>
		<!-- sidebar menu end-->
	</div>
	</aside> <!--sidebar end--> <!-- **********************************************************************************************************************************************************
													MAIN CONTENT
													*********************************************************************************************************************************************************** -->
	<!--main content start--> <section id="main-content"> <section
		class="wrapper" id='main-data'>

	<h3>
		<i class="fa fa-angle-right"></i>Bem vindo ao melhor restaurante do
		mundo, e quiçá da Europa.
	</h3>

	</section> <! --/wrapper --> </section><!-- /MAIN CONTENT --> <!--main content end--> <!--footer start-->
	<footer class="site-footer">
	<div class="text-center">
		2015/2016 - LEIM - ICD - Rui Santos 39286 <a
			href="basic_table.html#" class="go-top"> <i
			class="fa fa-angle-up"></i>
		</a>
	</div>
	</footer> <!--footer end--> </section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!--script for this page-->

	<script>
			//custom select box
			function loadDoc() {
				console.log('loadDoc');
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("main-data").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "getEmenta.jsp", true);
				xhttp.send();
			}

			function loadDH() {
				console.log('loadDH');
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("main-data").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "auxDHora.jsp", true);
				xhttp.send();
			}
			//
			function loadDocDH() {
				console.log('loadDocDH');
				var xhttp = new XMLHttpRequest();
				var date = $('input[name=inputDate]').val().replace(/-/g, '');
				console.log("PRINT DATA DH: " + date);
				var time = $('input[name=inputTime]').val().replace(/:/g, '');
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("results").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "getEmentaDHora.jsp?date="+date+"&time="+time, true);
				xhttp.send();
			}
			
			function loadDocOrders() {
				console.log('loadDocOrders');
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("main-data").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "getEmentaForOrder.jsp", true);
				xhttp.send();
			}
			
			function atribuiEstrela(value){
				console.log('atribuiEstrela');
				var xhttp = new XMLHttpRequest();
				var estrela = $("input[name='"+value+"']:checked").val();
				var emp = "<% out.print(empregado.trim());%>";
				emp = emp.trim();
				console.log(emp);
				alert("ok: " + estrela)
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						alert("ok: " + estrela)
					}
				};
				xhttp.open("GET", "estrelas.jsp?estrela="+estrela+"&empregado="+emp, true);
				xhttp.send();
			}
			
			function order(item) {
				console.log('order');
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						alert(xhttp.responseText.trim());
						loadDivida();				
					}
				};
				xhttp.open("GET", "placeOrder.jsp?item="+item, true);
				xhttp.send();
			}
			
			function loadConsultaPedido() {
				console.log('loadDoc');
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("main-data").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "ConsultaPedido.jsp", true);
				xhttp.send();
			}
			
			function loadData(){
				console.log('loadD');
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("main-data").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "auxData.jsp", true);
				xhttp.send();
			}
			
			function loadDocData(){
				console.log('loadDocData');
				var xhttp = new XMLHttpRequest();
				var auxdate = $('input[name=inputDate]').val().replace(/-/g, '');
				
				console.log("DATA PRINT: " + $('input[name=inputDate]').val());
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("results").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "getEmentaData.jsp?date="+auxdate, true);
				xhttp.send();
			}
			
			
			function loadDivida() {
				console.log('loadDivida');
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("divida").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "divida.jsp", true);
				xhttp.send();
			}
			
			function loadApplet(){
				alert("Loading Applet");
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (xhttp.readyState == 4 && xhttp.status == 200) {
						console.log('200ok');
						document.getElementById("main-data").innerHTML = xhttp.responseText;
					}
				};
				xhttp.open("GET", "appletEmpregado.jsp", true);
				xhttp.send();			}
			
			$(function() {
				//           $('select.styled').customSelect();
				//chama sempre uma vez quando a pagina e LOADED
				loadDivida();
			});
</script>

</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.util.Random" %>


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

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
											<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
											<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
											<![endif]-->
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
					class="fa fa-dashboard"></i> <span>Ve pedidos</span>
			</a></li>

			<li class="sub-menu"><a href="#" onclick="loadMudaEstado()"> <i
					class="fa fa-book"></i> <span>Muda Estado</span>
			</a></li>
			
			<li class="sub-menu"><a href="#" onclick="loadRemoverPedido()"> <i
					class="fa fa-book"></i> <span>Remover Pedido</span>
			</a></li>
			

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
		<i class="fa fa-angle-right"></i>Pagina Funcionario
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
	
	function loadMudaEstado(){
		console.log('loadPedidos');
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("main-data").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("GET", "mudaEstadoFuncionario.jsp", true);
		xhttp.send();	
	}
	
	function mudarEstado(cliente, estado){
		console.log('mudarEstado');
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				alert(xhttp.responseText.trim());
				
			}
		};
		xhttp.open("GET", "auxMudaEstado.jsp?cliente="+cliente+"&estado="+estado, true);
		xhttp.send();	
	}
	
	function loadRemoverPedido(){
		console.log('mudarEstado');
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("main-data").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("GET", "removerPedido.jsp", true);
		xhttp.send();	
	}
	
	function removerPedido(cliente){
		
		console.log('mudarEstado');
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				alert(xhttp.responseText.trim());
				loadRemoverPedido();
			}
		};
		xhttp.open("GET", "auxRemoverPedido.jsp?cliente="+cliente, true);
		xhttp.send();	
	}
	
	
	</script>


</body>
</html>

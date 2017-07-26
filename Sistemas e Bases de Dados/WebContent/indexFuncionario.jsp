<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.util.Random"%>


<html lang="pt">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>SBD - Trabalho pratico 2</title>

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
	<!--logo start--> <a href="#" class="logo"><b>Menu Funcionario/Gestor</b></a> <!--logo end--> </header> <!--header end--> <!-- **********************************************************************************************************************************************************
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

			<li class="mt"><a href="#" onclick="checkPedidos()"> <i
					class="fa fa-dashboard"></i> <span>Pedidos</span>
			</a></li>

			<li class="sub-menu"><a href="#" onclick="loadRemovePedido()">
					<i class="fa fa-book"></i> <span>Remover Pedido</span>
			</a></li>

			<li class="sub-menu"><a href="#"
				onclick="registaPedidosFornecedor()"> <i class="fa fa-book"></i>
					<span>Regista Pedido Fornecedor</span>
			</a></li>

			<li class="sub-menu"><a href="#" onclick="clienteAniversario()">
					<i class="fa fa-book"></i> <span>Cliente Aniversário</span>
			</a></li>
			
			<li class="sub-menu"><a href="#" onclick="stocks()">
					<i class="fa fa-book"></i> <span>Stocks</span>
			</a></li>
			
			<li class="sub-menu"><a href="#" onclick="auxEstatisticas()">
					<i class="fa fa-book"></i> <span>Estatisticas Items</span>
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
		2016/2017 - LEIM - SBD- Rui Santos 39286 <a href="basic_table.html#"
			class="go-top"> <i class="fa fa-angle-up"></i>
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
	
	function auxEstatisticas(){
		console.log('estatisticas');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("main-data").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("GET","auxEstatisticas.jsp",true);
		xhttp.send();
	}
	
	function estatisticas(escolha){
		console.log('estatisticas');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("results").innerHTML = xhttp.responseText;
			}
		};
		
		xhttp.open("GET","lucroORvendido.jsp?escolha="+escolha,true);
		xhttp.send();
		
	}
	
	function checkPedidos(){
		console.log('checkPedidos');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("main-data").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("GET","auxMesas.jsp",true);
		xhttp.send();
	}
	
	function showPedidosMesa(mesa){
		console.log('showPedidosMesa');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("pedidos").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("GET","auxPedidos.jsp?mesa="+mesa,true);
		xhttp.send();
		$(document).ready(function(){
			$('#pedidos').show();
			$('#items').hide();
		});
	}
	
	function showItemsPedido(pedido){
		console.log('showItemsPedido');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("items").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("GET","auxItems.jsp?pedido="+pedido,true);
		xhttp.send();
		$(document).ready(function(){
			$('#items').show();
		});
	}
	
	function mudaEstadoItem(pedido,item,uniqueID){
		console.log('mudaEstadoPedido');
		var xhttp = new XMLHttpRequest();
		var estado = $('input[name=estado]').val();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				alert(xhttp.responseText);
			}
		};
		xhttp.open("GET","alteraEstado.jsp?pedido="+pedido+"&item="+item+"&estado="+estado+"&uniqueID="+uniqueID,true);
		xhttp.send();
	}
	
	function registaPedidosFornecedor(){
		console.log('registaPedidosFornecedor');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("main-data").innerHTML = xhttp.responseText;
				
			}
		};
		xhttp.open("GET","registaPedidosFornecedor.jsp",true);
		xhttp.send();
	}
	
	function fornecedoresIngrediente(item){
		console.log('fornecedoresIng');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("fornecedorIng").innerHTML = xhttp.responseText;
				
			}
		};
		xhttp.open("GET","fornecedorIng.jsp?ingrediente="+item,true);
		xhttp.send();
		
		$(document).ready(function(){
			$('#fornecedorIng').show();
			$('#quantidade').hide();
			
		});
	}
	
	function precoFornecedor(fornecedor,ingrediente){
		console.log('precoFornecedor');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("quantidade").innerHTML = xhttp.responseText;
				
			}
		};
		xhttp.open("GET","precoFornecedorQuantidade.jsp?fornecedor="+fornecedor+"&ingrediente="+ingrediente,true);
		xhttp.send();
		
		$(document).ready(function(){
			$('#quantidade').show();
		});
	}
	
	function encomendar(ingrediente,fornecedor,restaurante){
		console.log('encomendar');
		console.log(fornecedor);
		var xhttp = new XMLHttpRequest();
		var quantidade = $('input[name=quantidadePedida]').val();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				alert(xhttp.responseText);
			}
		};
		if(!(quantidade == 'empty')){
			xhttp.open("GET","auxRegistaPedido.jsp?ingrediente="+ingrediente+"&fornecedor="+fornecedor+"&restaurante="+restaurante+"&quantidade="+quantidade,true);
			xhttp.send();
		}else{
			alert('Especifique uma quantidade');
		}
	}
	
	function clienteAniversario(){
		console.log('clienteAniversario');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("main-data").innerHTML = xhttp.responseText;
				
			}
		};
		xhttp.open("GET","clienteAniversario.jsp", true);
		xhttp.send();
		
	}
	
	function stocks(){
		console.log('stocks');
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("main-data").innerHTML = xhttp.responseText;
				
			}
		};
		xhttp.open("GET","stocks.jsp", true);
		xhttp.send();
		
	}
	
	function roturaOUatuais(value){
		console.log('roturaOUatuais');
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("stocks").innerHTML = xhttp.responseText;
				
			}
		};
		if(value == 'rotura'){
			xhttp.open("GET","rotura.jsp", true);
			xhttp.send();
		}else{
			xhttp.open("GET","atuais.jsp", true);
			xhttp.send();
		}
		
	}
	
	</script>

</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.util.Random"%>


<html lang="pt">
<head>

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
	<!--logo start--> <a href="#" class="logo"><b>Menu Cliente</b> <b style="margin-left: 50px">divida: <span
			id="divida">0</span></b><b style='margin-left: 3px'>$</b></a> <!--logo end--> </header> <!--header end--> <!-- **********************************************************************************************************************************************************
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
					class="fa fa-book"></i> <span>Consulta Ementa</span>
			</a></li>

			<li class="sub-menu"><a href="#" onclick="loadDH()"> <i
					class="fa fa-calendar"></i> <span>Consulta Ementa DH</span>
			</a></li>

			<li class="sub-menu"><a href="#" onclick="loadConsultaPedido()">
					<i class="fa fa-book"></i> <span>Consulta Pedido</span>
			</a></li>

			<li class="sub-menu"><a href="#" onclick="loadDocOrders()">
					<i class="fa fa-thumb-tack"></i> <span>Registar Pedido</span>
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
			var time = $('input[name=inputTime]').val().replace(/:/g, '');
			var restaurante = $('select[name=restaurantes]').val();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					document.getElementById("results").innerHTML = xhttp.responseText;
				}
			};
			xhttp.open("GET", "getEmentaDataHora.jsp?date=" + date + "&time="
					+ time + "&rest=" + restaurante, true);
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
			xhttp.open("GET", "TakeawayORpresencial.jsp", true);
			xhttp.send();
		}

		function order(item,itemID,menuID) {
			console.log('order');
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					alert(xhttp.responseText);
					atribuiDivida(itemID,menuID);
					registarVenda(itemID,menuID);
					openReceitaStock(itemID);
					
				}
			};
			xhttp.open("GET", "placeOrder.jsp?descricao=" + item, true);
			xhttp.send();
		}
		
		function registarVenda(itemID,menuID){
			console.log('registarVenda');
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					
				}
			};
			xhttp.open("GET", "registarVenda.jsp?itemID="+itemID+"&menuID="+menuID, true);
			xhttp.send();
		}
		
		function atribuiDivida(itemID,menuID){
			console.log('atribuiDivida');
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					loadDivida();	
				}
			};
			xhttp.open("GET", "atribuiDivida.jsp?itemID=" + itemID+"&menuID="+menuID, true);
			xhttp.send();
		}
		
		function openReceitaStock(id_item) {
			console.log('openReceitaDivida: ' + id_item);
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					retiraQuantidadeIngrediente(this);
				}
			};
			xhttp.open("GET", "receitasXML/" + id_item + "elemento.xml", true);
			xhttp.send();
		}
		
		function retiraQuantidadeIngrediente(xml){
			console.log('retirarQuantidadeIngrediente');
			var i;
			var xmlDoc = xml.responseXML;
			var ingredientes = xmlDoc.getElementsByTagName("ingrediente");
			var ingredienteQuantidade = "";
			for (i = 0; i < ingredientes.length; i++) {
				if(i== ingredientes.length-1){
					ingredienteQuantidade += ingredientes[i].getAttributeNode("id").value + "-" +ingredientes[i].getElementsByTagName("quantidade")[0].childNodes[0].nodeValue;
				}else{
					ingredienteQuantidade += ingredientes[i].getAttributeNode("id").value + "-" +ingredientes[i].getElementsByTagName("quantidade")[0].childNodes[0].nodeValue + "_";
				}
			}
			console.log("STRING A ENVIAR: " + ingredienteQuantidade);
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					alert(xhttp.responseText);
				}
			};
			xhttp.open("GET", "retiraQuantidadeIngredientes.jsp?ingQuant="+ingredienteQuantidade, true);
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
			xhttp.open("GET", "consultaPedido.jsp", true);
			xhttp.send();
		}

		function onchangeEmenta(value) {
			console.log('loadDocData');
			var xhttp = new XMLHttpRequest();
			var restaurante = value;

			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					document.getElementById("results").innerHTML = xhttp.responseText;
				}
			};
			xhttp.open("GET", "onchangeEmenta.jsp?nomeRestaurante="
					+ restaurante, true);
			xhttp.send();
		}

		function showReceita(id_item) {
			console.log('showReceita: ' + id_item);
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					displayReceita(this);
				}
			};
			xhttp.open("GET", "receitasXML/" + id_item + "elemento.xml", true);
			xhttp.send();
		}

		function displayReceita(xml) {
			console.log('displayReceitaXML');
			var i, j;
			var xmlDoc = xml.responseXML;
			var tableIngrediente = "<table class='table'><thead><tr><th>Ingrediente</th><th>Quantidade</th><th>Unidade</th></tr></thead><tbody>";
			var tablePreparos = "<table class='table'><thead><tr><th>Preparo</th></tr></thead><tbody>";
			var ingredientes = xmlDoc.getElementsByTagName("ingrediente");
			var preparos = xmlDoc.getElementsByTagName("preparo");
			console.log("LENGTH DO PREPARO: " + preparos.length);
			for (i = 0; i < ingredientes.length; i++) {
				tableIngrediente += "<tr><td>"
						+ ingredientes[i].getElementsByTagName("nome")[0].childNodes[0].nodeValue
						+ "</td><td>"
						+ ingredientes[i].getElementsByTagName("quantidade")[0].childNodes[0].nodeValue
						+ "</td><td>"
						+ ingredientes[i].getElementsByTagName("quantidade")[0].getAttributeNode("unidade").value
						+ "</td></tr>";
			}
			tableIngrediente += "</tbody></table>";

			for (j = 0; j < preparos.length; j++) {
				tablePreparos += "<tr><td>"
						+ preparos[j].childNodes[0].nodeValue + "</td></tr>";
			}

			tablePreparos += "</tbody></table>";

			document.getElementById("ingredientes").innerHTML = tableIngrediente;
			document.getElementById("preparos").innerHTML = tablePreparos;
			
			$(document).ready(function(){
				$('#ingredientes').show();
				$('#preparos').show();
			});
			
		}
		
		function takeawayorpresencial(escolher) {
			console.log('takeawayPresencial');
			var escolha = escolher;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					if(escolha == 'presencial'){
						document.getElementById("mesas").innerHTML = xhttp.responseText;
						document.getElementById("escolhaRestaurante").innerHTML = " ";
						document.getElementById("results").innerHTML = " ";
					}else{
						document.getElementById("escolhaRestaurante").innerHTML = xhttp.responseText;
						document.getElementById("mesas").innerHTML = " ";
						document.getElementById("results").innerHTML = " ";
					}
					
					
				}
			};
			
			if(escolha == 'presencial'){
				xhttp.open("GET", "escolheMesa.jsp", true);
				xhttp.send();
			}else{
				//significa takeaway
				xhttp.open("GET", "getEmentaOrder.jsp?mesaID=0", true);
				xhttp.send();
			}
			
		}
		
		function atribuiMesa(value) {
			console.log('loadEmentaORDER');
			var xhttp = new XMLHttpRequest();
			var mesa = $('select[name=escolhaMesas]').val();
			console.log('numero da mesa: ' + mesa);
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					console.log('200ok');
					document.getElementById("results").innerHTML = xhttp.responseText;
				}
			};
			if(!(mesa == 'empty')){
				xhttp.open("GET", "getEmentaOrder.jsp?mesaID="+mesa, true);
				xhttp.send();
			}
			
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
			xhttp.open("GET", "clienteDivida.jsp", true);
			xhttp.send();
		}

		$(function() {
			//           $('select.styled').customSelect();
			//chama sempre uma vez quando a pagina e LOADED
			loadDivida();
		});
	</script>

</body>
</html>

<%@ page import="SBD.DBconnection"%>
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
	<!--sidebar start--> <aside>
	<div id="sidebar" class="nav-collapse ">

	</div>
	</aside>

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
			<b style="margin-left: 50px">divida: <span id="divida">0</span></b></a> <!--logo end--> </header> 
			
			<section id="main-content"> <section
		class="wrapper" id='main-data'>
		<h3><i class="fa fa-angle-right"></i> Dados Funcionario</h3>
						<div class="row">
							<div class="col-md-3">
							
	                  	 	 <div class="content-panel">
	                  	  	
	                  	  	<form action="auxLoginFuncionario.jsp" method="POST">
	                  	  		<input class="form-control" type="text" name='nome'>nome</input>
								<input class="form-control" type="password" name="password">password</input>
								<br>
								<input class="btn btn-primary" class="btn btn-primary" type="submit" value="Submit" />
							</form>
	                  	  	</div>
	                 	 </div>
						</div>
						<h3><i class="fa fa-angle-right"></i> Continuar como cliente</h3>
						<div class="row">
							<div class="col-md-3">
							
	                  	 	 <div class="content-panel">           
								<button class='btn btn-primary'onclick="auxCliente()">Continuar</button>
	                  	  	</div>
	                 	 </div>
						</div>

	</section> <! --/wrapper --> </section><!-- /MAIN CONTENT --> <!--main content end--> <!--footer start-->
	<footer class="site-footer">
	<div class="text-center">
		2016/2017 - LEIM - SBD - Rui Santos 39286 <a
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
	
	<script>
	function continuarAnonimo(){
		window.location.href = "index.jsp?clienteID=0";
	}
	
	function auxCliente(){
		console.log('loadAuxCliente');
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				console.log('200ok');
				document.getElementById("main-data").innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("GET", "auxCliente.jsp", true);
		xhttp.send();
	}
	
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
	</script>
	
	</body>
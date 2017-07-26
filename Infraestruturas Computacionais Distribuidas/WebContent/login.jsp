<%@ page import="DOM.ClienteTCP"%>
<%@ page import="java.util.Scanner"%>

<%
	ClienteTCP cl;
	String cenas;
// 	cl = new ClienteTCP("1");
// 	cenas = cl.respostaServidor();
// 	// out.println(cenas);
// 	String[] ementa = cenas.split(":");
// 	// out.println(rekt.length);

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

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
											<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
											<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
											<![endif]-->
</head>
<body>
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
	                  	  	
	                  	  	<form action="auxLogin.jsp" method="POST">
	                  	  		<input type="text" name='nome'/>
								<input type="password" name="password"/>
								<input class="btn btn-primary" type="submit" value="Submit"/>
							</form>
	                  	  	</div>
	                 	 </div>
						</div>
						<h3><i class="fa fa-angle-right"></i> Continuar como cliente</h3>
						<div class="row">
							<div class="col-md-3">
							
	                  	 	 <div class="content-panel">           
								<button class='btn btn-primary'onclick="troca()">Continuar</button>
	                  	  	</div>
	                 	 </div>
						</div>

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
	
	<script>
	function troca(){
		window.location.href = "index.jsp";
	}
	</script>
	
	</body>
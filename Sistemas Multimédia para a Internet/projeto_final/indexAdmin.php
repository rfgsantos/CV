<!DOCTYPE html>
<?php
//fazemos session start apenas uma vez
require_once("../Lib/db.php");
session_start();

if(isset($_SESSION['username']) && !empty($_SESSION['username']) && $_SESSION['tipo']=='Admin'){
  $username = $_SESSION['username'];

  dbConnect(ConfigFile);
  mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
  $query_fotografias = "SELECT Fotografia_perfil,Fotografia_aux FROM userclient WHERE username='$username'";
  $result_fotografias = mysqli_query($GLOBALS['ligacao'], $query_fotografias);
  $result_array_fotografias = mysqli_fetch_array($result_fotografias);

  $query_nome = "SELECT nome FROM userclient WHERE username='$username'";
  $result_nome = mysqli_query($GLOBALS['ligacao'], $query_nome);
  $result_array_nome = mysqli_fetch_array($result_nome);

  $nomeUtilizador = $result_array_nome['nome'];

  $thumbnail = $result_array_fotografias['Fotografia_perfil'];
  $thumb2 = $result_array_fotografias['Fotografia_aux'];

  dbDisconnect();
}else{
  header("location:index.php");
}
?>

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>MEO Sudoeste</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav">
<div class="wrapper">
  <header class="main-header">
    <nav class="navbar navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <a href="#" class="navbar-brand"><b>MEO</b>Sudoeste</a>
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
            <i class="fa fa-bars"></i>
          </button>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="#" onClick="ajax('admin_getinfobd.php')">Info DB</a></li>
            <li><a href="#" onClick="ajax('admin_list.php')">List Users</a></li>
            <li><a href="#" onClick="ajax('admin_listemailconfigs.php')">List Emails</a></li>
            <li><a href="#" onClick="ajax('createEvents.html')">Create Event</a></li>
            <li><a href="#" onClick="ajax('feedAdmin.php')">Feed</a></li>
          </ul>
        </div>
        <!-- /.navbar-collapse -->
        <div id="cenas" class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- Messages: style can be found in dropdown.less-->
                <li class="dropdown messages-menu">
                    <!-- Menu toggle button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-envelope-o"></i>
                        <span class="label label-success">4</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have 4 messages</li>
                        <li>
                            <!-- inner menu: contains the messages -->
                            <ul class="menu">
                                <li><!-- start message -->
                                    <a href="#">
                                        <div class="pull-left">
                                            <!-- User Image -->
                                            <img src="<?php echo $thumbnail ?>" class="img-circle" alt="User Image">
                                        </div>
                                        <!-- Message title and timestamp -->
                                        <h4>
                                            Support Team
                                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                        </h4>
                                        <!-- The message -->
                                        <p>Why not buy a new awesome theme?</p>
                                    </a>
                                </li>
                                <!-- end message -->
                            </ul>
                            <!-- /.menu -->
                        </li>
                        <li class="footer"><a href="#">See All Messages</a></li>
                    </ul>
                </li>
                <!-- /.messages-menu -->
                <!-- User Account Menu -->
                <li class="dropdown user user-menu">
                    <!-- Menu Toggle Button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <!-- The user image in the navbar-->
                        <img src="<?php echo $thumbnail ?>" class="user-image" alt="User Image">
                        <!-- hidden-xs hides the username on small devices so only the image appears. -->
                        <span class="hidden-xs"><?php echo $username ?></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- The user image in the menu -->
                        <li class="user-header">
                            <img src="<?php echo $thumb2 ?>" class="img-circle" alt="User Image">
                            <p>
                                <?php echo $nomeUtilizador ?>
                            </p>
                        </li>
                        <!-- Menu Body -->
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" onclick="ajax('otherpages/webservice.php')" class="btn btn-default btn-flat">Profile</a>
                            </div>
                            <div class="pull-right">
                                <a href="logout.php" class="btn btn-default btn-flat">Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
      </div>
      <!-- /.container-fluid -->
    </nav>
  </header>
  <!-- Full Width Column -->
  <div class="content-wrapper">
    <div class="container">
      <!-- Content Header (Page header) -->
      <section class="content-header">
      </section>
      <!-- Main content -->
      <section class="content">
        <div class="row" id="content">
        </div>
        <div class="row" id="editEmail">
        </div>
      <!-- /.box -->
    </section>
      <!-- /.content -->
    </div>
    <!-- /.container -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="container">
      <strong>Copyright &copy; 2016-2017 <a>SMI - Fabio Rolo - Rui Santos</a>.</strong> All rights reserved.
    </div>
    <!-- /.container -->
  </footer>
</div>
<!-- ./wrapper -->
<div id="apagaUser" class="modal modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button onClick="ajax('admin_list.php')" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Erase User</h4>
      </div>
      <div class="modal-body">
        <p id="conteudoModal"></p>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<div id="eventoCriado" class="modal modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button onClick="ajax('createevents.html')" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Event Created</h4>
      </div>
      <div class="modal-body">
        <p id="conteudoModalEvento"></p>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<div id="mailEditado" class="modal modal-warning">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button onClick="ajax('admin_listemailconfigs.php')" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Email Editado</h4>
      </div>
      <div class="modal-body">
        <p id="validarEditMail"></p>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<script>
  function ajax(page){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if(this.readyState == 4 && this.status == 200){
        document.getElementById('content').innerHTML = xhttp.responseText;
        $('#editEmail').html('');
      }
    };
    xhttp.open("GET", page, true);
    xhttp.send();
  }


  function showEditions(page){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if(this.readyState == 4 && this.status == 200){
        document.getElementById('editEmail').innerHTML = xhttp.responseText;
      }
    };
    xhttp.open("GET", page, true);
    xhttp.send();
  }

  function apagarUser(page){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if(this.readyState == 4 && this.status == 200){
        $("#conteudoModal").html(xhttp.responseText);
        $("#apagaUser").modal('show');
      }
    };
    xhttp.open("GET", page, true);
    xhttp.send();
  }

  function validateEvent(page){
    var xhttp = new XMLHttpRequest();
    var nomeEvento = $("#nomeEvento").val();
    var latitude = $("#Latitude").val();
    var longitude = $("#Longitude").val();
    var descricao = $("#descricao").val();
    xhttp.onreadystatechange = function() {
      if(this.readyState == 4 && this.status == 200){
        $("#conteudoModalEvento").html(xhttp.responseText);
        $("#eventoCriado").modal('show');
      }
    };
    xhttp.open("GET", page+"?Latitude="+latitude+"&nomeEvento="+nomeEvento+"&Longitude="+longitude+"&descricao="+descricao, true);
    xhttp.send();

    return false;

  }


  function ola(page,old_dominio){
    var xhttp = new XMLHttpRequest();
    var dominio = $("[name='dominio']").val();
    var smtpServer = $("[name='smtpServer']").val();
    var useSSL = $("[name='useSSL']").val();
    var port = $("[name='port']").val();
    xhttp.onreadystatechange = function() {
      if(this.readyState == 4 && this.status == 200){
        $("#validarEditMail").html(xhttp.responseText);
        $("#mailEditado").modal('show');
        $
      }
    };
    xhttp.open("GET", page+"?dominio="+dominio+"&smtpServer="+smtpServer+"&useSSL="+useSSL+"&port="+port+"&oldDominio="+old_dominio, true);
    xhttp.send();

    return false;
  }

  function modalShow(id){
      var modal = document.getElementById(id+'myModal');
      modal.style.display = "block";
  }

  function span(modal){
      document.getElementById(modal).style.display='none';
  }



</script>
<!-- jQuery 2.2.3 -->
<script src="assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="assets/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="assets/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="assets/dist/js/demo.js"></script>

<style>
* {box-sizing:border-box}
body {font-family: Verdana,sans-serif;margin:0}
.image {
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
}

.image:hover {opacity: 0.7;}

/* The Modal (background) */
.ladom {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (Image) */
.ladom-content {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
}

/* Add Animation - Zoom in the Modal */
.ladom-content {
    -webkit-animation-name: zoom;
    -webkit-animation-duration: 0.6s;
    animation-name: zoom;
    animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
    from {-webkit-transform:scale(0)}
    to {-webkit-transform:scale(1)}
}

@keyframes zoom {
    from {transform:scale(0)}
    to {transform:scale(1)}
}

/* The Close Button */
.close-ladom {
    position: absolute;
    top: 15px;
    right: 35px;
    color: #f1f1f1;
    font-size: 40px;
    font-weight: bold;
    transition: 0.3s;
}

.close-ladom:hover,
.close-ladom:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
    .ladom-content {
        width: 100%;
    }
}

.img-center{
    margin:0 auto;
}
</style>
</body>
</html>

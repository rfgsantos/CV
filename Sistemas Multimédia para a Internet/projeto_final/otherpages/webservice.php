<?php //

session_start();
$username = $_SESSION['username'];
$webservice_DB = "http://localhost:8080/webService/SMIConnectionClienteService?wsdl";
$client = new SoapClient($webservice_DB,array('exceptions'=>true, 'connection_timeout'=>1000));

$params = (array)$client->getParametrosCliente($username);

?>


<div class="col-md-5 col-md-offset-3">
  <div class="box box-primary">
    <div class="box-body box-profile">
      <?php
      echo "<img class='profile-user-img img-responsive img-circle' src=";
      if($_SESSION['tipo']=='Admin'){
        echo $params['item'][2]." alt='User profile picture'>";
      }else{
        echo "../".$params['item'][2]." alt='User profile picture'>";
      }
      echo "<h3 class='profile-username text-center'>\"".$username."\"</h3>";

      echo "<p class='text-muted text-center'> ";
      if($_SESSION['tipo']=='Admin'){
        echo 'Administrator </p>';
      }else{
        echo 'Utilizador </p>';
      }

      echo "<ul class='list-group list-group-unbordered'>";
      echo "<li class='list-group-item'>";
      echo "<b>Nome Completo</b> <a class='pull-right'>\"".$params['item'][0]."\"</a>";
      echo "</li>";
      echo "<li class='list-group-item'>";
      echo "<b>Username</b> <a class='pull-right'>\"".$params['item'][1]."\"</a>";
      echo "</li>";
      echo "<li class='list-group-item'>";
      echo "<b>Email</b> <a class='pull-right'>\"".$params['item'][3]."\"</a>";
      echo "</li>";
      echo "<li class='list-group-item'>";
      echo "<b>Tipo Acesso</b> <a class='pull-right'>\"".$params['item'][4]."\"</a>";
      echo "</li>";
      echo "</ul>";
      ?>
    </div>
    <!-- /.box-body -->
  </div>
</div>

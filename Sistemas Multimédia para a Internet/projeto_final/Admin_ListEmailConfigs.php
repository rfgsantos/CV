<!DOCTYPE html>
<?php
require_once( "../Lib/db.php" );
?>
<div class="col-md-6 col-md-offset-3">
<div class="box">
  <div class="box-header with-border">
    <h3 class="box-title"><b>List Users</b></h3>
  </div>
  <!-- /.box-header -->
  <div class="box-body">

<?php
$query = "SELECT * FROM `email`";
$nome_server = $_SERVER['SERVER_NAME'];
$server_port = 80;
dbConnect(ConfigFile);
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
$result = mysqli_query($GLOBALS['ligacao'], $query);
echo '<table class="table table-bordered">
        <tr>
            <th>Dominio</th>
            <th>Smtp Server</th>
            <th>Porto</th>
            <th>UseSsl</th>
            <th>Editar</th>
        </tr>';
$link = "EditEmailConfig.php?dominio=";

while ($things = mysqli_fetch_array($result)) {
    $dominio = $things["dominio"] ;
    $link_final = $link . $dominio;
        echo "  <tr>
                    <td>" . $dominio . "</td>
                    <td>" . $things["smtp_server"] . "</td>
                    <td>" . $things["port"] . "</td>
                    <td>" . $things["UseSsl"] . "</td>
                    <td><a href='#' onClick='showEditions(\"".$link_final."\")'>Editar</a></td>
                </tr>";
    }



?>
</div>
<!-- /.box-body -->
</div>
</div>

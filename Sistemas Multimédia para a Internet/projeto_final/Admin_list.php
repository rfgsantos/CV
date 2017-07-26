<!DOCTYPE html>
<?php
require_once( "../Lib/lib.php" );
require_once( "../Lib/db.php" );
?>

<div class="box">
  <div class="box-header with-border">
    <h3 class="box-title"><b>List Users</b></h3>
  </div>
  <!-- /.box-header -->
  <div class="box-body">
<?php
$query = "SELECT `tipo`, `nome`, `username`, `password`, `email` FROM `userclient`";

dbConnect(ConfigFile);
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
$result = mysqli_query($GLOBALS['ligacao'], $query);

$nome_server = $_SERVER['SERVER_NAME'];
$server_port = 80;

echo '<table class="table table-bordered">
        <tr>
            <th>Tipo</th>
            <th>Nome</th>
            <th>username</th>
            <th>password</th>
            <th>email</th>
            <th>Apagar</th>
        </tr>';
$link = "ApagarClienteDaBD.php?user=";

while ($things = mysqli_fetch_array($result)) {
    $username = $things["username"] ;
    $link_final = $link . $username;
        echo "  <tr>
                    <td>" . $things["tipo"] . "</td>
                    <td>" . $things["nome"] . "</td>
                    <td>" . $username . "</td>
                    <td>" . $things["password"] . "</td>
                    <td>" . $things["email"] . "</td>
                    <td><a href='#' onClick='apagarUser(\"".$link_final."\")'>Apagar</a></td>
                </tr>";
    }
?>

</div>
<!-- /.box-body -->
</div>

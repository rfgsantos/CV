<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<?php
require_once( "../Lib/db.php" );

loadConfiguration("../Config/.htconfig.xml");
$host = $configDataBase->host;
$port = $configDataBase->port;
$username = $configDataBase->username;
$password = $configDataBase->password;

?>

<div class="box">
  <div class="box-header with-border">
    <h3 class="box-title"><b>Informações DB</b></h3>
  </div>
  <!-- /.box-header -->
  <div class="box-body">
    <table class="table table-bordered">
      <tr>
        <th>Host</th>
        <th>Port</th>
        <th>Username</th>
        <th>Password</th>
      </tr>
      <tr>
        <td><?php echo $host ?></td>
        <td><?php echo $port?></td>
        <td><?php echo $username?></td>
        <td><?php echo $password?></td>
      </tr>
    </table>
  </div>
  <!-- /.box-body -->
</div>

<?php
require_once( "../Lib/db.php" );
session_start();
$username = $_SESSION['username'];
$evento = $_GET['evento'];

dbConnect(ConfigFile);
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
$query = "INSERT INTO `userclient_evento_subscrever` VALUES ('$username','$evento')";
$result_feed = mysqli_query($GLOBALS['ligacao'], $query);
$changed = mysqli_affected_rows($GLOBALS['ligacao']);

if($changed == -1){
  echo "Não Subscreveu no Evento!";
  exit();
}else{
  echo "Subscreveu no Evento!";
  exit();
}
 ?>

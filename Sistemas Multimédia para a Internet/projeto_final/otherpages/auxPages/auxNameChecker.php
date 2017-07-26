<?php
require_once("../../../Lib/db.php");

$username = $_GET['currentUsername'];

dbConnect(ConfigFile);

mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);

$query = "SELECT * FROM `userclient` WHERE username='$username'";

$result = mysqli_query($GLOBALS['ligacao'], $query);

dbDisconnect();

// print_r($result);

if(mysqli_num_rows($result) == 1){
  echo "false";
}else{
  echo "true";
}

 ?>

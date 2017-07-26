<?php

require_once( "../Lib/lib.php" );
require_once( "../Lib/db.php" );

$userName = $_GET["user"];

$query = "DELETE FROM userclient where username ='" . $userName . "'";


dbConnect(ConfigFile);
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
$result = mysqli_query($GLOBALS['ligacao'], $query);
if(!$result){
  echo 'Não foi possivel retirar user da base de dados!';
}else{
  echo 'Apagou o user da basa de dados!';
}


dbDisconnect();

?>

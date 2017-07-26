<?php

require_once( "../Lib/db.php");


$user = $_GET["user"];

$query = "UPDATE userclient SET userclient.valido = 1 where userclient.username='$user'";

dbConnect(ConfigFile);
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
$result = mysqli_query($GLOBALS['ligacao'], $query);


if ($result == TRUE) {
    echo "Conta com o username: " . $user . " validada com sucesso" ;

} else {
    echo "Conta com o username: " . $user . " não foi validada" ;

}
dbDisconnect();

header("location:otherpages/login.php?validou='True'");


?>

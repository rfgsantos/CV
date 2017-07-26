<?php

require_once ('./getEmailConfig.php');
require_once( "../Lib/db.php");
require_once( "../Lib/lib.php");
require_once( "../Lib/lib-mail-v2.php" );
require_once ("./getUserIconConfig.php");
require_once( "../Lib/ImageResize-class.php");

$username = $_POST["username"];
$email = $_POST['email'];

$timeout = 100;

$usernameEmail     = "smi1617no@gmail.com";
$passwordEmail     = "smi201617";

$dominio_src = "gmail";

dbConnect(ConfigFile);
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);

$query = "Select * from email where dominio ='" . $dominio_src . "'";
$result = mysqli_query($GLOBALS['ligacao'], $query);
$dominio_info = mysqli_fetch_array($result);

$dominio_src = $dominio_info['dominio'];
$smtp_server = $dominio_info['smtp_server'];
$port = $dominio_info['port'];
$UseSsl = $dominio_info['UseSsl'];
mysqli_free_result($result);
$fromEmail = $usernameEmail;


$fromName = $_POST["nome"];
$toList = $email;
$subject = "Confirmaçao de registo!";
$nome_server = $_SERVER['SERVER_NAME'];
$server_port = 80;
$message = "Carregue no seguinte link para completar a sua inscrição" . " http://" . $nome_server . ":" . $server_port . "/SMI-examples/projeto_final/ValidarEmail.php?user=" . $username;

$result = sendAuthEmail($smtp_server, $UseSsl, $port, $timeout, $fromEmail, $passwordEmail, $fromEmail, $fromName, $toList, NULL, NULL, $subject, $message, TRUE);

dbDisconnect();

if ($result == TRUE) {
echo "E-mail was delivered to e-mail server.";
} else {
echo "E-mail couldn't be delivered to e-mail server.";
}



?>

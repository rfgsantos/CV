<?php
require_once( "../Lib/db.php");
require_once( "../Lib/lib.php");


$old_dominio = $_GET['oldDominio'];
$dominio = $_GET['dominio'];
$smtpServer = $_GET['smtpServer'];
$useSSL = $_GET['useSSL'];
$port = $_GET['port'];

if (
        $dominio == NULL ||
        $smtpServer == NULL ||
        $useSSL == NULL ||
        $port == NULL ) {
    redirectToLastPage("E-mail with PHP", 5);
} else {
    header('Content-type: text/html; charset=utf-8');

    dbConnect(ConfigFile);

    $db = $GLOBALS['configDataBase']->db;

    mysqli_select_db($GLOBALS['ligacao'], $db);

    $query = "UPDATE `email` SET `dominio`='".$dominio."',`smtp_server`='".$smtpServer."',`port`='".$port."',`UseSsl`='". $useSSL."' WHERE dominio='$old_dominio'";

    mysqli_query($GLOBALS['ligacao'], $query);

    $recordsUpdated = mysqli_affected_rows($GLOBALS['ligacao']);

    if ($recordsUpdated == -1) {
        echo "Email Update has failed!";
    } else {
        echo "Email updated with success.";
    }

    dbDisconnect();
}
?>

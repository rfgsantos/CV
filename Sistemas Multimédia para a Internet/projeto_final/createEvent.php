<?php
session_start();
require_once( "../Lib/lib.php" );
require_once( "../Lib/db.php" );

$nome = $_GET["nomeEvento"];
$descricao = $_GET["descricao"];
$latitude = $_GET["Latitude"];
$longitude = $_GET["Longitude"];

$username = $_SESSION["username"];

dbConnect(ConfigFile);
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
$query = "insert into Evento(nome,latitude,longitude,username,descricao) values('" . $nome . "','" . $latitude . "','" . $longitude . "','" . $username . "','" . $descricao . "')";
$result = mysqli_query($GLOBALS['ligacao'], $query);

// mysqli_free_result($result);

$recordsInserted = mysqli_affected_rows($GLOBALS['ligacao']);

if ($recordsInserted == -1) {
    echo "Information about file could not be insert into the data base.";
} else {
    echo "Information about file was insert into data base.";
}


dbDisconnect();
?>

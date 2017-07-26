<?php



$destino;
$thumbsDestino;
$thumbs_fileAux;

function getUserFilesConfigurations(){
    
    global $destino;
    global $thumbsDestino;
    global $thumbs_fileAux;
    
    $configFile = "../UserFileConfigurations/UserFilesConfig.xml";
    ($aux = simplexml_load_file( $configFile )) or die ("Can't read configuration file.");
    $fileConfig = $aux[0];
    $destino           = $fileConfig->destino_base;
    $thumbsDestino     = $fileConfig->thumb_dst;
    $thumbs_fileAux    = $fileConfig->thumbs_aux;
    
}




?>

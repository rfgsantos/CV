<?php
require_once( "../Lib/lib.php" );
require_once( "../Lib/db.php" );
require_once( "../Lib/lib-coords.php" );
require_once( "../Lib/ImageResize-class.php");
require_once ("getFilesConfigurations.php");
require_once ("./getIDFromFile.php");

session_start();
set_time_limit(0);
$FileWithError = $_FILES["userFile"]["error"];
if ($FileWithError) {
  $errorCode = $FileWithError;
  echo "Erro ao carregar o Ficheiro: " . $errorCode;
  exit();
}

getFilesConfigurations();

$dir_destinoFile = $destino;
$dir_destino_thumbs = $thumbsDestino;
$icon_width = $thumbsWidth;
$icon_height = $thumbHeight;
$evento = $_POST["evento"];
$username = $_SESSION["username"];
$title = $_POST["title"];
$privado = $_POST['privado'];


if($privado == '1'){
  $checked = "True";
}else{
  $checked = "False";
}


//getFileName
$srcName = $_FILES["userFile"]["name"];


if (($srcName <> "none") && ($srcName <> "" )) {
  // Destination for the uploaded file
  $src = $_FILES['userFile']['tmp_name'];
  $dst = $dir_destinoFile .DIRECTORY_SEPARATOR. DIRECTORY_SEPARATOR . $srcName;

  if (copy($src, $dst)) {
    unlink($src);

    // echo "File uploaded with sucess.\n<br>";

    //redimencionar  a imagem para mostrar no feed.
    //colocar a informaçao do icon + dir_imagem na base de dados
    //alterar a base de dados do conteudo
    // print_r($dst);

    $fileInfo = finfo_open(FILEINFO_MIME);
    //retorna informaçao do ficheiro
    $fileInfoData = finfo_file($fileInfo, $dst);

    $fileTypeComponents = explode(";", $fileInfoData);

    $mimeTypeFileUploaded = explode("/", $fileTypeComponents[0]);

    $mimeFileName = $mimeTypeFileUploaded[0];
    $typeFileName = $mimeTypeFileUploaded[1];


    $thumbsDir = $dir_destino_thumbs;



    switch ($mimeFileName) {
      case "image":
      $exif = @exif_read_data($dst, 'IFD0', true);
      // if ($exif === false) {
      //     echo "No exif header data found.<br>\n";
      // }
      $imageFileNameAux = $dst;
      $imageMimeFileName = "image";
      $imageTypeFileName = $typeFileName;

      $thumbsFileNameAux = $thumbsDestino . DIRECTORY_SEPARATOR .DIRECTORY_SEPARATOR. $srcName;
      $resizeObj = new ImageResize($dst);



      $resizeObj->resizeImage(intval($thumbsWidth),intval($thumbHeight), 'crop');
      $resizeObj->saveImage($thumbsFileNameAux, $imageTypeFileName, 100);
      $resizeObj->close();
      break;
      case "video":

      $imageFileNameAux = "images/video_img.png";
      $imageMimeFileName = "image";
      $imageTypeFileName = "png";

      $thumbsFileNameAux = "images/video_imgsmall.png";


      break;

    }
    getId();
    $query = "INSERT INTO `ficheiro`(`nome`, `id`, `username`, `mime`, `tipo`, `nomeEvento`, `conteudo`, `conteudo_thumbs`, `visibilidade`) "
    . "values('".$title."','".$id."','".$username."','".$imageMimeFileName."','".$imageTypeFileName."','".$evento."','".$dst."','".$thumbsFileNameAux."','".$checked."')";

    dbConnect(ConfigFile);
    mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);

    mysqli_query($GLOBALS['ligacao'], $query);

    $recordsInserted = mysqli_affected_rows($GLOBALS['ligacao']);

    if ($recordsInserted == -1) {
      echo "File uploaded unsuccessfully.";
    }
    else {
      echo "File uploaded successfully.";
    }

    dbDisconnect();



  }
}





?>

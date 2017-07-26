<?php

require_once( "../Lib/lib.php" );
require_once( "../Lib/db.php" );
require_once( "../Lib/lib-coords.php" );
require_once( "../Lib/ImageResize-class.php");
require_once ("./getUserIconConfig.php");
require_once ("./getIDFromFile.php");



if (isset($_POST)) {
    //variaveis para a cricao de um registo
    $tipo = $_POST['tipo'];
    $nome = $_POST['nome'];
    $username = $_POST['username'];
    $password = md5($_POST['password']);
    $email = $_POST['email'];
    $valido = 0;

    getUserFilesConfigurations();


    set_time_limit(0);
    $FileWithError = $_FILES["userfile"]["error"];
    if ($FileWithError) {
        $errorCode = $FileWithError;
        echo "Erro ao carregar o Ficheiro";
        exit();
    }


    $dir_destinoFile = $destino;
    $dir_destino_thumbs = $thumbsDestino;
    $dir_destino_thumbs_Aux = $thumbs_fileAux;


//getFileName
    $srcName = $_FILES["userfile"]["name"];


    if (($srcName <> "none") && ($srcName <> "" )) {
        // Destination for the uploaded file
        $src = $_FILES['userfile']['tmp_name'];
        $dst = $dir_destinoFile . DIRECTORY_SEPARATOR . DIRECTORY_SEPARATOR . $srcName;

        if (copy($src, $dst)) {
            unlink($src);

            echo "File uploaded with sucess.\n<br>";

            //redimencionar  a imagem para mostrar no feed.
            //colocar a informaçao do icon + dir_imagem na base de dados
            //alterar a base de dados do conteudo


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
                    if ($exif === false) {
                        echo "No exif header data found.<br>\n";
                    }
                    $imageFileNameAux = $dst;
                    $imageMimeFileName = "image";
                    $imageTypeFileName = $typeFileName;

                    $thumbsFileNameAux = $thumbsDestino . DIRECTORY_SEPARATOR . DIRECTORY_SEPARATOR . $srcName;
                    $resizeObj = new ImageResize($dst);

                     echo $thumbsFileNameAux . "   ";

                    $resizeObj->resizeImage(40, 40, 'crop');
                    $resizeObj->saveImage($thumbsFileNameAux, $imageTypeFileName, 100);
                    $resizeObj->close();



                    $resizeImg = new ImageResize($dst);

                    $thumbsFileNameAuxx = $dir_destino_thumbs_Aux . DIRECTORY_SEPARATOR . DIRECTORY_SEPARATOR . $srcName;
                    echo $thumbsFileNameAuxx;

                    $resizeImg->resizeImage(200, 200, 'crop');
                    $resizeImg->saveImage($thumbsFileNameAuxx, $imageTypeFileName, 100);
                    $resizeImg->close();



                    break;
            }
            $query = "INSERT INTO `userclient`(`tipo`, `nome`, `username`, `password`, `email`, `valido`, `Fotografia_perfil`, `Fotografia_real`, `Fotografia_aux`) VALUES('".$tipo."','" . $nome . "','" . $username
                    . "','" . $password . "','" . $email . "'," . 0 . ",'" . $thumbsFileNameAux .  "','" . $dst ."','" .$thumbsFileNameAuxx ."')";

            echo $query;


            dbConnect(ConfigFile);
            mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);

            mysqli_query($GLOBALS['ligacao'], $query);

            $recordsInserted = mysqli_affected_rows($GLOBALS['ligacao']);

            if ($recordsInserted == -1) {
                echo "Information about file could not be insert into the data base.\n<br>";
            } else {
                echo "Information about file was insert into data base.\n<br>";
                dbDisconnect();
                require_once './sendEmail.php';
                header("location:index.php?validar='True'");
            }
        }
    }



}
?>

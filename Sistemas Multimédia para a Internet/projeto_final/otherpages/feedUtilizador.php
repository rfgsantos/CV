<!DOCTYPE html>
<?php
require_once ("../../Lib/db.php");
session_start();
$tipo;
$visibilidade;
$tipo = $_SESSION['tipo'];

switch($tipo){
case 'Normal':
  $visibilidade = 'False';
  break;
case 'Simpatizante':
  $visibilidade = 'True';
  break;
case 'Admin':
  $visibilidade = 'True';
  break;
case 'Convidado':
  $visibilidade = 'False';
  break;
}

dbConnect(ConfigFile);
//para obter os conteudos baseados na visibilidade permitida do utilizador logged in
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
$query_feed = "SELECT nomeEvento,conteudo,conteudo_thumbs FROM ficheiro WHERE visibilidade='$visibilidade'";
$result_feed = mysqli_query($GLOBALS['ligacao'], $query_feed);


if($tipo !='Convidado'){
  $username = $_SESSION['username'];
  $query_fotografias = "SELECT Fotografia_perfil,Fotografia_aux FROM userclient WHERE username='$username'";
  $result_fotografias = mysqli_query($GLOBALS['ligacao'], $query_fotografias);
  $result_array_fotografias = mysqli_fetch_array($result_fotografias);

  $query_nome = "SELECT nome FROM userclient WHERE username='$username'";
  $result_nome = mysqli_query($GLOBALS['ligacao'], $query_nome);
  $result_array_nome = mysqli_fetch_array($result_nome);

  $nomeUtilizador = $result_array_nome['nome'];

  $thumbnail = "../" . $result_array_fotografias['Fotografia_perfil'];
  $thumb2 = "../" . $result_array_fotografias['Fotografia_aux'];
}


dbDisconnect();

?>

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>MEO Sudoeste</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="../assets/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../assets/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
  folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="../assets/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav">
  <div class="wrapper">

    <header class="main-header">
      <nav class="navbar navbar-static-top">
        <div class="container">
          <div class="navbar-header">
            <a href="<?php if($tipo=='Convidado'){echo "../index.php";}else{echo "feedUtilizador.php";}?>" class="navbar-brand"><b>MEO</b>Sudoeste</a>
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
              <i class="fa fa-bars"></i>
            </button>
          </div>
          <!-- /.navbar-collapse -->
          <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
            <ul class="nav navbar-nav">
              <li><a href="#" onClick="ajax('../uploadfilesphp.php')" id="linkUpload" >Upload Images</a></li>
            </ul>
          </div>

          <div id="cenas" class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
              <li class="dropdown messages-menu">
                <!-- Menu toggle button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success">4</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 4 messages</li>
                  <li>
                    <!-- inner menu: contains the messages -->
                    <ul class="menu">
                      <li><!-- start message -->
                        <a href="#">
                          <div class="pull-left">
                            <!-- User Image -->
                            <img src="<?php echo $thumbnail ?>" class="img-circle" alt="User Image">
                          </div>
                          <!-- Message title and timestamp -->
                          <h4>
                            Support Team
                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                          </h4>
                          <!-- The message -->
                          <p>Why not buy a new awesome theme?</p>
                        </a>
                      </li>
                      <!-- end message -->
                    </ul>
                    <!-- /.menu -->
                  </li>
                  <li class="footer"><a href="#">See All Messages</a></li>
                </ul>
              </li>
              <!-- /.messages-menu -->
              <!-- User Account Menu -->
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <img src="<?php echo $thumbnail ?>" class="user-image" alt="User Image">
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs"><?php echo $username ?></span>
                </a>
                <ul class="dropdown-menu">
                  <!-- The user image in the menu -->
                  <li class="user-header">
                    <img src="<?php echo $thumb2 ?>" class="img-circle" alt="User Image">

                    <p>
                      <?php echo $nomeUtilizador ?>
                    </p>
                  </li>
                  <!-- Menu Body -->

                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" onclick="ajax('webservice.php')" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="../logout.php" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
        <!-- /.container-fluid -->
      </nav>
    </header>
    <!-- Full Width Column -->
    <div class="content-wrapper">
      <div class="container">
        <!-- Content Header (Page header) -->
        <section class="content-header">
        </section>

        <!-- Main content -->
        <section class="content">

          <div id="content" class="row">
            <div class="col-md-6 col-md-offset-3">
              <div class="box box-primary">

                <div class="box-body">

                  <h3 class="box-title text-center" >Quem somos</h3>
                  <div class="box-body">Bacon ipsum dolor amet brisket turducken picanha, burgdoggen cow doner t-bone cupim sausage pig tri-tip swine alcatra tail. Ham hock sirloin pork chop bresaola t-bone chuck. Ham ground round chicken sausage jowl, shank jerky kielbasa strip steak ham hock venison pig corned beef pastrami. Shank swine pork shoulder pig. Salami bacon porchetta chicken drumstick, turducken cow fatback flank shank.
                    <br>
                    <br>
                    Burgdoggen tongue porchetta brisket capicola corned beef swine ribeye ground round beef ribs short ribs andouille pork belly. Pork belly bacon venison andouille chicken. Burgdoggen capicola shank short loin corned beef alcatra strip steak ham bresaola pastrami sausage. Short ribs frankfurter short loin doner ground round. Brisket shank biltong tongue turducken porchetta alcatra shankle. Rump sirloin picanha filet mignon bresaola, fatback jowl pig swine shankle pork belly burgdoggen ground round. Fatback venison picanha, shoulder chicken chuck sausage jerky.
                    <br>
                    <br>
                    Shoulder brisket frankfurter cow ham hock tongue pork belly ribeye shank tri-tip tenderloin kevin rump boudin. Biltong beef turkey frankfurter. Cupim ball tip pork belly drumstick turkey strip steak. Frankfurter venison fatback, bresaola tenderloin chicken tri-tip short loin burgdoggen jerky capicola rump. Sausage tenderloin tri-tip burgdoggen bacon kielbasa salami ball tip pancetta spare ribs cupim jerky. Filet mignon brisket shankle, chicken ground round ribeye kevin. Cow spare ribs biltong pork pancetta cupim frankfurter.
                    <br>
                    <br>
                    Salami jowl tail, pork ribeye doner meatloaf swine picanha. Shoulder tri-tip pork belly bacon alcatra fatback. Cow burgdoggen venison, sirloin short loin turducken capicola frankfurter pork chop alcatra. T-bone capicola prosciutto swine meatloaf pork chop fatback. Corned beef beef ribs tail, spare ribs cow pork loin bacon meatloaf boudin ham.
                    <br>
                    <br>
                    Pig beef landjaeger ground round kielbasa ham doner bacon pastrami jerky. Capicola salami sirloin prosciutto brisket, drumstick landjaeger spare ribs porchetta tongue kielbasa jerky biltong ham rump. Burgdoggen short loin beef ribs, ball tip tail ham hock meatball bacon corned beef pastrami. Ball tip turkey sausage hamburger ham hock kevin tongue salami filet mignon chicken porchetta. Andouille meatloaf turkey, pork loin beef alcatra bresaola hamburger.</div>
                  </div>
                  </div>
                  <!--para ser printed com echos do PHP-->
                  <?php
                  $id =0;
                  echo '<script src="../../jwplayer-7.12.0/jwplayer.js"></script>
                        <script>jwplayer.key = "DDU+BdvttVhJ/R5VAzbbyjkIGv+8PDQo3wsneQ==";</script>';
                  while($result_array_feed = mysqli_fetch_array($result_feed)){
                    $id +=1;
                    $modal = $id."myModal";
                    $conteudoFeed = "../".$result_array_feed['conteudo'];
                    $nomeEvento = $result_array_feed['nomeEvento'];
                    $aux =  substr($conteudoFeed, -3);

                    if($aux == "mp4"){
                      $conteudo = "../images/video_img.png";
                      echo '<div class="box box-primary"> <div class="box-body">';
                      echo '<h4 class="box-title text-center">\''.$nomeEvento.'\'</h4>';
                      echo '<button style="margin-bottom:5px" class="btn btn-primary center-block">Subscrever Evento</button>';
                      echo '<img  onclick="modalShow(\''.$id.'\')" class="image center-block" src="'.$conteudo.'" width="300" height="200">';
                      echo '<div id="'.$modal.'" class="ladom">';
                      echo '<span class="close-ladom" onClick="modalclose(\''.$modal.'\',\''."True".'\')">&times;</span>';
                      echo "<div id='myElement' style='margin-left:30%;' class='ladom-content'>Loading the player ...</div>";

                      echo "<script type='text/javascript'>
                      jwplayer('myElement').setup({
                        autostart: 'false',
                        author: 'Rolo e Rui',
                        height: 480,
                        width: 640,
                        playlist: [{
                          title: 'Ficheiro MP4',
                          description: 'Ficheiro de vídeo no formato mp4',
                          image:'".$conteudo."',
                          sources: [
                            {
                              file:'../File_dir/out.mp4' ,
                              type: 'mp4'
                            }
                          ]
                        }]
                      });
                      </script>";
                      echo '</div></div></div>';
                    }else{

                      echo '<div class="box box-primary"><div class="box-body">';
                      echo '<h4 class="box-title text-center">Nome Evento: \''.$nomeEvento.'\'</h4>';
                      echo '<button onClick="subscribeEvent(\''.$nomeEvento.'\')" style="margin-bottom:5px" class="btn btn-primary center-block">Subscrever Evento</button>';
                      echo '<img  onclick="modalShow(\''.$id.'\')" class="image center-block" src="'.$conteudoFeed.'" width="300" height="200">';
                      echo '<div id="'.$modal.'" class="ladom">';
                      echo '<span class="close-ladom" onClick="modalclose(\''.$modal.'\')">&times;</span>';
                      echo '<img class="ladom-content" id="'.$id.'" src="'.$conteudoFeed.'">';
                      echo '</div></div></div>';
                    }
                  }
                    ?>
                </div>
              </div>

              <!-- /.box -->
            </section>
            <!-- /.content -->
          </div>
          <!-- /.container -->
        </div>
        <!-- /.content-wrapper -->
        <footer class="main-footer">
          <div class="container">

            <strong>Copyright &copy; 2016-2017 <a>SMI -Fabio Rolo - Rui Santos</a>.</strong> All rights reserved.
          </div>
          <!-- /.container -->
        </footer>
      </div>
      <!-- ./wrapper -->
      <div id="fotoModal" class="modal modal-warning">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button onclick="closeModal('fotoModal')" type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Publicação Foto</h4>
              </div>
              <div class="modal-body">
                <p id="fotoModalConteudo"></p>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>

        <div id="subscribe" class="modal modal-warning">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button onclick="closeModal('subscribe')" type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title">Subscrição</h4>
                </div>
                <div class="modal-body">
                  <p id="subscribeConteudo"></p>
                </div>
              </div>
              <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
          </div>

        <!-- jQuery 2.2.3 -->
        <script src="../assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- Bootstrap 3.3.6 -->
        <script src="../assets/bootstrap/js/bootstrap.min.js"></script>
        <!-- SlimScroll -->
        <script src="../assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="../assets/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="../assets/dist/js/app.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="../assets/dist/js/demo.js"></script>

        <script>
        <?php
        if($tipo == 'Convidado'){
          echo "document.getElementById('cenas').style = 'display:none';";
          echo "document.getElementById('linkUpload').style = 'display:none';";
        }
        ?>

        function ajax(page){
          var xhttp = new XMLHttpRequest();
          xhttp.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200){
              document.getElementById('content').innerHTML = xhttp.responseText;
              $('#editEmail').html('');
            }
          };
          xhttp.open("GET", page, true);
          xhttp.send();
        }

        function subscribeEvent(evento){
          var xhttp = new XMLHttpRequest();
          xhttp.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200){
              document.getElementById('subscribeConteudo').innerHTML = xhttp.responseText;
              $('#subscribe').modal('show');
            }
          };
          xhttp.open("GET","../subscreveEvento.php?evento="+evento, true);
          xhttp.send();

        }

        function uploadpicture(page){
          var data = new FormData();
          data.append("privado",$("input:checked").length)
          data.append("description",$("[name='description']").val());
          data.append("title",$("[name='title']").val());
          data.append("evento",$("[name='evento']").val());
          data.append("userFile",$("[name='userFile']")[0].files[0]);

          var xhttp = new XMLHttpRequest();
          xhttp.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200){
              $('#fotoModalConteudo').html(xhttp.responseText);
              $("#fotoModal").show('show');

            }
          };
          xhttp.open("POST", page, true);
          xhttp.send(data);

          return false;
        }

        function modalShow(id){
          var modal = document.getElementById(id+'myModal');
          modal.style.display = "block";
        }

        function modalclose(modal,video='False'){
          document.getElementById(modal).style.display='none';
          if(video == "True"){
            jwplayer('myElement').stop();
          }
        }

        function closeModal(param){
          document.getElementById(param).style.display = 'none';
        }
        </script>

        <style>
        * {box-sizing:border-box}
        body {font-family: Verdana,sans-serif;margin:0}
        /* Style the Image Used to Trigger the Modal */
        .image {
          border-radius: 5px;
          cursor: pointer;
          transition: 0.3s;
        }

        .image:hover {opacity: 0.7;}

        /* The Modal (background) */
        .ladom {
          display: none; /* Hidden by default */
          position: fixed; /* Stay in place */
          z-index: 1; /* Sit on top */
          padding-top: 100px; /* Location of the box */
          left: 0;
          top: 0;
          width: 100%; /* Full width */
          height: 100%; /* Full height */
          overflow: auto; /* Enable scroll if needed */
          background-color: rgb(0,0,0); /* Fallback color */
          background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
        }

        /* Modal Content (Image) */
        .ladom-content {
          margin: auto;
          display: block;
          width: 80%;
          max-width: 700px;
        }

        /* Add Animation - Zoom in the Modal */
        .ladom-content {
          -webkit-animation-name: zoom;
          -webkit-animation-duration: 0.6s;
          animation-name: zoom;
          animation-duration: 0.6s;
        }

        @-webkit-keyframes zoom {
          from {-webkit-transform:scale(0)}
          to {-webkit-transform:scale(1)}
        }

        @keyframes zoom {
          from {transform:scale(0)}
          to {transform:scale(1)}
        }

        /* The Close Button */
        .close-ladom {
          position: absolute;
          top: 15px;
          right: 35px;
          color: #f1f1f1;
          font-size: 40px;
          font-weight: bold;
          transition: 0.3s;
        }

        .close-ladom:hover,
        .close-ladom:focus {
          color: #bbb;
          text-decoration: none;
          cursor: pointer;
        }

        /* 100% Image Width on Smaller Screens */
        @media only screen and (max-width: 700px){
          .ladom-content {
            width: 100%;
          }
        }

        .img-center{
          margin:0 auto;
        }

        </style>

      </body>
      </html>

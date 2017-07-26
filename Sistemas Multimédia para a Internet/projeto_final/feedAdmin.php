<?php
require_once("../Lib/db.php");


dbConnect(ConfigFile);
//para obter os conteudos baseados na visibilidade permitida do utilizador logged in
mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);
$query_feed = "SELECT nomeEvento,conteudo,conteudo_thumbs FROM ficheiro";
$result_feed = mysqli_query($GLOBALS['ligacao'], $query_feed);

?>
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
        <!--para ser printed com echos do PHP-->
        <?php
            $id =0;
            echo '<script src="../jwplayer-7.12.0/jwplayer.js"></script>
                  <script>jwplayer.key = "DDU+BdvttVhJ/R5VAzbbyjkIGv+8PDQo3wsneQ==";</script>';
            while($result_array_feed = mysqli_fetch_array($result_feed)){
                $id +=1;
                $modal = $id."myModal";
                $conteudoFeed = $result_array_feed['conteudo'];
                $nomeEvento = $result_array_feed['nomeEvento'];
                $aux =  substr($conteudoFeed, -3);

                if($aux == "mp4"){
                  $conteudo = "images/video_img.png";
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
                          file:'/File_dir/out.mp4' ,
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

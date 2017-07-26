<?php
require_once( "../Lib/lib.php" );
require_once( "../Lib/db.php" );
require_once( "../Lib/lib-coords.php" );
require_once( "../Lib/ImageResize-class.php");
require_once ("getFilesConfigurations.php");
require_once ("./getIDFromFile.php");
?>

<div class="col-md-6 col-md-offset-3">
  <div class="login-box-body">
    <form enctype="multipart/form-data" onsubmit="return uploadpicture('../uploadfiles.php')" id="file" method="POST" name="FormUpload">
    <div class="form-group ">
      <input class="form-control" type="text" name="title" placeHolder="Titulo"><br>
    </div>
    <div class="form-group ">
      <label>Descreva o Evento:</label>
      <textarea class="form-control"name="description" rows="4" cols="50" placeholder="Description"></textarea>
    </div>

    <div class="form-group has-feedback">
      <?php
      echo "<select class='form-control' name='evento'>";
      $query = "Select nome from Evento";
      dbConnect(ConfigFile);
      mysqli_select_db($GLOBALS['ligacao'], $GLOBALS['configDataBase']->db);

      $result = mysqli_query($GLOBALS['ligacao'], $query);
      echo"<option>Escolhe Evento</option>";
      while ($things = mysqli_fetch_array($result)) {
        $value = $things["nome"];
        echo "<option value= '" . $value . "'>" . $value . "</option>";
      }


      echo "</select>";
      ?>
    </div>
    <div class="form-group">
      <div class="checkbox">
      <label><input type ="checkbox" name ="privado">Conteudo Privado?</label>
    </div>
    </div>
    <div class="form-group ">
      <input class="form-control" type="hidden" name="MAX_FILE_SIZE" value="52428800">
    </div>
    <div class="form-group">
      <input type="file" name="userFile" size="64">
    </div>
    <div class="row">
    <div class="col-xs-12">
      <input class="btn btn-primary btn-block btn-flat" type="submit" name="Submit" value="Upload file">
    </div>
  </div>
  </form>

</div>
</div>

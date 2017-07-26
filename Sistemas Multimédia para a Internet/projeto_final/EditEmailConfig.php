<div class="col-md-6 col-md-offset-3">
  <div class="login-box-body">
    <div class="box">
      <div class="box-header with-border">
        <h3 class="box-title"><b>List Users</b></h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">

<?php

require_once( "../Lib/db.php");
require_once( "../Lib/lib.php");

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $args = $_POST;
} else {
    $args = $_GET;
}

$dominio = $args["dominio"];

if ($dominio == NULL) {
    redirectToLastPage("E-mail with PHP", 5);
} else {
    header('Content-type: text/html; charset=utf-8');

    dbConnect(ConfigFile);

    $old_dominio = $dominio;
    $db = $GLOBALS['configDataBase']->db;

    mysqli_select_db($GLOBALS['ligacao'], $db);

    $query = "SELECT * FROM `email` WHERE dominio = '".$dominio."'";

    $result = mysqli_query($GLOBALS['ligacao'], $query);
    $link = "EmailConfigUpdate.php";
    echo "<form onSubmit='return ola(\"".$link."\",\"".$old_dominio."\")' method='post'>";
    echo "  <table class='table table-bordered'";
    echo "    <tr>\n";
    echo "      <th>Fields</th>\n";
    echo "      <th>Old Values</th>\n";
    echo "      <th>New value</th>\n";
    echo "    </tr>\n";

    $accountData = mysqli_fetch_array($result);

    $smtpServer = $accountData['smtp_server'];
    $useSSL = $accountData['UseSsl'];
    $port = $accountData['port'];
    echo "    <tr>\n";
    echo "      <td>Dominio</td>\n";
    echo "      <td>$dominio</td>\n";
    echo "      <td><div class='form-group'><input class='form-control' type=\"text\" size=32 name=\"dominio\" value=\"$dominio\"></div></td>\n";
    echo "    </tr>\n";

    echo "    <tr>\n";
    echo "      <td>SMTP Server</td>\n";
    echo "      <td>$smtpServer</td>\n";
    echo "      <td><div class='form-group'><input class='form-control' type=\"text\" size=32 name=\"smtpServer\" value=\"$smtpServer\"></div></td>\n";
    echo "    </tr>\n";


    echo "    <tr>\n";
    echo "      <td>Use SSL</td>\n";
    if ($useSSL == 0) {
        echo "      <td>No</td>\n";
        echo "      <td><div class='form-group'><select class='form-control' name=\"useSSL\" ><option value=\"0\">No</option><option value=\"1\">Yes</option></select></div></td>\n";
    } else {
        echo "      <td>Yes</td>\n";
        echo "      <td><select class='form-control' name=\"useSSL\" ><option value=\"1\">Yes</option><option value=\"0\">No</option></select></td>\n";
    }
    echo "    </tr>\n";

    echo "    <tr>\n";
    echo "      <td>Port</td>\n";
    echo "      <td>$port</td>\n";
    echo "      <td><div class='form-group'><input class='form-control' type=\"text\" size=32 name=\"port\" value=\"$port\"></div></td>\n";
    echo "    </tr>\n";

    echo "  </table>\n";


    echo "  <div class='col-xs-12'><input class='btn btn-primary btn-block btn-flat' type=\"submit\" name=\"update\"></div>";

    dbDisconnect();
}
?>

</div>
</div>

</div>
</div>

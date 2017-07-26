<?php
// echo "<script>window.location.href='index.php'</script>";
session_start();
session_destroy();
session_unset();
header("location:index.php");
 ?>

<?php

  $ligacao;  
  $configDataBase;
  $configDataFiles;
  $usernameEmail;
  $passwordEmail;

function getEmailConfigurations(){
    
  
    global $configDataBase;
    global $configDataFiles;
    global $usernameEmail;
    global $passwordEmail;
    
    $configFile = "../account_smi_mail/acc.xml";
    
    if ( ($configDataBase==NULL)) {
        ($aux = simplexml_load_file( $configFile )) or die ("Can't read configuration file.");
        $configDataBase = $aux[0];
        $usernameEmail     = strval( $configDataBase->user );
        $passwordEmail     = strval( $configDataBase->password );
        
  	}
     
        
  
}

function getEmailDomain(){
    global $usernameEmail;
    
    $array= explode("@", $usernameEmail);
    $array = explode(".",$array[1]);
    return $array[0];
}

?>
function uploadFile(){
    var fileName = document.getElementsByName("userFile");
    var args = "file=" + filename;
    xmlHttp = GetXmlHttpObject();
    xmlHttp.open("GET", "getCounties.php?"+args, true);
    
        
}


// Java Document

var lastDate = document.lastModified;

if(lastDate==0){
var pageData = "Last modified: Unknown<BR>&copy;  2008 JoeCo";
}
else{
var pageData = "Last modified: " + lastDate + " <BR>&copy;  2008 JoeCo";
}


package hospital.sys;

public class Header {
    public static String header(Boolean hasLoginOption){
        return "<!DOCTYPE html>"
                +"<html lang=\"en\">"
                +"<head>"
                +"<meta charset=\"utf-8\">"
                +"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
                +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
                +"<link href=\"images/logo.png\" rel=\"icon\" />\n"
                +"<title>Online Hospital Management System</title>"
                +"<link href=\"./css/bootstrap.min.css\" rel=\"stylesheet\">"
                +"<link href=\"./css/style.css\" rel=\"stylesheet\">"
                +"<script src=\"./js/jquery.js\"></script>"
                +"</head>"
                +"<body>"
                +"<div class=\"container-fluid\">"
                +"<div class=\"row navbar-fixed-top\">"
                +"<nav class=\"navbar navbar-default header\">"
                +"<div class=\"container-fluid\">"
                +"<div class=\"navbar-header\">"
                +"<a class=\"navbar-brand logo\" href=\"#\">"
                +"<img alt=\"Brand\" src=\"images/logo.png\">"
                +"</a>"
                +"<div class=\"navbar-text title\">"
                +"<p>Hospital Management System"
                +"<p>"
                +"</div>"
                +"</div>"
                +"</div>"
                +"</nav>"
                + (hasLoginOption ?
                "<a href=\"./login\" style=\"text-align:Center;font-weight:bold;font-size:110%;padding: 0 2%;color:rgb(253, 250, 250)\">LOGIN</a>" :
                "")
                +"</div>";
    }
}

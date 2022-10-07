package com.hospital.actions;

import com.hospital.listeners.SessionListener;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/home")
public class HomeAction extends HttpServlet {

    ServletConfig config = null;
    public static String user_email = "";
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        user_email = (String) session.getAttribute("username");
        res.sendRedirect("./department");
    }
    public static String adminDashboardHeader(String email){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link href=\"images/logo.png\" rel=\"icon\" />\n" +
                "    <title>Online Hospital Management System</title>\n" +
                "    <!-- Bootstrap -->\n" +
                "    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "    <link href=\"css/style.css\" rel=\"stylesheet\">\n" +
                "    <script src=\"js/jquery.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "        $(document).ready(function () {\n" +
                "            $('#doctorlist').show();\n" +
                "            $('.doctor li:first-child a').addClass('active');\n" +
                "            $('.doctor li a').click(function (e) {\n" +
                "                var tabDiv = this.hash;\n" +
                "                $('.doctor li a').removeClass('active');\n" +
                "                $(this).addClass('.active');\n" +
                "                $('.switchgroup').hide();\n" +
                "                $(tabDiv).fadeIn();\n" +
                "                e.preventDefault();\n" +
                "            });\n" +
                "        });\n" +
                "    </script>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container-fluid\">\n" +
                "        <!--- Header Start -------->\n" +
                "        <div class=\"row header\">\n" +
                "            <div class=\"col-md-10\">\n" +
                "                <div class=\"navbar-header\">\n" +
                "                    <a class=\"navbar-brand logo\" href=\"#\">\n" +
                "                        <img alt=\"Logo\" src=\"images/logo.png\">\n" +
                "                    </a>\n" +
                "                    <div class=\"navbar-text title\">\n" +
                "                        <p>Hospital Management System\n</p>" +
                "                        <p style=\"font-family: Arial; font-size: 15px; margin:0%;\" >Current Logged In Users:"+ SessionListener.CurrentLoggedInUsers+"</p>" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"col-md-2 \">\n" +
                "                <ul class=\"nav nav-pills \">\n" +
                "                    <li class=\"dropdown dmenu\">\n" +
                "                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\"\n" +
                "                            aria-expanded=\"false\">\n" + email +"<span class=\"caret\"></span>\n" +
                "                        </a>\n" +
                "                        <ul class=\"dropdown-menu \">\n" +
                "                            <li><a href=\"./profile\">Change Profile</a></li>\n" +
                "                            <li role=\"separator\" class=\"divider\"></li>\n" +
                "                            <li><a href=\"./logout\">Logout</a></li>\n" +
                "                        </ul>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <!--- Header Ends --------->\n";
    }
    public static String dashboardFooter(){
        return "        <script src=\"js/bootstrap.min.js\"></script>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}

package com.hospital.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class HeaderTag extends SimpleTagSupport {
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\">\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "        <link href=\"images/logo.png\" rel=\"icon\" />\n" +
                "        <title>BOTIEN HOP</title>\n" +
                "        <link href=\"./css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "        <link href=\"./css/style.css\" rel=\"stylesheet\">\n" +
                "        <script src=\"./js/jquery.js\"></script>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div class=\"container-fluid\">\n" +
                "            <div class=\"row navbar-fixed-top\">\n" +
                "                <nav class=\"navbar navbar-default header\">\n" +
                "                    <div class=\"container-fluid\">\n" +
                "                        <div class=\"navbar-header\">\n" +
                "                            <a class=\"navbar-brand logo\" href=\"#\">\n" +
                "                                <img alt=\"Brand\" src=\"images/logo.png\">\n" +
                "                            </a>\n" +
                "                            <div class=\"navbar-text title\">\n" +
                "                                <p>Hospital Management System</p>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </nav>\n" +
                "                <%= request.getServletPath().equals(\"/register.jsp\") ?\n" +
                "                \"<a href=\\\"./login.jsp\\\" style=\\\"text-align:Center;font-weight:bold;font-size:110%;padding: 0 2%;color:rgb(253, 250, 250)\\\">LOGIN</a>\" :\n" +
                "                \"\"%>\n" +
                "            </div>");
    }
}
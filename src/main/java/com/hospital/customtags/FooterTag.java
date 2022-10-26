package com.hospital.customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class FooterTag extends SimpleTagSupport {
    StringWriter stringWriter =  new StringWriter();

    public void doTag() throws JspException, IOException {
        getJspBody().invoke(stringWriter);
        JspWriter out = getJspContext().getOut();
        out.println("            <div class=\"row footer navbar-fixed-bottom\">\n" +
                "                <div class=\"col-md-12\">\n" +
                "                    <div>Botien Technologies</div>\n" +
                "                    <p>Copyrights © 2022. All rights reserved. </p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <script src=\"js/bootstrap.min.js\"></script>\n" +
                "    </body>\n" +
                "</html>");
    }
}

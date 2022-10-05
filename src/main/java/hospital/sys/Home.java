package hospital.sys;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Home extends HttpServlet {

    ServletConfig config = null;

    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        res.getWriter().print(adminDashboard(req.getParameter("email")));
        System.out.println(session.isNew());
    }

    public String adminDashboard(String email){
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
                "                        <img alt=\"Brand\" src=\"images/logo.png\">\n" +
                "                    </a>\n" +
                "                    <div class=\"navbar-text title\">\n" +
                "                        <p>Hospital Management System\n" +
                "                        <p>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"col-md-2 \">\n" +
                "                <ul class=\"nav nav-pills \">\n" +
                "                    <li class=\"dropdown dmenu\">\n" +
                "                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\"\n" +
                "                            aria-expanded=\"false\">\n" +
                email +"<span class=\"caret\"></span>\n" +
                "                        </a>\n" +
                "                        <ul class=\"dropdown-menu \">\n" +
                "                            <li><a href=\"profile.jsp\">Change Profile</a></li>\n" +
                "                            <li role=\"separator\" class=\"divider\"></li>\n" +
                "                            <li><a href=\"logout.jsp\">Logout</a></li>\n" +
                "                        </ul>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <!--- Header Ends --------->\n" +
                "        <div class=\"row\">\n" +
                "            <!----- Menu Area Start ------>\n" +
                "            <div class=\"col-md-2 menucontent\">\n" +
                "                <a href=\"#\">\n" +
                "                    <h1>Dashboard</h1>\n" +
                "                </a>\n" +
                "                <ul class=\"nav nav-pills nav-stacked\">\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Department</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Doctors</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Patients</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Nurse</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Room</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Pathology</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Blood Donor</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Billing</a></li>\n" +
                "                    <li role=\"presentation\"><a href=\"#\">Search</a></li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <!---- Menu Ares Ends  -------->\n" +
                "            <!-------   Content Area start  --------->\n" +
                "            <div class=\"col-md-10 maincontent\">\n" +
                "                <!-----------  Content Menu Tab Start   ------------>\n" +
                "                <div class=\"panel panel-default contentinside\">\n" +
                "                    <div class=\"panel-heading\">Manage Department</div>\n" +
                "                    <!----------------   Panel Body Start   --------------->\n" +
                "                    <div class=\"panel-body\">\n" +
                "                        <ul class=\"nav nav-tabs doctor\">\n" +
                "                            <li role=\"presentation\"><a href=\"#doctorlist\">Department List</a></li>\n" +
                "                            <li role=\"presentation\"><a href=\"#adddoctor\">Add Department</a></li>\n" +
                "                        </ul>\n" +
                "                        <!----------------   Display Department Data List start   --------------->\n" +
                "\n" +
                "                        <div id=\"doctorlist\" class=\"switchgroup\">\n" +
                "                            <table class=\"table table-bordered table-hover\">\n" +
                "                                <tr class=\"active\">\n" +
                "                                    <td>Department ID</td>\n" +
                "                                    <td>Department Name</td>\n" +
                "                                    <td>Department Description</td>\n" +
                "                                    <td>Options</td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td>\n" +
                "                                        23\n" +
                "                                    </td>\n" +
                "                                    <td>\n" +
                "                                        Therapy Department\n" +
                "                                    </td>\n" +
                "                                    <td>\n" +
                "                                        Example Description\n" +
                "                                    </td>\n" +
                "                                    <td>\n" +
                "                                        <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\"\n" +
                "                                            data-target=\"#myModal<%=deptId%>\"><span class=\"glyphicon glyphicon-wrench\"\n" +
                "                                                aria-hidden=\"true\"></span></button>\n" +
                "                                        <a href=\"add delete validation here\" class=\"btn btn-danger\"\n" +
                "                                            onclick=\"return confirmDelete()\"><span class=\"glyphicon glyphicon-trash\"\n" +
                "                                                aria-hidden=\"true\"></span></a>\n" +
                "\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </div>\n" +
                "                        <!----------------   Display Department Data List ends   --------------->\n" +
                "\n" +
                "                        <!------ Edit Department Modal Start ---------->\n" +
                "                        <div class=\"modal fade\" id=\"myModal<%=deptId%>\" tabindex=\"-1\" role=\"dialog\"\n" +
                "                            aria-labelledby=\"myModalLabel\">\n" +
                "                            <div class=\"modal-dialog\" role=\"document\">\n" +
                "                                <div class=\"modal-content\">\n" +
                "                                    <div class=\"modal-header\">\n" +
                "                                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\"\n" +
                "                                            aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n" +
                "                                        <h4 class=\"modal-title\" id=\"myModalLabel\">Edit Department Information\n" +
                "                                        </h4>\n" +
                "                                    </div>\n" +
                "\n" +
                "                                    <div class=\"modal-body\">\n" +
                "                                        <div class=\"panel panel-default\">\n" +
                "                                            <div class=\"panel-body\">\n" +
                "                                                <form class=\"form-horizontal\" action=\"edit_dept_validation.jsp\">\n" +
                "\n" +
                "                                                    <div class=\"form-group\">\n" +
                "                                                        <label class=\"col-sm-4 control-label\">Department\n" +
                "                                                            ID</label>\n" +
                "                                                        <div class=\"col-sm-4\">\n" +
                "                                                            <input type=\"number\" class=\"form-control\" name=\"deptId\"\n" +
                "                                                                value=\"<%=deptId%>\" readonly=\"readonly\">\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "\n" +
                "                                                    <div class=\"form-group\">\n" +
                "                                                        <label class=\"col-sm-4 control-label\">Department\n" +
                "                                                            Name</label>\n" +
                "                                                        <div class=\"col-sm-4\">\n" +
                "                                                            <input type=\"text\" class=\"form-control\" name=\"deptName\"\n" +
                "                                                                value=\"<%=deptName%>\">\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "\n" +
                "                                                    <div class=\"form-group\">\n" +
                "                                                        <label class=\"col-sm-4 control-label\">Department\n" +
                "                                                            Description</label>\n" +
                "                                                        <div class=\"col-sm-4\">\n" +
                "                                                            <input type=\"text\" class=\"form-control\" name=\"deptDesc\"\n" +
                "                                                                value=\"<%=deptDesc%>\">\n" +
                "                                                        </div>\n" +
                "                                                    </div>\n" +
                "\n" +
                "                                                    <div class=\"modal-footer\">\n" +
                "                                                        <button type=\"button\" class=\"btn btn-default\"\n" +
                "                                                            data-dismiss=\"modal\">Close</button>\n" +
                "                                                        <input type=\"submit\" class=\"btn btn-primary\"\n" +
                "                                                            value=\"Update\"></button>\n" +
                "                                                    </div>\n" +
                "                                                </form>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!----------------   Modal ends here  --------------->\n" +
                "\n" +
                "                        <!----------------   Add Department Start   --------------->\n" +
                "                        <div id=\"adddoctor\" class=\"switchgroup\">\n" +
                "                            <div class=\"panel panel-default\">\n" +
                "                                <div class=\"panel-body\">\n" +
                "                                    <form class=\"form-horizontal\" action=\"add_dept_validation.jsp\">\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <label class=\"col-sm-4 control-label\">Department ID</label>\n" +
                "                                            <div class=\"col-sm-4\">\n" +
                "                                                <input type=\"number\" class=\"form-control\" name=\"deptId\"\n" +
                "                                                    placeholder=\"ID Auto Generated\" readonly>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <label class=\"col-sm-4 control-label\">Department Name</label>\n" +
                "                                            <div class=\"col-sm-4\">\n" +
                "                                                <input type=\"text\" class=\"form-control\" name=\"deptName\"\n" +
                "                                                    placeholder=\"Enter Department Name\">\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <label class=\"col-sm-4 control-label\">Department\n" +
                "                                                Description</label>\n" +
                "                                            <div class=\"col-sm-4\">\n" +
                "                                                <input type=\"text\" class=\"form-control\" name=\"deptDesc\"\n" +
                "                                                    placeholder=\"Enter Department Description here...\">\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "\n" +
                "\n" +
                "                                        <div class=\"form-group\">\n" +
                "                                            <div class=\"col-sm-offset-4 col-sm-10\">\n" +
                "                                                <button type=\"submit\" class=\"btn btn-primary\">Add\n" +
                "                                                    Department</button>\n" +
                "                                            </div>\n" +
                "                                        </div>\n" +
                "                                    </form>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!----------------   Add Department Ends   --------------->\n" +
                "                    </div>\n" +
                "                    <!----------------   Panel Body Ends   --------------->\n" +
                "                </div>\n" +
                "                <!-----------  Content Menu Tab Ends   ------------>\n" +
                "            </div>\n" +
                "            <!-------   Content Area Ends  --------->\n" +
                "        </div>\n" +
                "        <script src=\"js/bootstrap.min.js\"></script>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}

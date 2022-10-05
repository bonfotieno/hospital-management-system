package hospital.sys;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Login extends HttpServlet {
    ServletConfig config = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print(this.login(null));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        String userRole = req.getParameter("userrole");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (userRole.equalsIgnoreCase("Select User")) {
            wr.print(this.login("You must select a user type<br/>"));
            return;
        }

        if (password == null || password.equalsIgnoreCase("")) {
            wr.print(this.login("Password is required<br/>"));
            return;
        }

        if (!email.equals(config.getInitParameter("username")) && !password.equals(config.getInitParameter("password"))) {
            wr.print(this.login("Invalid username & password combination<br/>"));
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("loggedInTime", "Logged In Time:" + new Date());
        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
        dispatcher.forward(req, resp);

    }

    public String login(String actionError) {
        return Header.header(false) + "<div class=\"row \">"
                + "<div class=\"col-md-12\">"
                + "<br /><br /><br /><br /><br /><br /><br /><br />"
                + "<div class=\"panel panel-default login\">"
                + "<div class=\"panel-heading logintitle\">Login</div>"
                + "<div class=\"panel-body\">"
                + "<form class=\"form-horizontal center-block\" role=\"form\" action=\"./home\" method=\"post\">"
                + "<input type=\"hidden\" name=\"action\" value=\"login\">"
                + "<div class=\"input-group input-group-lg\">"
                + "<span class=\"input-group-addon\" id=\"sizing-addon1\"><span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span></span>"
                + "<select class=\"form-control\" name=\"userrole\">"
                + "<option selected=\"selected\">Select User</option>"
                + "<option value=\"admin\">Admin</option>"
                + "<option value=\"patient\">Patient</option>"
                + "</select>"
                + "</div><br/>"
                + "<div>"
                + "</div>"
                + "<div class=\"input-group input-group-lg\">"
                + "<span class=\"input-group-addon\" id=\"sizing-addon1\"><span class=\"glyphicon glyphicon-envelope\" aria-hidden=\"true\"></span></span>"
                + "<input type=\"email\" class=\"form-control\" name=\"email\" placeholder=\"example@gmail.com\" required aria-describedby=\"sizing-addon1\">"
                + "</div>"
                + "<br />"
                + "<div class=\"input-group input-group-lg\">"
                + "<span class=\"input-group-addon\" id=\"sizing-addon1\"><span class=\"glyphicon glyphicon-lock\" aria-hidden=\"true\"></span></span>"
                + "<input type=\"password\" name=\"password\" class=\"form-control\" placeholder=\"Password\" required aria-describedby=\"sizing-addon1\">"
                + "</div>"
                + "<br />"
                + "<div class=\"col-sm-7 col-sm-offset-2\">"
                + "<button type=\"submit\" class=\"btn btn-primary btn-block btn-lg\">Login</button>"
                + "</div>"
                + "</form>"
                + "</div>"
                + "<div style=\"text-align:center;font-weight:bold;color:red\">" + (actionError != null ? actionError : "") + "</div>"
                + "<a href=\"./register\" style=\"text-align:Center;font-weight:bold;font-size:120%;padding: 0 2%\">Register As Patient</a>"
                + "</div>"
                + "</div>"
                + "</div>" + Footer.footer();
    }
}

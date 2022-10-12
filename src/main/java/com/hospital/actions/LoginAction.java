package com.hospital.actions;

import com.hospital.model.Department;
import com.hospital.model.User;
import com.hospital.services.MySQLdb;
import com.hospital.services.SQLdb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/login", initParams = {
        @WebInitParam(name="username", value="gfffh@war.bom"),
        @WebInitParam(name="password",value="bonny255")
})
public class LoginAction extends Header {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print(this.login(null));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        String userRole = req.getParameter("userrole");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = new User();

            user.setUsername(email);
            user.setPassword(password);
            user.setUserType(userRole);

            SQLdb<User, Connection> sqLdb = new MySQLdb<>(user, (Connection) req.getServletContext().getAttribute("dbConnection"));
            ResultSet resultSet = sqLdb.fetchAll();
            if (userRole.equalsIgnoreCase("Select User")) {
                wr.print(this.login("You must select a user type<br/>"));
                return;
            }

            if (password == null || password.equalsIgnoreCase("")) {
                wr.print(this.login("Password is required<br/>"));
                return;
            }
            if(resultSet.next()){
                System.out.println("\n\n");
                System.out.println(resultSet.getString("username"));
                System.out.println("\n");
                if (!email.equals(resultSet.getString("username")) && !password.equals(resultSet.getString("Password"))) {
                    wr.print(this.login("Invalid username & password combination<br/>"));
                    return;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        HttpSession session = req.getSession(true);
        session.setAttribute("username", email);
        session.setAttribute("loggedInTime", "Logged In Time:" + new Date());

        List<Department> departments  = new ArrayList<>();
        session.setAttribute("departments", departments);

        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
        dispatcher.forward(req, resp);
    }

    public String login(String actionError) {
        return header(false) + "<div class=\"row \">"
                + "<div class=\"col-md-12\">"
                + "<br /><br /><br /><br /><br /><br /><br /><br />"
                + "<div class=\"panel panel-default login\">"
                + "<div class=\"panel-heading logintitle\">Login</div>"
                + "<div class=\"panel-body\">"
                + "<form class=\"form-horizontal center-block\" role=\"form\" action=\"./login\" method=\"post\">"
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

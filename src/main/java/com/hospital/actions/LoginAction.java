package com.hospital.actions;

import com.hospital.model.Admin;
import com.hospital.model.Department;
import com.hospital.model.Patient;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/login", initParams = {
        @WebInitParam(name="username", value="gfffh@war.bom"),
        @WebInitParam(name="password",value="bonny255")
})
public class LoginAction extends Header {
    ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().print(this.loginView(null));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();
        String userRole = req.getParameter("userrole");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        password = DigestUtils.md5Hex(password); //convert the password to md5 hash

        if (userRole.equalsIgnoreCase("Select User")) {
            wr.print(this.loginView("You must select a user type<br/>"));
            return;
        }

        if (password == null || password.equalsIgnoreCase("")) {
            wr.print(this.loginView("Password is required<br/>"));
            return;
        }
        if (userRole.equals("admin")) {
            Admin user = this.loginAdmin(email, password);
            if (user == null || user.getId() == null) {
                wr.print(this.loginView("Invalid username & password combination<br/>"));
                return;
            }
        }else{
            Patient user = this.loginPatient(email, password);
            if (user == null || user.getId() == null) {
                wr.print(this.loginView("Invalid username & password combination<br/>"));
                return;
            }
        }


        HttpSession session = req.getSession(true);
        session.setAttribute("username", email);
        session.setAttribute("loggedInTime", "Logged In Time:" + new Date());

        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
        dispatcher.forward(req, resp);
    }

    public String loginView(String actionError) {
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
    public Admin loginAdmin(String email, String password) {
        Admin user = null;
        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from admins where email='" + email + "' and " +
                    "password='" + password + "'");
            while (result.next()) {
                user = new Admin();
                user.setId((long) result.getInt("id"));
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setPhone(result.getString("phone"));
            }

        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return user;
    }
    public Patient loginPatient(String email, String password) {

        Patient user = null;

        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
            Statement sqlStmt = connection.createStatement();

            ResultSet result = sqlStmt.executeQuery("select * from patients where email='" + email + "' and " +
                    "password='" + password + "'");
            while (result.next()) {
                user = new Patient();
                user.setId((long) result.getInt("id"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setAddress(result.getString(""));
                user.setPhone(result.getString("phone"));
                user.setReasonOfVisit(result.getString("reason_of_visit"));
                user.setRoomNo(result.getString("room_no"));
                user.setBedNo(result.getString("bed_no"));
                user.setGender(result.getString("gender"));
                user.setAge(Integer.parseInt(result.getString("age")));
                user.setBloodGroup(result.getString("blood_group"));
            }

        }catch (Exception ex) {
            System.out.println("Log In Error: " + ex.getMessage());
        }

        return user;

    }
}

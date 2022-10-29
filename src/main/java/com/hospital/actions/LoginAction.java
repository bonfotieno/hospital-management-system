package com.hospital.actions;

import com.hospital.model.Admin;
import com.hospital.model.Patient;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

@WebServlet(urlPatterns = "/login")
public class LoginAction extends HttpServlet {
    @Resource(lookup = "java:jboss/datasources/hospital_sys")
    DataSource dataSource;
    ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userRole = req.getParameter("userrole");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        password = DigestUtils.md5Hex(password); //convert the password to md5 hash

        if (userRole.equalsIgnoreCase("Select User")) {
            servletCtx.setAttribute("loginError" , "You must select a user type<br/>");
            resp.sendRedirect("./login.jsp");
            return;
        }

        if (password == null || password.equalsIgnoreCase("")) {
            servletCtx.setAttribute("loginError" , "Password is required<br/>");
            resp.sendRedirect("./login.jsp");
            return;
        }
        if (userRole.equals("admin")) {
            Admin user = this.loginAdmin(email, password);
            if (user == null || user.getId() == null) {
                servletCtx.setAttribute("loginError" , "Invalid username & password combination<br/>");
                resp.sendRedirect("./login.jsp");
                return;
            }
        }else{
            Patient user = this.loginPatient(email, password);
            if (user == null || user.getId() == null) {
                servletCtx.setAttribute("loginError" , "Invalid username & password combination<br/>");
                resp.sendRedirect("./login.jsp");
                return;
            }
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("username", email);
        session.setAttribute("loggedInTime", "Logged In Time:" + new Date());

        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
        dispatcher.forward(req, resp);
    }
    public Admin loginAdmin(String email, String password) {
        Admin user = null;
        try {
            Connection connection = dataSource.getConnection();
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
            Connection connection = dataSource.getConnection();;
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
            ex.printStackTrace();
        }

        return user;

    }
}

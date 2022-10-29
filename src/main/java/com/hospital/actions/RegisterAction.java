package com.hospital.actions;

import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet("/register")
public class RegisterAction extends HttpServlet {
    ServletConfig config = null;
    ServletContext servletCtx = null;
    private PrintWriter wr;
    @Resource(lookup = "java:jboss/datasources/hospital_sys")
    DataSource dataSource;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config=config;
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String patientName = req.getParameter("patientname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String address = req.getParameter("add");
        String phoneNumber = req.getParameter("phone");
        String reasonOfVisit = req.getParameter("rov");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String bloodGroup = req.getParameter("bgroup");

        String actionError = "";


        if (email == null || email.equalsIgnoreCase(""))
            actionError = "Email is required<br/>";

        if (password == null || password.equalsIgnoreCase(""))
            actionError += "Password is required<br/>";

        if (confirmPassword == null || confirmPassword.equalsIgnoreCase(""))
            actionError += "Confirm password is required<br/>";

        if (password != null && confirmPassword != null && !password.equals(confirmPassword))
            actionError += "Password & confirm password do not match<br/>";

        servletCtx.setAttribute("registerError" , actionError);
        if (actionError.equals("")) {
            password = DigestUtils.md5Hex(password); //hash the password before storing in the database
            insert(patientName, email, password, address, phoneNumber, reasonOfVisit, gender, age, bloodGroup);
            resp.sendRedirect("./login.jsp");
        }else
            resp.sendRedirect("./register.jsp");
    }
    public void insert(String patientName, String email, String password, String address, String phoneNumber, String reasonOfVisit, String gender, String age, String bloodGroup) {
        try {
            Connection connection = dataSource.getConnection();
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into patients(name, email, password, address, phone, reason_of_visit, gender, age, blood_group) " +
                    "values('" + patientName.trim() + "','" + email + "','" + password + "','" + address +"','" + phoneNumber + "','" + reasonOfVisit +"','" + gender +"','" + Integer.parseInt(age) +"','" + bloodGroup +"')");
            wr.print("<script type=\"text/javascript\">window.alert(\"Patient Registration Done Successfully\");</script>");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

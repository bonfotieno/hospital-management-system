package com.hospital.actions;

import com.hospital.controllers.AuthBean;
import com.hospital.model.Admin;
import com.hospital.model.Auth;
import com.hospital.model.Patient;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
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
import java.util.Date;

@WebServlet(urlPatterns = "/login")
public class LoginAction extends HttpServlet {
    @EJB
    AuthBean authBean;
    ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Auth auth = new Auth();

        try {
            BeanUtils.populate(auth, req.getParameterMap());

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        try {
            Admin user = authBean.loginAdmin(auth);
            HttpSession session = req.getSession(true);
            session.setAttribute("username", user.getEmail());
            session.setAttribute("profile", user.getProfile());
            session.setAttribute("loggedInTime", " Logged In At: " + new Date());

            RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
            dispatcher.forward(req, resp);
        } catch (Exception ex) {
            servletCtx.setAttribute("loginError" , ex.getMessage());
            resp.sendRedirect("./login.jsp");
        }




//        String userRole = req.getParameter("userrole");
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//
//        password = DigestUtils.md5Hex(password); //convert the password to md5 hash
//
//        if (userRole.equalsIgnoreCase("Select User")) {
//            servletCtx.setAttribute("loginError" , "You must select a user type<br/>");
//            resp.sendRedirect("./login.jsp");
//            return;
//        }
//
//        if (password == null || password.equalsIgnoreCase("")) {
//            servletCtx.setAttribute("loginError" , "Password is required<br/>");
//            resp.sendRedirect("./login.jsp");
//            return;
//        }
//        if (userRole.equals("admin")) {
//            Admin user = authBean.loginAdmin(email, password);
//            if (user == null || user.getId() == null) {
//                servletCtx.setAttribute("loginError" , "Invalid username & password combination<br/>");
//                resp.sendRedirect("./login.jsp");
//                return;
//            }
//        }else{
//            Patient user = authBean.loginPatient(email, password);
//            if (user == null || user.getId() == null) {
//                servletCtx.setAttribute("loginError" , "Invalid username & password combination<br/>");
//                resp.sendRedirect("./login.jsp");
//                return;
//            }
//        }
//
//        HttpSession session = req.getSession(true);
//        session.setAttribute("username", email);
//        session.setAttribute("loggedInTime", "Logged In Time:" + new Date());
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
//        dispatcher.forward(req, resp);
    }

}

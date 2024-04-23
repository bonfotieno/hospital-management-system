package com.hospital.actions;

import com.hospital.controllers.AuthBean;
import com.hospital.controllers.AuthBeanI;
import com.hospital.model.Admin;
import com.hospital.model.Auth;
import com.hospital.model.Patient;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/login")
public class LoginAction extends HttpServlet {
    @EJB
    AuthBeanI authBean;
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
            auth.setUserRole(req.getParameter("userrole"));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (auth.getUserRole().equals("none")) {
            servletCtx.setAttribute("loginError" , "You Must Select A User Type");
            resp.sendRedirect("./login.jsp");
            return;
        }

        if (auth.getUserRole().equals("admin")) {
            try {
                Admin user = authBean.loginAdmin(auth);
                HttpSession session = req.getSession(true);
                session.setAttribute("username", user.getEmail());
                session.setAttribute("profile", user.getProfile());
                session.setAttribute("loggedInTime", " Logged In At: " + new Date());
                session.setMaxInactiveInterval(180);

                RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
                dispatcher.forward(req, resp);
            } catch (Exception ex) {
                servletCtx.setAttribute("loginError" , ex.getMessage());
                resp.sendRedirect("./login.jsp");
            }
        }else{
            try {
                Patient user = authBean.loginPatient(auth);
                HttpSession session = req.getSession(true);
                session.setAttribute("username", user.getEmail());
                session.setAttribute("loggedInTime", " Logged In At: " + new Date());

                RequestDispatcher dispatcher = req.getRequestDispatcher("./home");
                dispatcher.forward(req, resp);
            } catch (Exception ex) {
                servletCtx.setAttribute("loginError" , ex.getMessage());
                resp.sendRedirect("./login.jsp");
            }
        }

    }

}

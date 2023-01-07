package com.hospital.actions;

import com.hospital.controllers.PatientBeanI;
import com.hospital.model.Patient;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.ejb.EJB;
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

@WebServlet("/register-patient")
public class RegisterPatientAction extends HttpServlet {

    ServletConfig config = null;

    private final Patient patient = new Patient();

    private PrintWriter wr;

    ServletContext servletCtx = null;

    @EJB
    PatientBeanI patientBean;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config=config;
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        wr = resp.getWriter();

        String actionError = "";

        Patient patient = new Patient();

        try {
            BeanUtils.populate(patient, req.getParameterMap());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        try {
            patientBean.registerPatient(patient);
            wr.print("<script type=\"text/javascript\">window.alert(\"Patient Registration Done Successfully\");</script>");
            resp.sendRedirect("./login.jsp");

        } catch (Exception ex) {
            servletCtx.setAttribute("registerError" , ex.getMessage());
            resp.sendRedirect("./register_patient.jsp");
        }



    }
}

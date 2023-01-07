package com.hospital.actions;

import com.hospital.common.CommonMethods;
import com.hospital.controllers.PatientBeanI;
import com.hospital.model.Patient;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/patient-add", "/patient-edit", "/patient-delete", "/patient-edit-as-patient"})
public class PatientAction extends HttpServlet {
    private final Patient patient = new Patient();

    ServletContext servletCtx = null;

    @EJB
    PatientBeanI patientBean;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        patient.setId(Long.parseLong(req.getParameter("id")));
        patientBean.delete(patient);
        resp.sendRedirect("./patient_admin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        try {
            BeanUtils.populate(patient, req.getParameterMap());
            String sDate1=req.getParameter("admission_date");
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
            patient.setAdmissionDate(date1);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (StringUtils.isBlank(patient.getName())) {
            //add validation here
            return;
        }

        if (StringUtils.isBlank(patient.getGender())) {
            //add validation here
            return;
        }

        if (StringUtils.isBlank(patient.getAddress())) {
            //add validation here
            return;
        }

        if (StringUtils.isBlank(patient.getPhone())) {
            //add validation here
            return;
        }
        if (req.getServletPath().equals("/patient-add")) {
            patientBean.save(patient);
            resp.sendRedirect("./patient_admin.jsp");
            return;
        }

        if (req.getServletPath().equals("/patient-edit")) {
            patientBean.save(patient);
            resp.sendRedirect("./patient_admin.jsp");
            return;
        }

        if (req.getServletPath().equals("/patient-edit-as-patient")) {
            patientBean.save(patient);
            resp.sendRedirect("./patient_page.jsp");
        }
    }
}

package com.hospital.actions;

import com.hospital.common.CommonMethods;
import com.hospital.controllers.DoctorController;
import com.hospital.model.Doctor;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = {"/doctor-add", "/doctor-edit", "/doctor-delete"})
public class DoctorAction extends HttpServlet {
    private final Doctor doctor = new Doctor();
    ServletContext servletCtx = null;
    private final DoctorController doctorController = new DoctorController();
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        doctor.setId(Long.parseLong(req.getParameter("id")));
        System.out.println("\n\nUNIQUEID: "+ req.getParameter("uniqueID")+"\n\n");
        Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
        doctorController.delete(connection, doctor);
        resp.sendRedirect("./doctor.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        try {
            BeanUtils.populate(doctor, req.getParameterMap());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
        if (req.getServletPath().equals("/doctor-add")) {
            if (StringUtils.isBlank(doctor.getName())) {
                //add validation here
                return;
            }
            if (StringUtils.isBlank(doctor.getEmail())) {
                //add validation here
                return;
            }
            if (StringUtils.isBlank(doctor.getAddress())) {
                //add validation here
                return;
            }
            if (StringUtils.isBlank(doctor.getPhone())) {
                //add validation here
                return;
            }
            doctorController.add(connection, doctor);
            resp.sendRedirect("./doctor.jsp");
            return;
        }
        if (req.getServletPath().equals("/doctor-edit")) {
            doctorController.update(connection, doctor);
            resp.sendRedirect("./doctor.jsp");
        }
    }
}

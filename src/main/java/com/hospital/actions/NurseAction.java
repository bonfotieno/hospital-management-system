package com.hospital.actions;

import com.hospital.common.CommonMethods;
import com.hospital.controllers.NurseBeanI;
import com.hospital.model.Nurse;
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

@WebServlet(urlPatterns = {"/nurse-add", "/nurse-edit", "/nurse-delete"})
public class NurseAction extends HttpServlet {
    private final Nurse nurse = new Nurse();

    ServletContext servletCtx = null;

    @EJB
    NurseBeanI nurseBean;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        nurse.setId(Long.parseLong(req.getParameter("id")));
        nurseBean.delete(nurse);
        resp.sendRedirect("./nurse.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        try {
            BeanUtils.populate(nurse, req.getParameterMap());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (req.getServletPath().equals("/nurse-add")) {
            if (StringUtils.isBlank(nurse.getName())) {
                //add validation here
                return;
            }

            if (StringUtils.isBlank(nurse.getEmail())) {
                //add validation here
                return;
            }

            if (StringUtils.isBlank(nurse.getAddress())) {
                //add validation here
                return;
            }

            if (StringUtils.isBlank(nurse.getPhone())) {
                //add validation here
                return;
            }

            nurseBean.save(nurse);
            resp.sendRedirect("./nurse.jsp");
            return;
        }

        if (req.getServletPath().equals("/nurse-edit")) {
            nurseBean.save(nurse);
            resp.sendRedirect("./nurse.jsp");
        }
    }
}

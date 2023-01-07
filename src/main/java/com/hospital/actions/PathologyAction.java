package com.hospital.actions;

import com.hospital.common.CommonMethods;
import com.hospital.controllers.PathologyBeanI;
import com.hospital.model.Pathology;
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

@WebServlet(urlPatterns = {"/pathology-add", "/pathology-edit", "/pathology-delete"})
public class PathologyAction extends HttpServlet {
    private final Pathology pathology = new Pathology();

    ServletContext servletCtx = null;

    @EJB
    PathologyBeanI pathologyBean;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletCtx = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        pathology.setId(Long.parseLong(req.getParameter("id")));
        pathologyBean.delete(pathology);
        resp.sendRedirect("./pathology.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        try {
            BeanUtils.populate(pathology, req.getParameterMap());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        if (pathology.getPatientId()==null) {
            //add validation here
            return;
        }


        if (req.getServletPath().equals("/pathology-add")) {
            pathologyBean.save(pathology);
            resp.sendRedirect("./pathology.jsp");
            return;
        }

        if (req.getServletPath().equals("/pathology-edit")) {
            pathologyBean.save(pathology);
            resp.sendRedirect("./pathology.jsp");
        }
    }
}

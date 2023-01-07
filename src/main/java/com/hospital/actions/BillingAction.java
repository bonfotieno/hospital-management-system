package com.hospital.actions;

import com.hospital.common.CommonMethods;
import com.hospital.controllers.BillingBeanI;
import com.hospital.model.Billing;
import org.apache.commons.beanutils.BeanUtils;

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

@WebServlet(urlPatterns = {"/billing-add", "/billing-edit", "/billing-delete"})
public class BillingAction extends HttpServlet {

    private final Billing billing = new Billing();

    ServletContext servletCtx = null;

    @EJB
    BillingBeanI billingBeanI;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletCtx = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        billing.setId(Long.parseLong(req.getParameter("id")));
        billingBeanI.delete(billing);
        resp.sendRedirect("./billing.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        try {
            BeanUtils.populate(billing, req.getParameterMap());
            String sDate1=req.getParameter("discharge_date");
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
            billing.setDischargeDate(date1);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        if (billing.getPathologyCharge() == null) {
            //add validation here
            return;
        }

        if (req.getServletPath().equals("/billing-add")) {
            billingBeanI.save(billing);
            resp.sendRedirect("./billing.jsp");
            return;
        }

        if (req.getServletPath().equals("/billing-edit")) {
            billingBeanI.save(billing);
            resp.sendRedirect("./billing.jsp");
        }
    }
}

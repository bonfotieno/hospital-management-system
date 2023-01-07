package com.hospital.actions;


import com.hospital.common.CommonMethods;
import com.hospital.controllers.BedBeanI;
import com.hospital.model.Bed;
import com.hospital.model.Room;
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

@WebServlet(urlPatterns = {"/bed-add", "/bed-edit", "/bed-delete", "/bed-list-by-room"})
public class BedAction extends HttpServlet {

    private final Bed bed = new Bed();

    ServletContext servletCtx = null;

    @EJB
    BedBeanI bedBean;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletCtx = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }

        if (req.getServletPath().equals("/bed-delete")) {
            bed.setId(Long.parseLong(req.getParameter("id")));
            bedBean.delete(bed);
            resp.sendRedirect("./room.jsp#bedlist");
        }


        // handles ajax request
        if (req.getServletPath().equals("/bed-list-by-room")) {
            Room room = new Room();
            try {
                BeanUtils.populate(room, req.getParameterMap());
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(bedBean.getListByRoom(room));
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        try {
            BeanUtils.populate(bed, req.getParameterMap());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (req.getServletPath().equals("/bed-add")) {
            if (StringUtils.isBlank(bed.getBedNo())) {
                //add validation here
                return;
            }

            if (StringUtils.isBlank(bed.getBedStatus())) {
                //add validation here
                return;
            }

            bedBean.save(bed);
            resp.sendRedirect("./room.jsp#bedlist");
            return;
        }

        if (req.getServletPath().equals("/bed-edit")) {
            bedBean.save(bed);
            resp.sendRedirect("./room.jsp#bedlist");
        }
    }
}

package com.hospital.actions;

import com.hospital.common.CommonMethods;
import com.hospital.controllers.RoomBean;
import com.hospital.model.Room;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/room-add", "/room-edit", "/room-delete"})
public class RoomAction extends HttpServlet {
    private final Room room = new Room();
    ServletContext servletCtx = null;
    @EJB
    RoomBean roomBean;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        room.setUniqueID(req.getParameter("uniqueID"));
        roomBean.delete(room);
        resp.sendRedirect("./room.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        try {
            BeanUtils.populate(room, req.getParameterMap());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        room.setUniqueID(room.getRoomNo()+room.getBedNo());
        if (req.getServletPath().equals("/room-add")) {
            if (StringUtils.isBlank(room.getRoomNo())) {
                //add validation here
                return;
            }
            if (StringUtils.isBlank(room.getBedNo())) {
                //add validation here
                return;
            }
            if (StringUtils.isBlank(room.getRoomStatus())) {
                //add validation here
                return;
            }
            roomBean.add(room);
            resp.sendRedirect("./room.jsp");
            return;
        }
        if (req.getServletPath().equals("/room-edit")) {
            roomBean.update(room);
            resp.sendRedirect("./room.jsp");
        }
    }
}

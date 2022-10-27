package com.hospital.actions;

import com.hospital.common.CommonMethods;
import com.hospital.controllers.RoomController;
import com.hospital.model.Room;
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

@WebServlet(urlPatterns = {"/room-add", "/room-edit", "/room-delete"})
public class RoomAction extends HttpServlet {
    private final Room room = new Room();
    ServletContext servletCtx = null;
    private final RoomController roomController = new RoomController();
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
        Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
        roomController.delete(connection, room);
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
        Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
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
            roomController.add(connection, room);
            resp.sendRedirect("./room.jsp");
            return;
        }
        if (req.getServletPath().equals("/room-edit")) {
            roomController.update(connection, room);
            resp.sendRedirect("./room.jsp");
        }
    }
}

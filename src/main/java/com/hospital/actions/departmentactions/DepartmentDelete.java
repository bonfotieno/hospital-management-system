package com.hospital.actions.departmentactions;

import com.hospital.model.Department;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

@WebServlet("/department/delete")
public class DepartmentDelete extends HttpServlet {
    ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) { //checks if the previous session expired
            session.invalidate();
            resp.sendRedirect("");
            return;
        }
        String deptId = req.getParameter("deptId");

        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("delete from departments where id=" + deptId);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        resp.sendRedirect("../department");
    }
}

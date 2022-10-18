package com.hospital.actions.departmentactions;

import com.hospital.common.CommonMethods;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet("/department/delete")
public class DepartmentDelete extends HttpServlet {
    private ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonMethods.IsSessionExpired(req, resp);
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

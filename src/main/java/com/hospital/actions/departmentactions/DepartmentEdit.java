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


@WebServlet("/department/edit")
public class DepartmentEdit extends HttpServlet {
    private ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonMethods.IsSessionExpired(req, resp);
        String deptId = req.getParameter("deptId");
        String deptName = req.getParameter("deptName");
        String deptDesc = req.getParameter("deptDesc");

        try {
            Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate(
                    "UPDATE departments " +
                    "SET " +
                    "    name = '"+deptName+"'," +
                    "    description = '"+deptDesc+"'" +
                    "WHERE " +
                    "    id=" + deptId
            );

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        resp.sendRedirect("../department.jsp");
    }
}

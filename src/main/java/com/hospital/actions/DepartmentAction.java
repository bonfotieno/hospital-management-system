package com.hospital.actions;

import com.hospital.common.CommonMethods;
import com.hospital.controllers.DepartmentController;
import com.hospital.model.Department;
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

@WebServlet(urlPatterns = {"/department", "/department/edit", "/department/delete"})
public class DepartmentAction extends HttpServlet {
    private final Department department = new Department();
    ServletContext servletCtx = null;
    private final DepartmentController departmentController = new DepartmentController();
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        department.setId(Long.parseLong(req.getParameter("deptId")));
        Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
        departmentController.delete(connection, department);
        resp.sendRedirect("../department.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (CommonMethods.IsSessionExpired(req, resp)) {
            return;
        }
        try {
            BeanUtils.populate(department, req.getParameterMap());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        Connection connection = (Connection) servletCtx.getAttribute("dbConnection");
        System.out.println("THE VALUES: "+req.getParameterMap().toString());
        if (req.getServletPath().equals("/department")) {
            if (StringUtils.isBlank(department.getDeptName())) {
                //wr.print(this.addStudent("Name is required<br/>"));
                return;
            }
            if (StringUtils.isBlank(department.getDeptDesc())) {
                //wr.print(this.addStudent("Reg No is required<br/>"));
                return;
            }
            departmentController.add(connection, department);
            resp.sendRedirect("./department.jsp");
            return;
        }
        if (req.getServletPath().equals("/department/edit")) {
            departmentController.update(connection, department);
            resp.sendRedirect("../department.jsp");
        }
    }
}
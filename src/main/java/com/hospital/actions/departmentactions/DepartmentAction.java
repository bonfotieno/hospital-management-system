package com.hospital.actions.departmentactions;

import com.hospital.actions.HomeAction;
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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/department")
public class DepartmentAction extends HttpServlet {
    ServletContext servletCtx = null;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        servletCtx = config.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonMethods.IsSessionExpired(req, resp);
        Department department = new Department();
        try {
            BeanUtils.populate(department, req.getParameterMap());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        if (StringUtils.isBlank(department.getDeptName())) {
            //wr.print(this.addStudent("Name is required<br/>"));
            return;
        }

        if (StringUtils.isBlank(department.getDeptDesc())) {
            //wr.print(this.addStudent("Reg No is required<br/>"));
            return;
        }
        DepartmentController departmentController = new DepartmentController();
        departmentController.add((Connection) servletCtx.getAttribute("dbConnection"), department);
        resp.sendRedirect("./department.jsp");
    }
}
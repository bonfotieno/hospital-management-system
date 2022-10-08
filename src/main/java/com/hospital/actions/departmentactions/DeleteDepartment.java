package com.hospital.actions.departmentactions;

import com.hospital.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@WebServlet("/department/delete")
public class DeleteDepartment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptName = req.getParameter("deptName");
        for (Iterator<Department> iterator = DepartmentAction.departments.iterator(); iterator.hasNext(); ) {
            Department department = iterator.next();
            if (department.getDeptName().equals(deptName)) {
                iterator.remove();
                break;
            }
        }
        resp.sendRedirect("../department");
    }
}

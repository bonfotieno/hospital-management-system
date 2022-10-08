package com.hospital.actions.departmentactions;

import com.hospital.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@WebServlet("/department/edit")
public class EditDepartment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String prevName = req.getParameter("prevName");
        String deptName = req.getParameter("deptName");
        String deptDesc = req.getParameter("deptDesc");
        for (Iterator<Department> iterator = DepartmentAction.departments.iterator(); iterator.hasNext(); ) {
            Department department = iterator.next();
            if (department.getDeptName().equals(prevName)) {
                department.setDeptName(deptName);
                department.setDeptDesc(deptDesc);
                break;
            }
        }
        resp.sendRedirect("../department");
    }
}

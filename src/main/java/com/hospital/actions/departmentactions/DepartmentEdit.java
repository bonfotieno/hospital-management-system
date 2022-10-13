package com.hospital.actions.departmentactions;

import com.hospital.model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/department/edit")
public class DepartmentEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptId = req.getParameter("deptId");
        String deptName = req.getParameter("deptName");
        String deptDesc = req.getParameter("deptDesc");
        HttpSession session = req.getSession();
        List<Department> departments = (List<Department>) session.getAttribute("departments");
        for (Iterator<Department> iterator = departments.iterator(); iterator.hasNext(); ) {
            Department department = iterator.next();
            if (department.getId()==Integer.parseInt(deptId)) {
                department.setDeptName(deptName);
                department.setDeptDesc(deptDesc);
                break;
            }
        }
        resp.sendRedirect("../department");
    }
}

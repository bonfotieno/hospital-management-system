package com.hospital.controllers;

import com.hospital.model.Department;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentController implements Serializable {
    public void add(Connection connection, Department department){
        if (department == null || StringUtils.isBlank(department.getDeptName()) || StringUtils.isBlank(department.getDeptDesc()))
            return;
        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into departments(name,description) " +
                    "values('" + department.getDeptName() + "','" + department.getDeptDesc() + "')");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(){}
    public void delete(){}
    public List<Department> list(Connection connection, Department filter) {
        List<Department> departments = new ArrayList<Department>();
        try {
            Statement sqlStmt = connection.createStatement();
            ResultSet result = sqlStmt.executeQuery("select * from departments");
            while (result.next()) {
                Department department = new Department();
                department.setId((long) result.getInt("id"));
                department.setDeptName(result.getString("name"));
                department.setDeptDesc(result.getString("description"));
                departments.add(department);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return departments;
    }
}

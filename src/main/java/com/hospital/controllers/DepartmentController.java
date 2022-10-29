package com.hospital.controllers;

import com.hospital.model.Department;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DepartmentController implements Serializable {
    @Resource(lookup = "java:jboss/datasources/hospital_sys")
    DataSource dataSource;
    public void add(Department department){
        if (department == null || StringUtils.isBlank(department.getDeptName()) || StringUtils.isBlank(department.getDeptDesc()))
            return;
        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
            sqlStmt.executeUpdate("insert into departments(name,description) " +
                    "values('" + department.getDeptName() + "','" + department.getDeptDesc() + "')");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void update(Department department){
        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
            sqlStmt.executeUpdate(
                    "UPDATE departments " +
                            "SET " +
                            "    name = '"+department.getDeptName()+"'," +
                            "    description = '"+department.getDeptDesc()+"'" +
                            "WHERE " +
                            "    id=" + department.getId()
            );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void delete(Department department){
        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
            sqlStmt.executeUpdate("delete from departments where id=" + department.getId());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Department> list(Department filter) {
        List<Department> departments = new ArrayList<Department>();
        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
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

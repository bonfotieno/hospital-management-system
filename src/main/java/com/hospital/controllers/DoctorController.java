package com.hospital.controllers;

import com.hospital.model.Doctor;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    public void add(Connection connection, Doctor doctor){
        if (doctor == null || StringUtils.isBlank(doctor.getName()) || StringUtils.isBlank(doctor.getEmail() )|| StringUtils.isBlank(doctor.getPhone()))
            return;
        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into doctors(name,email,address,phone,department_id) " +
                    "values('" + doctor.getName() + "','" + doctor.getEmail() + "','" +  doctor.getAddress() + doctor.getPhone() + "'," +
                    "select id from departments where name='" +doctor.getDepartmentName() + "')");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void update(Connection connection, Doctor doctor){
        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate(
                    "UPDATE doctors " +
                            "SET " +
                            "    name = '"+doctor.getName()+"'," +
                            "    email = '"+doctor.getEmail()+"'" +
                            "    address = '"+doctor.getAddress()+"'" +
                            "    phone = '"+doctor.getPhone()+"'" +
                            "    select id from departments where name='" +doctor.getDepartmentName() + "'"+
                            "WHERE " +
                            "    id=" + doctor.getId()
            );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void delete(Connection connection, Doctor doctor){
        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("delete from doctors where id=" + doctor.getId());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Doctor> list(Connection connection, Doctor filter) {
        List<Doctor> doctors = new ArrayList<Doctor>();
        try {
            Statement sqlStmt = connection.createStatement();
            ResultSet result = sqlStmt.executeQuery("select * from doctors");
            while (result.next()) {
                Doctor doctor = new Doctor();
                doctor.setId((long) result.getInt("id"));
                doctor.setName(result.getString("name"));
                doctor.setEmail(result.getString("email"));
                doctor.setAddress(result.getString("address"));
                doctor.setPhone(result.getString("phone"));
                ResultSet deptResult = sqlStmt.executeQuery("select name from departments where id="+(long) result.getInt("department_id"));
                doctor.setDepartmentName(deptResult.getString("name"));
                doctors.add(doctor);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return doctors;
    }
}

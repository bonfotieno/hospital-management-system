package com.hospital.controllers;

import com.hospital.model.Department;
import com.hospital.model.Doctor;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named("doctorController")
public class DoctorController {

    @Resource(lookup = "java:jboss/datasources/hospital_sys")
    DataSource dataSource;
    private List<Department> list;
    public void add(Doctor doctor, String password){
        if (doctor == null || StringUtils.isBlank(doctor.getName()) || StringUtils.isBlank(doctor.getEmail() )|| StringUtils.isBlank(doctor.getPhone()))
            return;
        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
            sqlStmt.executeUpdate("insert into doctors(name,email,password,address,phone,department_id) " +
                    "values('" + doctor.getName() + "','" + doctor.getEmail() + "','" + password + "','" +  doctor.getAddress() +"','"+ doctor.getPhone() + "'," +
                    "(select id from departments where name='" +doctor.getDepartmentName() + "'))");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void update(Doctor doctor, String password){
        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
            sqlStmt.executeUpdate(
                    "UPDATE doctors " +
                            "SET " +
                            "    name = '"+doctor.getName()+"'," +
                            "    email = '"+doctor.getEmail()+"'," +
                            (password.equals("passwoord_here")?"":" password = '"+password+"',") +
                            "    address = '"+doctor.getAddress()+"'," +
                            "    phone = '"+doctor.getPhone()+"'," +
                            "    department_id = (select id from departments where name='" +doctor.getDepartmentName() + "') "+
                            "WHERE " +
                            "    id=" + doctor.getId()
            );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void delete(Doctor doctor){
        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
            sqlStmt.executeUpdate("delete from doctors where id=" + doctor.getId());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Doctor> getList() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        try {
            Statement sqlStmt = dataSource.getConnection().createStatement();
            ResultSet result = sqlStmt.executeQuery("SELECT doctors.id, doctors.name, doctors.email, doctors.address, doctors.phone, departments.name\n" +
                                                        "FROM doctors\n" +
                                                        "INNER JOIN departments ON doctors.department_id=departments.id;");
            while (result.next()) {
                Doctor doctor = new Doctor();
                doctor.setId((long) result.getInt("id"));
                doctor.setName(result.getString("name"));
                doctor.setEmail(result.getString("email"));
                doctor.setAddress(result.getString("address"));
                doctor.setPhone(result.getString("phone"));
                doctor.setDepartmentName(result.getString(6));
                doctors.add(doctor);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
        }
        return doctors;
    }

    public void setList(List<Department> list) {
        this.list = list;
    }
}

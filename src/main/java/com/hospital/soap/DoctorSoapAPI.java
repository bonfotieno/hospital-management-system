package com.hospital.soap;

import com.hospital.controllers.DoctorBeanI;
import com.hospital.model.Doctor;

import javax.ejb.EJB;
//import javax.jws.WebService;
import java.util.List;

//@WebService
public class DoctorSoapAPI {

    @EJB
    private DoctorBeanI doctorBean;

    public void add(Doctor doctor) {
        try {
            doctorBean.save(doctor);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public List<Doctor> list() {
        return doctorBean.getList();
    }
}

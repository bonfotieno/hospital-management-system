package com.hospital.controllers;

import com.hospital.model.Doctor;

import java.util.List;

public interface DoctorBeanI {
    void save(Doctor doctor);

    void update();

    void delete(Doctor doctor);

    List<Doctor> getList();
}

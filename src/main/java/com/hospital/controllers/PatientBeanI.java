package com.hospital.controllers;

import com.hospital.model.Patient;

import java.util.List;

public interface PatientBeanI {
    void save(Patient patient);

    void update();

    void delete(Patient patient);

    List<Patient> getList();

    List<Patient> getPatientListByEmail(String email);

    Patient registerPatient(Patient patient) throws Exception;
}

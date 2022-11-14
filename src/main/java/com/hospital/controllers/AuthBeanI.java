package com.hospital.controllers;

import com.hospital.model.Admin;
import com.hospital.model.Auth;
import com.hospital.model.Patient;

public interface AuthBeanI {
    Admin loginAdmin(Auth auth) throws Exception;

    Patient loginPatient(String email, String password);
}

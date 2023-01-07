package com.hospital.restapi;

import com.hospital.controllers.AuthBeanI;
import com.hospital.model.Admin;

import javax.ejb.EJB;
//import javax.jws.WebService;
//
//@WebService
public class AuthSoapApi {

    @EJB
    AuthBeanI authBean;

    public Admin login(String username, String password) {
        return new Admin(); //authBean.login(new Auth());
    }
}

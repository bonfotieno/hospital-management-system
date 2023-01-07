package com.hospital.controllers;

import com.hospital.model.Admin;
import com.hospital.model.Auth;
import com.hospital.model.Patient;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AuthBean implements AuthBeanI, Serializable {
    @PersistenceContext
    EntityManager em;
    public Admin loginAdmin(Auth auth) throws Exception {
        if (auth.getUsername() == null || auth.getPassword() == null)
            throw new Exception("Invalid password or username");
        String password = DigestUtils.md5Hex(auth.getPassword());                   //convert the password to md5 hash

        List<Auth> auths = em.createQuery("FROM Auth a WHERE a.username=:usrName " +
                        "and a.password=:pwd", Auth.class)
                .setParameter("usrName", auth.getUsername())
                .setParameter("pwd", password)
                .getResultList();

        if (auths == null || auths.isEmpty() || auths.get(0) == null)
            throw new Exception("Invalid username or password");

        return auths.get(0).getAdmin();
    }

    public Patient loginPatient(Auth auth) throws Exception {

        if (auth.getUsername() == null || auth.getPassword() == null)
            throw new Exception("Invalid password or username");
        String password = DigestUtils.md5Hex(auth.getPassword()); //convert the password to md5 hash

        List<Auth> auths = em.createQuery("FROM Auth a WHERE a.username=:usrName " +
                        "and a.password=:pwd", Auth.class)
                .setParameter("usrName", auth.getUsername())
                .setParameter("pwd", password)
                .getResultList();

        if (auths == null || auths.isEmpty() || auths.get(0) == null)
            throw new Exception("Invalid username or password");

        return auths.get(0).getPatient();

    }
}

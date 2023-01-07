package com.hospital.controllers;

import com.hospital.model.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.*;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Named("patientBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PatientBean implements PatientBeanI{

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Patient patient) {
        if (patient == null || StringUtils.isBlank(patient.getName()) || StringUtils.isBlank(patient.getEmail() )|| StringUtils.isBlank(patient.getPhone()))
            return;
        try {
            Room room = em.createQuery("FROM Room r WHERE r.id = :roomId", Room.class)
                    .setParameter("roomId", patient.getRoomId())
                    .getSingleResult();
            Bed bed = em.createQuery("FROM Bed b WHERE b.id = :bedId", Bed.class)
                    .setParameter("bedId", patient.getBedId())
                    .getSingleResult();
            patient.setRoomAdmitted(room);
            patient.setBedAdmitted(bed);
        } catch (Exception ex){
            System.out.println("\n\nDebugger Details: "+patient.getBedId()+"\n\n"+ex.getMessage()+"\n\n");
        }
        em.merge(patient);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete(Patient patient) {
        em.remove(em.find(Patient.class, patient.getId()));
    }

    @Override
    public List<Patient> getList() {
        return em.createQuery("FROM Patient p", Patient.class).getResultList();
    }

    public List<Patient> getPatientListByEmail(String email) {
        return em.createQuery("FROM Patient p WHERE p.email = :email", Patient.class)
                .setParameter("email", email)
                .getResultList();
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Patient registerPatient(Patient patient) throws Exception{
        if (patient == null)
            throw new Exception("Invalid details");

        if (patient.getEmail() == null)
            throw new Exception("Email is required");

        Auth auth = new Auth();
        auth.setUsername(patient.getEmail());
        if (patient.getPassword() == null || patient.getConfirmPassword() == null
                || !patient.getPassword().equals(patient.getConfirmPassword()))
            throw new Exception("Password & confirm password is required and must match");

        String passwordHash = DigestUtils.md5Hex(patient.getPassword()); //hash the password before storing in the database

        auth.setUsername(patient.getEmail());
        auth.setPassword(passwordHash);
        auth.setConfirmPassword(patient.getConfirmPassword());
        auth.setStatus(Status.ACTIVE);

        patient.addAuth(auth);

        return em.merge(patient);

    }
}

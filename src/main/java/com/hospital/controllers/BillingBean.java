package com.hospital.controllers;

import com.hospital.model.Billing;
import com.hospital.model.Patient;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Named("billingBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BillingBean implements BillingBeanI {

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Billing billing) {
        if (billing == null || billing.getPathologyCharge() == null) {
            return;
        }
        Patient patient = em.createQuery("FROM Patient patient WHERE patient.id = :patientId", Patient.class)
                .setParameter("patientId", billing.getPatientId())
                .getSingleResult();
        billing.setPatient(patient);
        em.merge(billing);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete(Billing billing) {
        em.remove(em.find(Billing.class, billing.getId()));
    }

    @Override
    public List<Billing> getList() {
        return em.createQuery("FROM Billing billing", Billing.class).getResultList();
    }

    @Override
    public List<Billing> getBillingListByEmail(String email) {
        return em.createQuery("FROM Billing billing WHERE billing.patient.email = :email", Billing.class)
                .setParameter("email", email)
                .getResultList();
    }
}

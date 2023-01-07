package com.hospital.controllers;


import com.hospital.model.Pathology;
import com.hospital.model.Patient;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named("pathologyBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PathologyBean implements PathologyBeanI{

    @PersistenceContext
    EntityManager em;

    public void save(Pathology pathology){
        if (pathology == null )
            return;
        Patient patient = em.createQuery("FROM Patient patient WHERE patient.id = :patientId", Patient.class)
                .setParameter("patientId", pathology.getPatientId())
                .getSingleResult();
        pathology.setPatient(patient);
        em.merge(pathology);
    }

    public void update(){

    }

    public void delete(Pathology pathology){
        em.remove(em.find(Pathology.class, pathology.getId()));
    }

    public List<Pathology> getList() {
        return em.createQuery("FROM Pathology pathology", Pathology.class).getResultList();
    }

    public List<Pathology> getPathologyListByEmail(String email) {
        return em.createQuery("FROM Pathology pathology WHERE pathology.patient.email = :email", Pathology.class)
                .setParameter("email", email)
                .getResultList();
    }

}

package com.hospital.controllers;

import com.hospital.model.Department;
import com.hospital.model.Doctor;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;


@Named("doctorBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DoctorBean implements DoctorBeanI, Serializable {

    @PersistenceContext
    EntityManager em;

    public void save(Doctor doctor){
        if (doctor == null || StringUtils.isBlank(doctor.getName()) || StringUtils.isBlank(doctor.getEmail() )|| StringUtils.isBlank(doctor.getPhone()))
            return;
        Department department = em.createQuery("FROM Department r WHERE r.id = :departmentId", Department.class)
                .setParameter("departmentId", doctor.getDepartmentId())
                .getSingleResult();
        doctor.setDepartment(department);
        em.merge(doctor);
    }

    public void update(){

    }

    public void delete(Doctor doctor){
        em.remove(em.find(Doctor.class, doctor.getId()));
    }

    public List<Doctor> getList() {
        return em.createQuery("FROM Doctor d", Doctor.class).getResultList();
    }

}

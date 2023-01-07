package com.hospital.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hospital.model.Bed;
import com.hospital.model.Nurse;
import com.hospital.model.Room;

import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Named("bedBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BedBean implements  BedBeanI{

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void save(Bed bed) {
        if (bed == null || StringUtils.isBlank(bed.getBedNo()) || StringUtils.isBlank(bed.getBedStatus()))
            return;
        Room room = em.createQuery("FROM Room r WHERE r.id = :roomId", Room.class)
                .setParameter("roomId", bed.getRoomId())
                .getSingleResult();
        bed.setRoom(room);
        em.merge(bed);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete(Bed bed) {
        em.remove(em.find(Bed.class, bed.getId()));
    }

    @Override
    public List<Bed> getList() {
        return em.createQuery("FROM Bed b", Bed.class).getResultList();
    }

    public String getListByRoom(Room room) {
        List<Bed> bedList =  em.createQuery("FROM Bed b WHERE b.room=:room", Bed.class)
                .setParameter("room", room)
                .getResultList();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(bedList);
    }

}

package com.hospital.controllers;

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
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Named("nurseBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NurseBean implements NurseBeanI, Serializable {

    @PersistenceContext
    EntityManager em;

    public void save(Nurse nurse){
        if (nurse == null || StringUtils.isBlank(nurse.getName()) || StringUtils.isBlank(nurse.getEmail() )|| StringUtils.isBlank(nurse.getPhone()))
            return;
        Room room = em.createQuery("FROM Room r WHERE r.id = :roomId", Room.class)
                .setParameter("roomId", nurse.getRoomId())
                .getSingleResult();
        nurse.setRoom(room);
        em.merge(nurse);
    }

    public void update(){

    }

    public void delete(Nurse nurse){
        em.remove(em.find(Nurse.class, nurse.getId()));
    }

    public List<Nurse> getList() {
        return em.createQuery("FROM Nurse n ORDER BY n.email", Nurse.class).getResultList();
    }

    public List<Room> getCrossJoinedList() {
//        Query query = em.createQuery("select n.name, r.roomNo from Nurse n CROSS JOIN r.room r");
//        return query.getResultList();

//        "from Company as comp, Employee as emp"

        TypedQuery<Room> query
                = em.createQuery(
                "SELECT r FROM Nurse n, Room r", Room.class);
        List<Room> resultList = query.getResultList();
//        System.out.println("\n\n"+resultList+"\n\n");
        return resultList;
    }

    public List<Room> getInnerJoinedLIst(){
        TypedQuery<Room> query
                = em.createQuery(
                "SELECT r FROM Nurse n INNER JOIN n.room r", Room.class);
        return query.getResultList();
    }
}

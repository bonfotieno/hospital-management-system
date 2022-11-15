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

    public List<Nurse> getCrossJoinedList() {
//        Query query = em.createQuery("select n.name, r.roomNo from Nurse n CROSS JOIN r.room r");
//        return query.getResultList();

//        em.createQuery("SELECT o FROM Order o join o.vehicle v where o.vehicle.id!=v.id", Order.class);
//        "from Company as comp, Employee as emp"
//        "SELECT r FROM Nurse n, Room r", Room.class);

        TypedQuery<Nurse> query
                = em.createQuery(
                "SELECT n FROM Room r, Nurse n", Nurse.class);
        List<Nurse> resultList = query.getResultList();
        System.out.println("\n\n"+resultList.get(1)+"\n\n");
        return resultList;
    }

    public List<Nurse> getLeftJoinedLIst(){
        TypedQuery<Nurse> query
                = em.createQuery(
                "SELECT new Nurse(n.name, r.roomNo) FROM Nurse n LEFT JOIN n.room r", Nurse.class);
        return query.getResultList();
    }

    public List<Nurse> getCrossedLIst(){
        TypedQuery<Nurse> query
                = em.createQuery(
                "SELECT n FROM Room r, Nurse n WHERE n.room.roomNo = r.roomNo", Nurse.class);
        return query.getResultList();
    }

}

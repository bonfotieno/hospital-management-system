package com.hospital.controllers;

import com.hospital.model.Department;
import com.hospital.model.Room;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named("roomBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RoomBean {
    @PersistenceContext
    EntityManager em;
    private List<Department> list;
    public void add(Room room){
        if (room == null || StringUtils.isBlank(room.getRoomNo()) || StringUtils.isBlank(room.getBedNo()))
            return;
        em.merge(room);
    }
    public void update(Room room){
        Room rm = em.find(Room.class, room.getUniqueID());
        rm.setRoomNo(room.getRoomNo());
        rm.setBedNo(room.getBedNo());
        rm.setRoomStatus(room.getRoomStatus());
    }
    public void delete(Room room){
        Room rm = em.find(Room.class, room.getUniqueID());
        em.remove(rm);
    }
    public List<Room> getList() {
        return em.createQuery("FROM Room r", Room.class).getResultList();
    }

    public void setList(List<Department> list) {
        this.list = list;
    }
}

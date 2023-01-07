package com.hospital.controllers;

import com.hospital.model.Room;
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

@Named("roomBean")
@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RoomBean implements RoomBeanI, Serializable {
    @PersistenceContext
    EntityManager em;

    public void add(Room room){
        if (room == null || StringUtils.isBlank(room.getRoomNo()) || StringUtils.isBlank(room.getRoomStatus()))
            return;
        em.merge(room);
    }

    public void update(Room room){
        Room rm = em.find(Room.class, room.getId());
        rm.setRoomNo(room.getRoomNo());
        rm.setRoomStatus(room.getRoomStatus());
        rm.setRoomDescription(room.getRoomDescription());
    }

    public void delete(Room room){
        Room rm = em.find(Room.class, room.getId());
        em.remove(rm);
    }

    public List<Room> getList() {
        return em.createQuery("FROM Room r", Room.class).getResultList();
    }

    public List<Room> getGroupedList() {
        return em.createQuery("FROM Room r GROUP BY r.roomNo", Room.class).getResultList();
    }

}

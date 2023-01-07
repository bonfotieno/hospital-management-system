package com.hospital.controllers;

import com.hospital.model.Bed;
import com.hospital.model.Room;

import java.util.List;

public interface BedBeanI {
    void save(Bed bed);

    void update();

    void delete(Bed bed);

    List<Bed> getList();

    String getListByRoom(Room room);
}

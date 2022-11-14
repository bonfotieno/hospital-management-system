package com.hospital.controllers;

import com.hospital.model.Room;

import java.util.List;

public interface RoomBeanI {
    void add(Room room);

    void update(Room room);

    void delete(Room room);

    List<Room> getList();

    List<Room> getGroupedList();

}

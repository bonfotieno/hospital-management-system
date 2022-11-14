package com.hospital.controllers;

import com.hospital.model.Nurse;
import com.hospital.model.Room;

import java.util.List;

public interface NurseBeanI {

    void save(Nurse nurse);

    void update();

    void delete(Nurse nurse);

    List<Nurse> getList();

    List<Room> getCrossJoinedList();
}

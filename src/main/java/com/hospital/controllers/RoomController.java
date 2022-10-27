package com.hospital.controllers;

import com.hospital.model.Room;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomController {
    public void add(Connection connection, Room room){
        if (room == null || StringUtils.isBlank(room.getRoomNo()) || StringUtils.isBlank(room.getBedNo()))
            return;
        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("insert into rooms(unique_id,room_no,bed_no,room_status) " +
                    "values('" + room.getUniqueID() + "','" + room.getRoomNo() + "','" + room.getBedNo() + "','" + room.getRoomStatus() + "')");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void update(Connection connection, Room room){
        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate(
                    "UPDATE rooms " +
                            "SET " +
                            "    room_no = '"+room.getRoomNo()+"'," +
                            "    bed_no = '"+room.getBedNo()+"'," +
                            "    room_status = '"+room.getRoomStatus()+"' " +
                            "WHERE " +
                            "    unique_id= '" + room.getUniqueID()+"' "
            );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void delete(Connection connection, Room room){
        try {
            Statement sqlStmt = connection.createStatement();
            sqlStmt.executeUpdate("delete from rooms where unique_id= '" + room.getUniqueID() +"' ");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Room> list(Connection connection, Room filter) {
        List<Room> rooms = new ArrayList<Room>();
        try {
            Statement sqlStmt = connection.createStatement();
            ResultSet result = sqlStmt.executeQuery("select * from rooms order by room_no,bed_no asc");
            while (result.next()) {
                Room room = new Room();
                room.setUniqueID(result.getString("unique_id"));
                room.setRoomNo(result.getString("room_no"));
                room.setBedNo(result.getString("bed_no"));
                room.setRoomStatus(result.getString("room_status"));
                rooms.add(room);
            }
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return rooms;
    }
}

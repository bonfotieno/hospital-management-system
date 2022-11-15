package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "nurses")
public class Nurse extends BaseEntity{

    public Nurse() {
    }

    public Nurse(String name, String roomNo) {
        this.name = name;
        this.roomNo = roomNo;
    }

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String address;

    @Column
    private String phone;

    @Transient
    private Long roomId;

    @Transient
    private String roomNo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

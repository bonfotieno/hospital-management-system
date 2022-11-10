package com.hospital.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {

    @Column(name = "unique_id", columnDefinition="VARCHAR(64)", unique=true, nullable=false)
    private String uniqueID;

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "bed_no")
    private String bedNo;

    @Column(name = "room_status")
    private String roomStatus;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Nurse> nurses = new ArrayList<Nurse>();

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public void addNurse(Nurse nurse){
        nurse.setRoom(this);
        getNurses().add(nurse);
    }
}

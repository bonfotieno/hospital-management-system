package com.hospital.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
}

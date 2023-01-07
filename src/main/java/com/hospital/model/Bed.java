package com.hospital.model;

import com.google.gson.annotations.Expose;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beds")
public class Bed extends BaseEntity{

    @Column(name = "bed_no")
    @Expose
    private String bedNo;

    @Column(name = "bed_status")
    @Expose
    private String bedStatus;

    @Column(name = "bed_description")
    @Expose
    private String bedDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @Transient
    private Long roomId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @OneToMany(mappedBy = "bedAdmitted", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Patient> patients;

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(String bedStatus) {
        this.bedStatus = bedStatus;
    }

    public String getBedDescription() {
        return bedDescription;
    }

    public void setBedDescription(String bedDescription) {
        this.bedDescription = bedDescription;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @JsonbTransient
    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}

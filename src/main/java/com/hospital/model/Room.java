package com.hospital.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NamedQueries({
        @NamedQuery(name = Room.FIND_ALL, query = "SELECT r FROM Room r"),
        @NamedQuery(name = Room.FIND_WITH_ID, query = "SELECT r FROM Room r WHERE r.id=:Id"),
})

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity implements Serializable {

    public static final String FIND_ALL = "Room.findAll";
    public static final String FIND_WITH_ID = "Room.findWithId";

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "room_status")
    private String roomStatus;

    @Column(name = "room_description")
    private String roomDescription;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Bed> beds;

    @OneToMany(mappedBy = "roomAdmitted", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Nurse> nurses;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    @JsonbTransient
    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }

    public void addBed(Bed bed){
        bed.setRoom(this);
        getBeds().add(bed);
    }

    @JsonbTransient
    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient){
        patient.setRoomAdmitted(this);
        getPatients().add(patient);
    }

    @JsonbTransient
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

package com.hospital.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity{

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String address;

    @Column
    private String phone;

    @Column(name = "reason_of_visit", columnDefinition = "varchar(300) default 'Not Stated'")
    private String reasonOfVisit;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room roomAdmitted;

    @Transient
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bed bedAdmitted;

    @Transient
    private Long bedId;

    @Column(name = "referred_to", columnDefinition = "varchar(200) default 'Not Referred'")
    private String referredTo;

    @Column
    private String gender;

    @Column(name = "admission_date")
    @Temporal(TemporalType.DATE)
    private Date admissionDate;

    @Column
    private int age;

    @Column(name = "blood_group")
    private String bloodGroup;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Auth> auths = new ArrayList<Auth>();

    @Transient
    private String password;

    @Transient
    private String confirmPassword;

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

    public String getReasonOfVisit() {
        return reasonOfVisit;
    }

    public void setReasonOfVisit(String reasonOfVisit) {
        this.reasonOfVisit = reasonOfVisit;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public Room getRoomAdmitted() {
        return roomAdmitted;
    }

    public void setRoomAdmitted(Room roomAdmitted) {
        this.roomAdmitted = roomAdmitted;
    }

    public Bed getBedAdmitted() {
        return bedAdmitted;
    }

    public void setBedAdmitted(Bed bedAdmitted) {
        this.bedAdmitted = bedAdmitted;
    }

    public String getReferredTo() {
        return referredTo;
    }

    public void setReferredTo(String referredTo) {
        this.referredTo = referredTo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Auth> getAuths() {
        return auths;
    }

    public void setAuths(List<Auth> auths) {
        this.auths = auths;
    }

    public void addAuth(Auth auth){
        auth.setPatient(this);
        getAuths().add(auth);
    }
}

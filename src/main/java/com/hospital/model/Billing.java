package com.hospital.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "billings")
public class Billing extends  BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;

    @Transient
    private Long patientId;

    @Column(name = "pathology_charges")
    private String billNo;

    @Column(name = "bill_no")
    private Double pathologyCharge;

    @Column(name = "other_charges")
    private Double otherCharge;

    @Column(name = "misc_charges")
    private Double miscCharge;

    @Column(name = "discharge_date")
    @Temporal(TemporalType.DATE)
    private Date dischargeDate;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Double getPathologyCharge() {
        return pathologyCharge;
    }

    public void setPathologyCharge(Double pathologyCharge) {
        this.pathologyCharge = pathologyCharge;
    }

    public Double getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(Double otherCharge) {
        this.otherCharge = otherCharge;
    }

    public Double getMiscCharge() {
        return miscCharge;
    }

    public void setMiscCharge(Double miscCharge) {
        this.miscCharge = miscCharge;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}

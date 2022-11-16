package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "pathology")
public class Pathology extends BaseEntity{

    @Column
    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    @Column(name = "x-ray_results", columnDefinition = "varchar(1020) default 'Not Done'")
    private String xrayResults;

    @Column(name = "ultra_sound_results", columnDefinition = "varchar(1020) default 'Not Done'")
    private String ultraSound;

    @Column(name = "blood_test_results", columnDefinition = "varchar(1020) default 'Not Done'")
    private String bloodTest;

    @Column(name = "CT_scan_results", columnDefinition = "varchar(1020) default 'Not Done'")
    private String ctScan;

    @Column(name = "MRI_results", columnDefinition = "varchar(1020) default 'Not Done'")
    private String MRI;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getXrayResults() {
        return xrayResults;
    }

    public void setXrayResults(String xrayResults) {
        this.xrayResults = xrayResults;
    }

    public String getUltraSound() {
        return ultraSound;
    }

    public void setUltraSound(String ultraSound) {
        this.ultraSound = ultraSound;
    }

    public String getBloodTest() {
        return bloodTest;
    }

    public void setBloodTest(String bloodTest) {
        this.bloodTest = bloodTest;
    }

    public String getCtScan() {
        return ctScan;
    }

    public void setCtScan(String ctScan) {
        this.ctScan = ctScan;
    }

    public String getMRI() {
        return MRI;
    }

    public void setMRI(String MRI) {
        this.MRI = MRI;
    }
}

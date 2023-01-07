package com.hospital.model;

import javax.persistence.*;

@Entity
@Table(name = "pathologies")
public class Pathology extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;

    @Transient
    private Long patientId;

    @Column(name = "general_symptoms", nullable = false)
    private String generalSymptoms;

    @Column(name = "x_ray_results")
    private String xrayResults = "Not Done";

    @Column(name = "ultra_sound_results")
    private String ultraSound = "Not Done";

    @Column(name = "blood_test_results")
    private String bloodTest = "Not Done";

    @Column(name = "CT_scan_results")
    private String ctScan = "Not Done";

    @Column(name = "MRI_results", nullable = false)
    private String MRI;

    @PrePersist
    public void prePersist() {
        if(generalSymptoms == null || generalSymptoms.equals(""))
            generalSymptoms  = "No symptoms recorded";

        if(MRI == null || MRI.equals(""))
            MRI  = "Not Done";

        if(ctScan == null || ctScan.equals(""))
            ctScan  = "Not Done";

        if(bloodTest == null || bloodTest.equals(""))
            bloodTest  = "Not Done";

        if(ultraSound == null || ultraSound.equals(""))
            ultraSound  = "Not Done";

        if(xrayResults == null || xrayResults.equals(""))
            xrayResults  = "Not Done";


    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getGeneralSymptoms() {
        return generalSymptoms;
    }

    public void setGeneralSymptoms(String generalSymptoms) {
        this.generalSymptoms = generalSymptoms;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

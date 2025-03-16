package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicalJournal")
public class MedicalJournal {
    
    @Id
    @Column(name = "socialSecurityNumber", unique = true)
    String socialSecurityNumber;

    @Column(name = "patientName")
    String patientName;

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }
}

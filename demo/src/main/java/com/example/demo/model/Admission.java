package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admission")
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admissionId")
    Integer id;

    @Column(name = "department")
    String department;

    @Column(name = "patientSocialSecurityNumber")
    String patientSocialSecurityNumber;

    @ManyToMany
    @JoinTable(name = "doctorAdmission", joinColumns = @JoinColumn(name = "admissionId"), inverseJoinColumns = @JoinColumn(name = "doctorId"))
    List<Doctor> doctors;
    
    @OneToOne
    @JoinColumn(name = "medicalJournalId")
    MedicalJournal medicalJournal;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPatientSocialSecurityNumber(String patientSocialSecurityNumber) {
        this.patientSocialSecurityNumber = patientSocialSecurityNumber;
    }

    public String getPatientSocialSecurityNumber() {
        return patientSocialSecurityNumber;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setMedicalJournal(MedicalJournal medicalJournal) {
        this.medicalJournal = medicalJournal;
    }

    public MedicalJournal getMedicalJournal() {
        return medicalJournal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

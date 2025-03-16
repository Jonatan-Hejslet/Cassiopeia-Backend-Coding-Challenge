package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "doctor")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctorId")
    Integer id;

    @Column(name = "doctorName")
    String name;

    @Column(name = "department")
    String department;
    
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepo;



@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
    
    @Autowired
    DoctorRepo doctorRepo;;

    @PostMapping("/addDoctor")
    Doctor addDoctor(@RequestBody Doctor doctor) {
        doctorRepo.save(doctor);
        return doctor;
    }

    @GetMapping("/getAll")
    List<Doctor> getDoctors() {
        List<Doctor> doctor = doctorRepo.findAll();
        return doctor;
    }
    
    
}

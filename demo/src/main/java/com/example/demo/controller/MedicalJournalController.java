package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MedicalJournal;
import com.example.demo.repository.MedicalJournalRepo;

@RestController
@RequestMapping(value = "/medicalJournal")
public class MedicalJournalController {
    
    @Autowired
    MedicalJournalRepo medicalJournalRepo;

    @PostMapping("/addMedicalJournal")
    MedicalJournal addMedicalJournal(@RequestBody MedicalJournal medicalJournal) {
        medicalJournalRepo.save(medicalJournal);
        return medicalJournal;
    }

    @GetMapping("/getMedicalJournal")
    MedicalJournal getMedicalJournal(@RequestParam String id) {
        Optional<MedicalJournal> medicalJournal = medicalJournalRepo.findById(id);
        return medicalJournal.orElse(null);
    }
}

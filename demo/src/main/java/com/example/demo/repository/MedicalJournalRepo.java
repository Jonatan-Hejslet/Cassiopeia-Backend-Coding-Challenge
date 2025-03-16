package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MedicalJournal;

public interface MedicalJournalRepo extends JpaRepository<MedicalJournal, String> {
    
}

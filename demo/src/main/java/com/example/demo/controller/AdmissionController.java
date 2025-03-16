package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admission;
import com.example.demo.model.Doctor;
import com.example.demo.model.MedicalJournal;
import com.example.demo.repository.AdmissionRepo;
import com.example.demo.repository.DoctorRepo;
import com.example.demo.repository.MedicalJournalRepo;






@RestController
@RequestMapping(value = "/admission")
public class AdmissionController {
    
    @Autowired
    AdmissionRepo admissionRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    MedicalJournalRepo medicalJournalRepo;
    
    @PostMapping("/addAdmission")
    Admission addAdmission(@RequestBody Admission admission) {
        List<Integer> doctorIds = admission.getDoctors().stream().map(Doctor::getId).collect(Collectors.toList());
        List<Doctor> doctors = doctorRepo.findAllById(doctorIds);
        MedicalJournal medicalJournal = medicalJournalRepo.findById(admission.getPatientSocialSecurityNumber()).orElse(null);
        admission.setDoctors(doctors);
        admission.setMedicalJournal(medicalJournal);
        String department = doctors.stream().map(Doctor::getDepartment).findFirst().orElse(null);
        admission.setDepartment(department);
        admissionRepo.save(admission);
        return admission;
    }   

    @GetMapping("/getAdmissionById")
    public Admission getAdmissionById(@RequestParam("id") Integer admissionId) {
        return admissionRepo.findById(admissionId)
                            .orElseThrow(() -> new RuntimeException("Admission not found with ID: " + admissionId));
 
    }

    @GetMapping("/getAllAdmissions")
    public List<Admission> getAllAdmissions() {
        List<Admission> admissions = admissionRepo.findAll();
        return admissions;
    }

    @GetMapping("/checkDoctorAccess")
    public ResponseEntity<Boolean> checkDoctorAccess(
            @RequestParam("id") Integer doctorId,
            @RequestParam("socialSecurityNumber") String patientSsn) {

        // 1. Make sure the doctor exists
        Optional<Doctor> doctorOpt = doctorRepo.findById(doctorId);
        if (!doctorOpt.isPresent()) {
            return ResponseEntity.ok(false);
        }

        // 2. Make sure the medical journal exists
        Optional<MedicalJournal> mjOpt = medicalJournalRepo.findById(patientSsn);
        if (!mjOpt.isPresent()) {
            return ResponseEntity.ok(false);
        }

        // 3. Find admissions for the given patient
        //    Option A: If you have a custom query: 
        //       List<Admission> admissions = admissionRepo.findAllByPatientSocialSecurityNumber(patientSsn);
        //    Option B: If you do NOT have that, fetch all admissions and filter in code:
        List<Admission> allAdmissions = admissionRepo.findAll();
        List<Admission> patientAdmissions = allAdmissions.stream()
                .filter(a -> a.getPatientSocialSecurityNumber().equals(patientSsn))
                .collect(Collectors.toList());

        // 4. Check if the doctor is listed on any Admission for that patient
        for (Admission admission : patientAdmissions) {
            boolean isDoctorFound = admission.getDoctors().stream()
                    .anyMatch(d -> d.getId().equals(doctorId));
            if (isDoctorFound) {
                return ResponseEntity.ok(true); // The doctor has access
            }
        }

        // If we get here, the doctor wasn't listed in any relevant admissions
        return ResponseEntity.ok(false);
    }

    @GetMapping("/getPatientsForDoctor")
    public List<MedicalJournal> getPatientsForDoctor(@RequestParam("id") Integer doctorId) {
    
        // 1. Fetch all admissions
        List<Admission> allAdmissions = admissionRepo.findAll();
    
        // 2. Filter to only admissions that contain the given doctorId
        List<Admission> doctorAdmissions = allAdmissions.stream()
        .filter(admission -> admission.getDoctors().stream()
            .anyMatch(d -> d.getId().equals(doctorId)))
        .collect(Collectors.toList());

        // 3. Extract the MedicalJournal (the patient) from each Admission
        //    and collect them into a distinct list (in case the same patient is found multiple times).
        List<MedicalJournal> patients = doctorAdmissions.stream()
        .map(Admission::getMedicalJournal)
        .distinct()  // remove duplicates
        .collect(Collectors.toList());
    
        return patients;
    }

    @GetMapping("/getDoctorsForPatient")
    public List<Doctor> getDoctorsForPatient(@RequestParam("socialSecurityNumber") String patientSsn) {
        // 1. Fetch all admissions
        List<Admission> allAdmissions = admissionRepo.findAll();

        // 2. Filter to only admissions for the given patient’s SSN
        List<Admission> patientAdmissions = allAdmissions.stream()
            .filter(admission -> admission.getPatientSocialSecurityNumber().equals(patientSsn))
            .collect(Collectors.toList());

        // 3. Gather all doctors from these admissions
        //    and return a unique set (so the same doctor isn’t repeated multiple times).
        return patientAdmissions.stream()
            .flatMap(admission -> admission.getDoctors().stream())
            .distinct()
            .collect(Collectors.toList());
    }
}

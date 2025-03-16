# Spring Boot Admissions Project

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Contact](#contact)

## Introduction

This project manages:

Admissions: Represents the admission, linking one or more Doctors to a Patient’s Medical Journal.

Doctors: Contains basic information about each doctor (e.g., name, department, doctor id).

Medical Journals: Tied to a patient containing social security number and patient name

The project is built using Spring Boot and MySQL.

## Features
Add a New Doctor: Introduces a new doctor record into the system’s database.

Endpoint call example:
POST http://localhost:8080/doctor/addDoctor

json example: 
{
    "name":"Dr. God",
    "department":"Hematologisk"
}


Add a New Medical Journal: Introduces a new patient record into the system’s database, identified by their social security number.

Endpoint call example:
POST http://localhost:8080/medicalJournal/addMedicalJournal

json example: 
{
    "socialSecurityNumber":"6666666666",
    "patientName":"Rune Rask"
}

Add a New Admission: Create an admission that associates doctors with a patient’s medical journal.

Endpoint call example:
POST http://localhost:8080/admission/addAdmission

json example: 
{
    "patientSocialSecurityNumber":"6666666666",
    "doctors":[{"id": 1}],
    "medicalJournal":{"id": "6666666666"}
}

Fetch Admission by ID: Retrieve a specific admission using its unique identifier.

Endpoint call exammple:
GET http://localhost:8080/admission/getAdmissionById?id=2

id is the admissions id

List All Admissions: Get a list of all admissions in the system.

Endpoint call:
GET http://localhost:8080/admission/getAllAdmissions

Check Doctor Access: Verify whether a specific doctor can access a given patient’s medical journal.

Endpoint call example:
GET http://localhost:8080/admission/checkDoctorAccess?id=2&socialSecurityNumber=4444444444

id is doctorId and socialSecurityNumber is describing a medical journal

List All Patients for a Doctor: Fetch all patients (medical journals) associated with a particular doctor.

Endpoint call:
GET http://localhost:8080/admission/getPatientsForDoctor?id=2

id is a doctorId

List All Doctors for a Patient: Fetch all doctors associated with a given patient’s social security number.

Endpoint call example:
http://localhost:8080/admission/getDoctorsForPatient?socialSecurityNumber=6666666666


## Contact
Name: Jonatan Hejslet
Email: Jonatan.Hejslet@gmail.com
GitHub: https://github.com/Jonatan-Hejslet
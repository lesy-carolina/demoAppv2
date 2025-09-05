package com.example.demoAppv2.service;

import com.example.demoAppv2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demoAppv2.repository.Patient;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service

public class PatientService {
    private static final Logger log = LoggerFactory.getLogger(PatientService.class);;
    //logger log= LoggerFactory.getlogger(PatientService.class);

    @Autowired

    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        log.info("aqui estamos en el service !!");
                List<Patient> resulPatient = patientRepository.findAll();

        return resulPatient;
    }
    public Patient getOnePatient (String nro) {
        log.info("aqui estamos en el service !!");
        Patient onePatient = new Patient();
        List<Patient> resultPatients = patientRepository.findAll();
        for (Patient p : resultPatients) {
            if(p.getDocument().equals(nro))
            {
                onePatient=p;
                break;
            }
        }
        return onePatient;
    }

    public List<Patient> getAllPatients(String genero, int edad) {
        log.info("aqui estamos en el service !!");
        Patient onePatient = new Patient();
        List<Patient> resultPatient = patientRepository.findAll();
        List<Patient> lstPatients = new ArrayList<>();
        for (Patient p : resultPatient) {
            if(p.getGender().equals(genero)&& p.getAge()>edad)
            {
                lstPatients.add(p);
            }
        }
        return lstPatients;
    }

    public List<Patient> getAllAgePatients(int edad) {
        log.info("aqui estamos en el service !!");
        Patient onePatient = new Patient();
        List<Patient> resultPatient = patientRepository.findAll();
        List<Patient> lstPatients = new ArrayList<>();
        for (Patient p : resultPatient) {
            if( p.getAge()>edad)
            {
                lstPatients.add(p);
            }
        }
        return lstPatients;
    }



    public void saveOnePatient(Patient patient) {
        this.patientRepository.save(patient);
        log.info("Aqui se guarda un paciente", patient);
    }
////---------------
    public List<Patient> getAllPatientsCustomv1(String gender, int age) {
        log.info("aqui estamos en el service getAllAgePatientsCustomv1!!");

     return patientRepository.findByGenderAndAgeGreaterThan(gender, age);

    }
    public Patient saveOnePatienv2(Patient p) {
        log.info("aqui se guarda un paciente saveOnePatienv2!!");

        return this.patientRepository.save(p);

    }
    public Patient updateOnePatient(long id, Patient p) {

        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente no se encuentra" + id));
        patient.setFullName(p.getFullName());
        patient.setDocument(p.getDocument());
        patient.setAge(p.getAge());
        patient.setGender(p.getGender());
        return this.patientRepository.save(patient);
    }
    public void deleteOnePatient(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente no se encuentra" + id));
         this.patientRepository.delete(patient);

    }
    public Patient getPatientByFullName(String fullName) {
        log.info("aqui estamos en el service getPatientByFullName!!");
        return this.patientRepository.findByDocument(fullName);

    }
    public void logUserSession(String username) {
        // guarda un registro de que este usuario
        System.out.println("Usuario " + username + " ha accedido a la sesión.");
        // interactuar con una base de datos para registrar la actividad
    }

    public String getWelcomeMessage(String username) {
        logUserSession(username); // Llamas al método de registro aquí
        return "Bienvenido: " + username;
    }









}

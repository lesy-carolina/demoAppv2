package com.example.demoAppv2.controller;

import com.example.demoAppv2.repository.Patient;
import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demoAppv2.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;


@RestController
@RequestMapping ("/patients")

public class PatientContoller {


    //logger log= LoggerFactory.getLogger(PatientContoller.class);
    private static final Logger log = LoggerFactory.getLogger(PatientContoller.class);
    private final Faker faker = new Faker( new Locale("es") );
    private final Random random = new Random();

    @Autowired

    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        log.info("aqui estamos en el controller !!");
        return patientService.getAllPatients();
    }
    @PostMapping("/fake")
    public Patient creationNewPatient() {
        Patient patient = new Patient();
        patient.setFullName(faker.name().fullName());
        patient.setDocument(faker.number().digits(8));
        patient.setAge(10 + random.nextInt(70));
        patient.setGender(random.nextBoolean() ? "M" : "F");
        this.patientService.saveOnePatient(patient);
        return patient;

    }
}

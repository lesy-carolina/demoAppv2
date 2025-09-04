package com.example.demoAppv2.service;

import com.example.demoAppv2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demoAppv2.repository.Patient;
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
                List<Patient> resulPatients = patientRepository.findAll();

        return patientRepository.findAll();
    }
    public void saveOnePatient(Patient patient) {
        this.patientRepository.save(patient);
        log.info("Aqui se guarda un paciente", patient);
    }


}

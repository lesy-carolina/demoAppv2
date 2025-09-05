package com.example.demoAppv2.controller;
import com.example.demoAppv2.repository.Patient;
import com.github.javafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demoAppv2.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;


@RestController
@RequestMapping ("/patients")

public class PatientController {
    //
    private static final Logger log = LoggerFactory.getLogger(PatientController.class);
    private final Faker faker = new Faker( new Locale("es"));
    private final Random random = new Random();

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        log.info("aqui estamos en el controller !!");
        return patientService.getAllPatients();
    }
    @GetMapping("/buscar")
    public Patient getOnePatient(@RequestParam String nro) {
        if(nro.length()> 0) {
        return patientService.getOnePatient(nro);
        }
        /// ///
        return null;
    }
    @GetMapping("/buscarv2")
    public List<Patient>   getOnePatientv2(@RequestParam("otro") String genero,@RequestParam("edad") int edad)
    {
        if (genero.length()> 0) {
            return patientService.getAllPatients(genero,edad);
        }
        /// ///
        return null;
    }

    @GetMapping("/buscarv3")
    public Patient getOnePatientv3(@RequestParam ("otrov3") String otrov2)
    {
        if(otrov2.length()> 0) {
            return patientService.getOnePatient(otrov2);
        }
        /// ///
        return null;
    }

    @GetMapping("/buscarv4/{edad}")
    public List<Patient>  getOnePatientv4(@PathVariable("edad") int edad)
    {
        if(edad> 0) {
            return patientService.getAllAgePatients(edad);
        }

        return null;
    }

    @GetMapping("/buscarv5/{gender}/{edad}")
    public List<Patient>  getOnePatientv5(@PathVariable("gender") String gender, @PathVariable("edad") int edad)
    {
        if(edad> 0) {
            return patientService.getAllPatientsCustomv1(gender,edad);
        }

        return null;
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

    @PostMapping
    public Patient create (@RequestBody Patient p) {


        return this.patientService.saveOnePatienv2(p);
    }
    @PutMapping("/{id}")
    public Patient update (@PathVariable Long id,@RequestBody  Patient p) {


        return this.patientService.updateOnePatient(id,p);
    }
    @DeleteMapping
    public void delete(@PathVariable Long id) {
        this.patientService.deleteOnePatient(id);
    }
    @GetMapping("/buscarPorNombre")
    public Patient getPatientByFullName(@RequestParam String nombre) {
        return patientService.getPatientByFullName(nombre);
    }
    @GetMapping("/sesion")
    public ResponseEntity<String> sesion(@CookieValue(name = "usuario", defaultValue = "invitado") String usuario) {
        String mensaje = patientService.getWelcomeMessage(usuario);
        return ResponseEntity.ok().body(mensaje);

    }

    @GetMapping("/setCookie")
    public ResponseEntity<String> setCookie(@RequestParam String usuario) {
        String mensaje = "Cookie 'usuario' establecida con el valor: " + usuario;
        return ResponseEntity.ok()
                .header("Set-Cookie", "usuario=" + usuario + "; Path=/; HttpOnly")
                .body(mensaje);
    }



}

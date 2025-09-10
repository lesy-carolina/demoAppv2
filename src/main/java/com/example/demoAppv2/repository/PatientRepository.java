package com.example.demoAppv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository  extends JpaRepository <Patient, Long> {

    List<Patient> findByGenderAndAgeGreaterThan(String gender, int age);
    List<Patient> findByAgeLessThan(int age);

    //filtrar por un documento
   Patient findByDocument(String document);

//JPQL
    @Query("SELECT p FROM Patient p WHERE LOWER(p.fullName) LIKE LOWER(CONCAT( :nombre, '%'))")
    List<Patient> buscarPorNombre(@Param("nombre") String nombre);







}

package com.example.demoAppv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository <Patient, Long> {
}

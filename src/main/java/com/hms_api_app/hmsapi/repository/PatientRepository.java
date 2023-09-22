package com.hms_api_app.hmsapi.repository;

import com.hms_api_app.hmsapi.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}

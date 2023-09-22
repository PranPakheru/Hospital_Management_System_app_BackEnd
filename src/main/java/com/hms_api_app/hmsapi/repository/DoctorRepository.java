package com.hms_api_app.hmsapi.repository;

import com.hms_api_app.hmsapi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

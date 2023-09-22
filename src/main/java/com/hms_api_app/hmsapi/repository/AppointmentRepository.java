package com.hms_api_app.hmsapi.repository;

import com.hms_api_app.hmsapi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(long patientId);
}

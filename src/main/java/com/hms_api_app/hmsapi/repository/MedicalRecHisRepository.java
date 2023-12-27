package com.hms_api_app.hmsapi.repository;
import com.hms_api_app.hmsapi.entity.Medical_Record_History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecHisRepository extends JpaRepository<Medical_Record_History, Long> {

   List<Medical_Record_History> findByPatientId(long patientId);
}

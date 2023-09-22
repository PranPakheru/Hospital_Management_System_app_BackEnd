package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.PatientDto;
import com.hms_api_app.hmsapi.entity.Patient;


import java.util.List;

public interface PatientService {

    PatientDto createPatient(PatientDto patientDto);
    List<PatientDto> getAllPatients();
    List<PatientDto> getOnePatientById(long id);
    List<PatientDto> updateThePatient(long id, PatientDto updatedPatient);
    String deleteById(long id);
}

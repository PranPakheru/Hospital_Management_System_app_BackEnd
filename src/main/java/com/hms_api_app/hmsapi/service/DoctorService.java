package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> createDoctor(DoctorDto doctordto);
    List<DoctorDto> getAllDoctors();
    List<DoctorDto> getOneDoctor(long id);
    List<DoctorDto> updateTheDoctor(long id, DoctorDto doctorDto);
    String deleteTheDoctor(long id);
}

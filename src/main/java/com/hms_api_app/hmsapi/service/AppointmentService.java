package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> saveAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> getAppointmentByPatientId(long patientId);
    String deleteAppointmentPatient(long id);
}

package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.AppointmentDto;
import com.hms_api_app.hmsapi.entity.Appointment;
import com.hms_api_app.hmsapi.errorHandler.ResourceNotFoundException;
import com.hms_api_app.hmsapi.repository.AppointmentRepository;
import com.hms_api_app.hmsapi.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    //other class objects.
    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientRepository patientRepo;

    //entity-dto mapping methods.
    private Appointment mapToEntity(AppointmentDto appointmentDto){
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        return appointment;
    }

    private AppointmentDto mapToDto(Appointment appointment){
        AppointmentDto appointmentDto = modelMapper.map(appointment, AppointmentDto.class);
        return appointmentDto;
    }


    //other implementation methods.
    @Override
    public List<AppointmentDto> saveAppointment(AppointmentDto appointmentDto) {
        Appointment newAppointment = mapToEntity(appointmentDto);
        Appointment savedAppointment = appointmentRepo.save(newAppointment);
        AppointmentDto savedDto = mapToDto(savedAppointment);

        List<AppointmentDto> listDto = new ArrayList<>();
        listDto.add(savedDto);
        return listDto;
    }

    @Override
    public List<AppointmentDto> getAppointmentByPatientId(long patientId) {
        patientRepo.findById(patientId).orElseThrow(()-> new ResourceNotFoundException(
                "Patient appointment", "id", patientId
        ));

        List<Appointment> byPatientId = appointmentRepo.findByPatientId(patientId);
        List<AppointmentDto> collect = byPatientId.stream().map(this::mapToDto).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String deleteAppointmentPatient(long id) {
//        Optional<Appointment> byId = appointmentRepo.findById(id);
//        if(byId.isPresent()){
//            appointmentRepo.deleteById(id);
//            return "Appointment deleted successfully.";
//        }
//        else{
//            return "Appointment not found!";
//        }

        appointmentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(
                "Appointment", "id", id
        ));
        appointmentRepo.deleteById(id);
        return "Appointment deleted successfully.";
    }
}

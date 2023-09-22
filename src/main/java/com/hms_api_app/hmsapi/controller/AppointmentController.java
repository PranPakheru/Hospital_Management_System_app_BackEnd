package com.hms_api_app.hmsapi.controller;

import com.hms_api_app.hmsapi.dto.AppointmentDto;
import com.hms_api_app.hmsapi.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    //other class objects.
    @Autowired
    private AppointmentService appointmentService;


    //Controller methods for CRUD.
    //localhost:8080/api-HMS/appointment/bookAppointment
    @PostMapping("/bookAppointment")
    public ResponseEntity<List<AppointmentDto>> bookAnAppointment(@RequestBody AppointmentDto appointmentDto){
        List<AppointmentDto> bookedAppointment = appointmentService.saveAppointment(appointmentDto);
        return new ResponseEntity<>(bookedAppointment, HttpStatus.CREATED);
    }

    //localhost:8080/api-HMS/appointment/getAppointmentByPatientId/{patientId}
    @GetMapping("/getAppointmentByPatientId/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getAppointment(@PathVariable long patientId){
        List<AppointmentDto> gotAppointment = appointmentService.getAppointmentByPatientId(patientId);
        return new ResponseEntity<>(gotAppointment, HttpStatus.FOUND);
    }

    //localhost:8080/api-HMS/appointment/deleteById/{id}
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable long id){
        String msg = appointmentService.deleteAppointmentPatient(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}

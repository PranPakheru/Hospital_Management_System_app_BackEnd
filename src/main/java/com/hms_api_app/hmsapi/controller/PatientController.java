package com.hms_api_app.hmsapi.controller;

import com.hms_api_app.hmsapi.dto.PatientDto;
import com.hms_api_app.hmsapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    //all class objects
    @Autowired
    private PatientService patientService;


    //all routing/mapping methods
    //localhost:8080/api-HMS/patient/registrationPage
    @PostMapping("/registrationPage")
    public ResponseEntity<Object> createPatient(@RequestBody PatientDto patientDto){
        PatientDto savedPatientDto = patientService.createPatient(patientDto);
        return new ResponseEntity<Object>(savedPatientDto, HttpStatus.CREATED);

    }

    //localhost:8080/api-HMS/patient/showPatients
    @GetMapping("/showPatients")
    public ResponseEntity<List<PatientDto>> showAllPatients(){
        List<PatientDto> listPatients = patientService.getAllPatients();
        return new ResponseEntity<>(listPatients, HttpStatus.OK);
    }

    //localhost:8080/api-HMS/patient/showPatientById/{id}
    @GetMapping("/showPatientById/{id}")
    public ResponseEntity<List<PatientDto>> showPatientById(@PathVariable long id){
        List<PatientDto> onePatientById = patientService.getOnePatientById(id);
        return new ResponseEntity<>(onePatientById, HttpStatus.OK);
    }

    //localhost:8080/api-HMS/patient/updatePatient/{id}
    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<List<PatientDto>> updatePatientById(@PathVariable long id, @RequestBody PatientDto updatedPatient){
        List<PatientDto> getPatient = patientService.updateThePatient(id, updatedPatient);
        return new ResponseEntity<>(getPatient, HttpStatus.CREATED);
    }

    //localhost:8080/api-HMS/patient/deleteById/{id}
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable long id){
        String msg = patientService.deleteById(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}

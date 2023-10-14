package com.hms_api_app.hmsapi.controller;

import com.hms_api_app.hmsapi.dto.PatientDto;
import com.hms_api_app.hmsapi.dto.PatientResponse;
import com.hms_api_app.hmsapi.service.PatientService;
import com.hms_api_app.hmsapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrationPage")
    public ResponseEntity<Object> createPatient(@Valid @RequestBody PatientDto patientDto){
        PatientDto savedPatientDto = patientService.createPatient(patientDto);
        return new ResponseEntity<Object>(savedPatientDto, HttpStatus.CREATED);

    }

    //localhost:8080/api-HMS/patient/showPatients
//    @GetMapping("/showPatients")
//    public ResponseEntity<List<PatientDto>> showAllPatients(){
//        List<PatientDto> listPatients = patientService.getAllPatient();
//        return new ResponseEntity<>(listPatients, HttpStatus.OK);
//    }

    //localhost:8080/api-HMS/patient/showPatientById/{id}
    @GetMapping("/showPatientById/{id}")
    public ResponseEntity<List<PatientDto>> showPatientById(@PathVariable long id){
        List<PatientDto> onePatientById = patientService.getOnePatientById(id);
        return new ResponseEntity<>(onePatientById, HttpStatus.OK);
    }

    //localhost:8080/api-HMS/patient/showAllPatients?pageNo=1&pageSize=10&sortBy=id&sortDir=asc
    @GetMapping("/showAllPatients")
    public ResponseEntity<PatientResponse> getAllPatients(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        PatientResponse patientResponse = patientService.getAllPatients(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(patientResponse, HttpStatus.OK);
    }

    //localhost:8080/api-HMS/patient/updatePatient/{id}
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<List<PatientDto>> updatePatientById(@Valid @PathVariable long id, @RequestBody PatientDto updatedPatient){
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

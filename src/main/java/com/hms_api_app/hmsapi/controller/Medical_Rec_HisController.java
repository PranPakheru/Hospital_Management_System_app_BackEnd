package com.hms_api_app.hmsapi.controller;

import com.hms_api_app.hmsapi.dto.Medical_Record_HistoryDto;
import com.hms_api_app.hmsapi.service.MedicalRecHisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecHis")
public class Medical_Rec_HisController {

    //other class objects.
    @Autowired
    private MedicalRecHisService medService;


    //Controlling CRUD methods.
    //localhost:8080/api-HMS/medicalRecHis/showMedicalRecHis
    @PostMapping("/showMedicalRecHis")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<Medical_Record_HistoryDto>> createMedical(@Valid @RequestBody Medical_Record_HistoryDto medDto){
        List<Medical_Record_HistoryDto> newMed = medService.createMedRecHis(medDto);
        return new ResponseEntity<>(newMed, HttpStatus.CREATED);
    }

    //localhost:8080/api-HMS/medicalRecHis/getMedRecByPatientId/{patientId}
    @GetMapping("/getMedRecByPatientId/{patientId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<Medical_Record_HistoryDto>> getMedRecHis(@PathVariable long patientId){
        List<Medical_Record_HistoryDto> listRecords = medService.getRecordsById(patientId);
        return new ResponseEntity<>(listRecords, HttpStatus.OK);
    }

    //localhost:8080/api-HMS/medicalRecHis/deleteMedRecHisById/{id}
    @DeleteMapping("/deleteMedRecHisById/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> deleteMedRedHis(@PathVariable long id){
        String msg = medService.deleteMedRecHisById(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}

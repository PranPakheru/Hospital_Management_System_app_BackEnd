package com.hms_api_app.hmsapi.controller;

import com.hms_api_app.hmsapi.dto.DoctorDto;
import com.hms_api_app.hmsapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    //all other class object creation.
    @Autowired
    private DoctorService doctorService;


    //all controller method for doctor CRUD.
    //localhost:8080/api-HMS/doctor/saveDoctor
    @PostMapping("/saveDoctor")
    public ResponseEntity<List<DoctorDto>> createNewDoctor(@RequestBody DoctorDto doctorDto){
        List<DoctorDto> savedDoctorList = doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(savedDoctorList, HttpStatus.CREATED);
    }

    //localhost:8080/api-HMS/doctor/showAllDoctors
    @GetMapping("/showAllDoctors")
    public ResponseEntity<List<DoctorDto>> showAllDoctors(){
        List<DoctorDto> allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors, HttpStatus.FOUND);
    }

    //localhost:8080/api-HMS/doctor/showDoctorById/{id}
    @GetMapping("/showDoctorById/{id}")
    public ResponseEntity<List<DoctorDto>> showDoctorById(@PathVariable long id){
        List<DoctorDto> oneDoctor = doctorService.getOneDoctor(id);
        return new ResponseEntity<>(oneDoctor, HttpStatus.FOUND);
    }

    //localhost:8080/api-HMS/doctor/updateDoctorData/{id}
    @PutMapping("/updateDoctorData/{id}")
    public ResponseEntity<List<DoctorDto>> updateDoctorData(@PathVariable long id, @RequestBody DoctorDto doctorDto){
        List<DoctorDto> updatedDoctor = doctorService.updateTheDoctor(id, doctorDto);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.ACCEPTED);
    }

    //localhost:8080/api-HMS/doctor/deleteById/{id}
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable long id){
        String msg = doctorService.deleteTheDoctor(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}

package com.hms_api_app.hmsapi.controller;

import com.hms_api_app.hmsapi.dto.BillDto;
import com.hms_api_app.hmsapi.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    //all other class objects.
    @Autowired
    private BillService billService;


    //CRUD methods for billing.
    //localhost:8080/api-HMS/bills/showBills
    @PostMapping("/showBills")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<BillDto>> createBill(@Valid @RequestBody BillDto billDto){
        List<BillDto> listBill = billService.createBillPatient(billDto);
        return new ResponseEntity<>(listBill, HttpStatus.CREATED);
    }

    //localhost:8080/api-HMS/bills/getRequiredBill/{patientId}
    @GetMapping("/getRequiredBill/{patientId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<BillDto>> getBillByPatientId(@PathVariable long patientId){
        List<BillDto> listBill = billService.getBills(patientId);
        return new ResponseEntity<>(listBill, HttpStatus.FOUND);
    }

    //localhost:8080/api-HMS/bills/deleteBills/{id}
    @DeleteMapping("/deleteBills/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> deleteBills(@PathVariable long id){
        String msg = billService.deleteBillById(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}

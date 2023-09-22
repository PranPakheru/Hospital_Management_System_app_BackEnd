package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.BillDto;
import com.hms_api_app.hmsapi.entity.Bill;

import java.util.List;

public interface BillService {

    List<BillDto> createBillPatient(BillDto billDto);
    List<BillDto> getBills(long patientId);
    String deleteBillById(long id);
}

package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.Medical_Record_HistoryDto;
import com.hms_api_app.hmsapi.entity.Medical_Record_History;

import java.util.List;

public interface MedicalRecHisService {
    List<Medical_Record_HistoryDto> createMedRecHis(Medical_Record_HistoryDto medDto);
    List<Medical_Record_HistoryDto> getRecordsById(long patientId);
    String deleteMedRecHisById(long id);
}

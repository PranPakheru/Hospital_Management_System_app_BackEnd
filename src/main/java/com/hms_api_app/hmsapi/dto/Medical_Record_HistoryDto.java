package com.hms_api_app.hmsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medical_Record_HistoryDto {

    private long id;
    private String illnessHistory;
    private String illnessDiagnosis;
    private String illnessTreatment;
    private String currentIllness;
    private long patientId;
    private String doctorName;

}

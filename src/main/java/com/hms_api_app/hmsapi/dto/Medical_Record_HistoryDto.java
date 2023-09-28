package com.hms_api_app.hmsapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(min = 1, message = "Patient name should at least be 1 character!")
    private String currentIllness;

    @NotNull
    private long patientId;

    @NotNull
    @Size(min = 1, message = "Patient name should at least be 1 character!")
    private String doctorName;

}

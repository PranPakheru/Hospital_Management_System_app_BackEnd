package com.hms_api_app.hmsapi.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private long id;

    @NotNull
    private Date appointmentDateTime;

    @NotNull
    private long patientId;

    @NotNull
    @Size(min = 1, message = "Patient name should at least be 1 character!")
    private String doctorName;
}

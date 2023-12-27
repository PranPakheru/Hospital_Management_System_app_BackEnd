package com.hms_api_app.hmsapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private long id;

    @NotNull
    private Date billingDate;

    @NotNull
    private double amount;

    private String remark;

    @NotNull
    private long patientId;

}

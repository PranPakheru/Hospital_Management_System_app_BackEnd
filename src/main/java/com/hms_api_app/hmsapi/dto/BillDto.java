package com.hms_api_app.hmsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    private long id;
    private Date billingDate;
    private double amount;
    private String remark;
    private long patientId;

}

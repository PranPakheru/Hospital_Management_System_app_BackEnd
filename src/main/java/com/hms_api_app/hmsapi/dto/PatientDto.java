package com.hms_api_app.hmsapi.dto;

import com.hms_api_app.hmsapi.entity.Medical_Record_History;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long mobileNumber;
    private String address;
    private Date dateOfBirth;

}

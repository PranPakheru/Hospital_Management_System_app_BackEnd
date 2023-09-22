package com.hms_api_app.hmsapi.dto;

import com.hms_api_app.hmsapi.entity.Medical_Record_History;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private long id;
    private String doctorName;
    private String designation;
    private String email;
    private long mobileNumber;
    private Date dateOfBirth;
    private List<Medical_Record_History> medRecHisListDoc;
}

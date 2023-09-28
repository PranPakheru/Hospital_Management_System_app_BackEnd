package com.hms_api_app.hmsapi.dto;

import com.hms_api_app.hmsapi.entity.Medical_Record_History;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(min = 1, message = "Patient name should at least be 1 character!")
    private String doctorName;

    @NotNull
    @Size(min = 1, message = "Patient name should at least be 1 character!")
    private String designation;

    @Email
    private String email;

    @NotNull
    private long mobileNumber;

    @NotNull
    private Date dateOfBirth;

}

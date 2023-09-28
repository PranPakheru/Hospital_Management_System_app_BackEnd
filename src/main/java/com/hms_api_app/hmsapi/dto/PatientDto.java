package com.hms_api_app.hmsapi.dto;

import com.hms_api_app.hmsapi.entity.Medical_Record_History;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private long id;

    @NotNull
    @Size(min = 1, message = "Patient name should at least be 1 character!")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "Patient name should at least be 1 character!")
    private String lastName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private long mobileNumber;

    @NotNull
    @Size(min = 10, message = "Enter full address!")
    private String address;

    @NotNull
    private Date dateOfBirth;

}

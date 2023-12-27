package com.hms_api_app.hmsapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 1, message = "Please enter proper username!")
    private String userNameOrEmail;

    @NotNull
    @Size(min = 1, message = "Please enter correct password!")
    private String password;

    private String adminKey;

}

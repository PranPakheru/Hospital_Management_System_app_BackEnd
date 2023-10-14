package com.hms_api_app.hmsapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;

    @NotNull
    @Size(min = 1, message = "User's name should at least be 1 character!")
    private String name;

    @NotNull
    @Size(min = 1, message = "User's user name should at least be 1 character!")
    private String userName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

}

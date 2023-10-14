package com.hms_api_app.hmsapi.controller;

import com.hms_api_app.hmsapi.dto.UserDto;
import com.hms_api_app.hmsapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //other class objects
    @Autowired
    private UserService userService;


    //controller methods
    //http://localhost:8080/api-HMS/auth/sign-up
    @PostMapping("/sign-up")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto){
        ResponseEntity<?> user = userService.createUser(userDto);
        String body = user.getBody().toString();
        HttpStatusCode statusCode = user.getStatusCode();

        return new ResponseEntity<>(body, statusCode);
    }
}

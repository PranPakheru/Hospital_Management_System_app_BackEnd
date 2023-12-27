package com.hms_api_app.hmsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hms_api_app.hmsapi.dto.LoginDto;
import com.hms_api_app.hmsapi.dto.UserDto;
import com.hms_api_app.hmsapi.service.LoginService;
import com.hms_api_app.hmsapi.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LogInController {
    
    //other class objects
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;


    //controller methods

    //http://localhost:8080/api-HMS/auth/registerUser
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    //http://localhost:8080/api-HMS/auth/userLogin
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/userLogin")
    public ResponseEntity<List<String>> userLogin(@Valid @RequestBody LoginDto loginDto){

        List<String> msg = loginService.login(loginDto);
        if(msg.get(0) == null){
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }

    // //http://localhost:8080/api-HMS/auth/adminLogin
    // @PostMapping("/adminLogin")
    // public ResponseEntity<List<String>> adminLogin(@Valid @RequestBody LoginDto loginDto){

    //     List<String> msg = loginService.login(loginDto);
    //     if(msg.get(0) == null){
    //         return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    //     }
    //     else{
    //         return new ResponseEntity<>(msg, HttpStatus.OK);
    //     }
    // }
}

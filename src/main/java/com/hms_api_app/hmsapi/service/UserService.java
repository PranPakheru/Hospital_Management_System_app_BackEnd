package com.hms_api_app.hmsapi.service;

import org.springframework.http.ResponseEntity;

import com.hms_api_app.hmsapi.dto.UserDto;


public interface UserService {

    ResponseEntity<String> createUser(UserDto userDto);
}

package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> createUser(UserDto userDto);
}

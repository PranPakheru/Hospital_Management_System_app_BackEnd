package com.hms_api_app.hmsapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hms_api_app.hmsapi.config.BeanConfig;
import com.hms_api_app.hmsapi.dto.LoginDto;
import com.hms_api_app.hmsapi.entity.User;
import com.hms_api_app.hmsapi.repository.UserRepository;

@Service
public class LoginService {
    
    //other class objects
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BeanConfig beanConfig;

    //variable from .env
    @Value("${SPECIAL_KEY}")
    private String specialKey;
    

    public List<String> login(LoginDto loginDto){

        List<User> allUsers = userRepo.findAll();
        List<String> result = new ArrayList<>();

        for(User user : allUsers){
            if( user.getUserName().equals(loginDto.getUserNameOrEmail()) ||
                 user.getEmail().equals(loginDto.getUserNameOrEmail()))
            {
                // String storedPass = user.getPassword();
                boolean res = beanConfig.passwordEncoder().matches(loginDto.getPassword(), user.getPassword());

                if(res == true){

                    if(user.getRoles().equals("ADMIN") && specialKey.equals(loginDto.getAdminKey())){
                        result.add(user.getUserName());
                        result.add(user.getRoles());
                        return  result;
                    }
                    else if(user.getRoles().equals("USER")){
                        result.add(user.getUserName());
                        result.add(user.getRoles());
                        return  result;
                    }
                }
            }
        }

        result.add(null);
        return result;
        
    }
}

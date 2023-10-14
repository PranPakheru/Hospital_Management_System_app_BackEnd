package com.hms_api_app.hmsapi.service;

import com.hms_api_app.hmsapi.config.BeanConfig;
import com.hms_api_app.hmsapi.dto.UserDto;
import com.hms_api_app.hmsapi.entity.Role;
import com.hms_api_app.hmsapi.entity.User;
import com.hms_api_app.hmsapi.repository.RoleRepository;
import com.hms_api_app.hmsapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService{

    //other class objects.
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private RoleRepository roleRepo;


    //mapper methods dto-entity.
//    private User mapToEntity(UserDto userDto){
//        User user = modelMapper.map(userDto, User.class);
//        return user;
//    }
//
//    private UserDto mapToDto(User user){
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//        return userDto;
//    }



    //other implementation methods.
    //registering the user
    @Override
    public ResponseEntity<?> createUser(UserDto userDto) {
        if(userRepo.existsByUserName(userDto.getUserName())){
            return new ResponseEntity<>("user already exists by- " +userDto.getUserName(), HttpStatus.BAD_REQUEST);
        }
        else if(userRepo.existsByEmail(userDto.getEmail())){
            return new ResponseEntity<>("email already exists by- " +userDto.getEmail(), HttpStatus.BAD_REQUEST);
        }
        else{
            User user = new User();
            user.setName(userDto.getName());
            user.setUserName(userDto.getUserName());
            user.setEmail(userDto.getEmail());
            user.setPassword(beanConfig.passwordEncoder().encode(userDto.getPassword()));

            Role role = roleRepo.findByName("ROLE_ADMIN").get();
            user.setRoles(Collections.singleton(role));

            userRepo.save(user);

            return new ResponseEntity<>("user registered successfully.", HttpStatus.CREATED);
        }

    }
}

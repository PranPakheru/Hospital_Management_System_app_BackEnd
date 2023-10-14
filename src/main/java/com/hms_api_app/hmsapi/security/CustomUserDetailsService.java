package com.hms_api_app.hmsapi.security;

import com.hms_api_app.hmsapi.config.BeanConfig;
import com.hms_api_app.hmsapi.entity.Role;
import com.hms_api_app.hmsapi.entity.User;
import com.hms_api_app.hmsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BeanConfig beanConfig;

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
      User user = userRepo.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("User name or email not found " + userNameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        List<SimpleGrantedAuthority> collect = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return collect;
    }
}

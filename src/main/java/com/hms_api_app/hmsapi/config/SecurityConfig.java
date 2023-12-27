package com.hms_api_app.hmsapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->
                        csrf
                                .disable())
                .authorizeHttpRequests((request)-> {

                            request
                                    .requestMatchers("/**").permitAll();
                             request
                                     .anyRequest().authenticated();
                        }
                );
        return http.build();
    }
//    request
//            .requestMatchers("/api-HMS/**").permitAll();
//                            request
//                                    .requestMatchers("/api-HMS/patient/**").permitAll();

}

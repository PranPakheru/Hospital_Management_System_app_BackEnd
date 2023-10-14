package com.hms_api_app.hmsapi.security;

import com.hms_api_app.hmsapi.config.BeanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    private CustomAuthSuccessHandler successHandler;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private BeanConfig beanConfig;

    @Bean
    protected DaoAuthenticationProvider getAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(beanConfig.passwordEncoder());
        return daoAuthenticationProvider;
    }

//    @Bean
//    protected AuthenticationManager authenticationManager() throws Exception{
//        return authenticationManager();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf->
                        csrf
                                .disable())
                .authorizeHttpRequests(request->
                        request
                                .requestMatchers("/auth/sign-up/**").permitAll()
                                .requestMatchers("/**").permitAll()
                )
                .formLogin(login->
                        login
                                .loginPage("/signIn").loginProcessingUrl("/userLogin")
                                .successHandler(successHandler)
                                .permitAll()
                );
        return http.build();
    }


}

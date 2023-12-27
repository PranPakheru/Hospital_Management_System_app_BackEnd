package com.hms_api_app.hmsapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
public class DataSourceConfig {

    //database configuration.
    @Value("${spring.datasource.url}")
    private String dbURL;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dmDataSource = new DriverManagerDataSource();
        dmDataSource.setUrl(dbURL);
        dmDataSource.setUsername(dbUsername);
        dmDataSource.setPassword(dbPassword);
        dmDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return dmDataSource;
    }

    
}

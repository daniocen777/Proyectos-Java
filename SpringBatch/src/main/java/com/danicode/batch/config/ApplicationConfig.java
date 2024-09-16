package com.danicode.batch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        managerDataSource.setUrl("jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima&useSSL=false");
        managerDataSource.setUsername("example-user");
        managerDataSource.setPassword("user-password");

        return managerDataSource;
    }
}

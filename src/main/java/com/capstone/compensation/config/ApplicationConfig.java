package com.capstone.compensation.config;

import com.capstone.compensation.service.RegisterUserService;
import com.capstone.compensation.service.impl.RegisterUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean("registerUserService")
    public RegisterUserService  getRegisterUserService()
    {
        return new RegisterUserServiceImpl();
    }

}

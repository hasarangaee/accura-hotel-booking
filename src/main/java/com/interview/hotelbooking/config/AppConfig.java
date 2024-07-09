package com.interview.hotelbooking.config;

import com.interview.hotelbooking.service.DefaultDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final DefaultDataService service;

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            service.createDefaultRoles();
            service.createDefaultAdminUser();
        };
    }
}

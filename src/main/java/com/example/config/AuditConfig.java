package com.example.config;

import com.example.auditor.AuditorAwareImpl;
import com.example.listener.AuditEmployeeListener;
import com.example.service.EntityUpdateStatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

    @Bean
    AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();
    }

}

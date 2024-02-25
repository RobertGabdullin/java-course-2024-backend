package edu.java.configuration;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private final ApplicationConfig applicationConfig;

    @Autowired
    public AppConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Bean
    public Duration interval() {
        return applicationConfig.scheduler().interval();
    }
}

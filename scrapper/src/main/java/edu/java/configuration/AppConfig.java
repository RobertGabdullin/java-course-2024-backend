package edu.java.configuration;

import edu.java.configuration.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

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

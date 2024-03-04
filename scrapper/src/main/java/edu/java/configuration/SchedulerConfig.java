package edu.java.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    public ApplicationConfig.Scheduler scheduler() {
        return applicationConfig.scheduler();
    }

}

package edu.java.scheduler;

import edu.java.configuration.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class LinkUpdaterScheduler {

    private final ApplicationConfig.Scheduler scheduler;

    @Autowired
    public LinkUpdaterScheduler(ApplicationConfig applicationConfig) {
        this.scheduler = applicationConfig.scheduler();
    }

    @Scheduled(fixedDelayString = "#{@interval.toMillis()}")
    public void update(){
        System.out.println("Update method is used...");
    }

}

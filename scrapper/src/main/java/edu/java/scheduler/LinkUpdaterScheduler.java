package edu.java.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@EnableScheduling
@Component
public class LinkUpdaterScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkUpdaterScheduler.class);

    @Scheduled(fixedDelayString = "#{@interval.toMillis()}")
    public void update() {
        LOGGER.info("Update method is used...");
    }

}

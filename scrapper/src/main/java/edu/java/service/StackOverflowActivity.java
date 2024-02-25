package edu.java.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class StackOverflowActivity {

    @Getter private long last_activity_date;

    public OffsetDateTime getUpdate(){
        Instant instant = Instant.ofEpochSecond(last_activity_date);
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

}

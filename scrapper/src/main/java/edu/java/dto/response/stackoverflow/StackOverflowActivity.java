package edu.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class StackOverflowActivity {
    @JsonProperty("last_activity_date")
    private long lastActivityDate;

    public OffsetDateTime getUpdate() {
        Instant instant = Instant.ofEpochSecond(lastActivityDate);
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

}

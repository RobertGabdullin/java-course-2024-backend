package edu.java.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class StackOverflowResponse {
    @Getter private List<StackOverflowActivity> items;

    public OffsetDateTime getLastUpdate(){
        return items.getFirst().getUpdate();
    }


}

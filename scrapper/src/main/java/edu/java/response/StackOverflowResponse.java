package edu.java.response;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.Getter;

public class StackOverflowResponse {
    @Getter private List<StackOverflowActivity> items;

    public OffsetDateTime getLastUpdate() {
        return items.getFirst().getUpdate();
    }


}

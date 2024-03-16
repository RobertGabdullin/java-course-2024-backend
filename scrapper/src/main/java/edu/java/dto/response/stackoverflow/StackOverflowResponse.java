package edu.java.dto.response;

import java.time.OffsetDateTime;
import java.util.List;
import edu.java.dto.StackOverflowActivity;
import lombok.Getter;

public class StackOverflowResponse {
    @Getter private List<StackOverflowActivity> items;

    public OffsetDateTime getLastUpdate() {
        return items.getFirst().getUpdate();
    }


}

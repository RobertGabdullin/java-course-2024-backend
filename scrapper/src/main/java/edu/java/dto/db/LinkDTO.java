package edu.java.dto.db;

import java.time.OffsetDateTime;

public record LinkDTO (
    Long linkId,
    String url,
    OffsetDateTime updatedAt,
    OffsetDateTime checkedAt
) {}

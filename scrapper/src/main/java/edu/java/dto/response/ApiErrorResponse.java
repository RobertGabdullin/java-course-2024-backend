package edu.java.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema
public record ApiErrorResponse(
    String description,
    String code,
    String exceptionName,
    String exceptionMessage,
    List<String> stacktrace) {}

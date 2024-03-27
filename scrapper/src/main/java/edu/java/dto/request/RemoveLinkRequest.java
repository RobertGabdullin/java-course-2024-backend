package edu.java.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record RemoveLinkRequest(String link) {}

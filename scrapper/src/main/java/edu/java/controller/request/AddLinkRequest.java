package edu.java.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record AddLinkRequest(String link) {}

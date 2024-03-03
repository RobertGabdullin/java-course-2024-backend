package edu.java.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record LinkResponse(int id, String url) {}

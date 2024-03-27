package edu.java.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema
public record ListLinksResponse(List<LinkResponse> links, int size) {}

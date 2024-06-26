package edu.java.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.net.URI;

@Schema
public record LinkResponse(long id, URI url) {}

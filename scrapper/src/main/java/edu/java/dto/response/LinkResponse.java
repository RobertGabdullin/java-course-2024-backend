package edu.java.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.net.URI;

@Schema
public record LinkResponse(int id, URI url) {}

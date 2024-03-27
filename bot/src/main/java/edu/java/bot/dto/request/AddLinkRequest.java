package edu.java.bot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.net.URI;

@Schema
public record AddLinkRequest(URI link) {}

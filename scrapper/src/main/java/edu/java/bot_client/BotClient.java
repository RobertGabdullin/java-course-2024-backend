package edu.java.bot_client;

import edu.java.controller.request.UpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class BotClient {

    private final WebClient webClient;

    public BotClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public BotClient(WebClient.Builder webClientBuilder, String url) {
        this.webClient = webClientBuilder.baseUrl(url).build();
    }

    public ResponseEntity<Object> sendUpdate(UpdateRequest update) {
        return webClient.post()
            .uri("/updates")
            .body(BodyInserters.fromValue(update))
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

}

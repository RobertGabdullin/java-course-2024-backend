package edu.java.service;

import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowClient {
    private final WebClient webClient;

    public StackOverflowClient(WebClient.Builder webClientBuilder) {
        this(webClientBuilder, "https://api.stackexchange.com/2.3");
    }

    public StackOverflowClient(WebClient.Builder webClientBuilder, String baseUrl){
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }
}

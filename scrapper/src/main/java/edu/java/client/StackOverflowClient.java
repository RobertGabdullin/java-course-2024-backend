package edu.java.service;

import edu.java.configuration.ApplicationConfig;
import edu.java.data_transfer.StackOverflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowClient {

    private final WebClient webClient;

    @Autowired
    public StackOverflowClient(ApplicationConfig applicationConfig, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(applicationConfig.stackOverflowBaseUrl()).build();
    }

    public StackOverflowClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public StackOverflowResponse lastUpdate(long id) {
        String url = String.format("/questions/" + id + "?site=stackoverflow");
        StackOverflowResponse response = webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(StackOverflowResponse.class)
            .block();
        return response;
    }

}

package edu.java.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

public class GitHubClient {
    private final WebClient webClient;

    public GitHubClient(WebClient.Builder webClientBuilder) {
        this(webClientBuilder, "https://api.github.com");
    }

    public GitHubClient(WebClient.Builder webClientBuilder, String baseUrl){
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public List<GitHubActivity> getListUpdates(String owner, String repo) {
        String url = String.format("/repos/%s/%s/activity", owner, repo);
        Mono<List<GitHubActivity>> response = webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<GitHubActivity>>() {})
            .log();

        return response.block();
    }

}

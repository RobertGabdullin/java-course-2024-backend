package edu.java.client;

import edu.java.dto.response.GitHubActivityResponse;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

public class GitHubClient {
    private final WebClient webClient;

    public GitHubClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public List<GitHubActivityResponse> getListUpdates(String owner, String repo) {
        String url = String.format("/repos/%s/%s/activity", owner, repo);
        List<GitHubActivityResponse> response =
            webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GitHubActivityResponse>>() {})
                .block();

        return response;
    }


}

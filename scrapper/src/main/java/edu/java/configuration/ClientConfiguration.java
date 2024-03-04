package edu.java.configuration;

import edu.java.service.GitHubClient;
import edu.java.service.StackOverflowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Bean
    public GitHubClient gitHubClient(ApplicationConfig applicationConfig, WebClient.Builder webClientBuilder) {
        return new GitHubClient(applicationConfig, webClientBuilder);
    }

    @Bean
    public StackOverflowClient stackOverflowClient(
        ApplicationConfig applicationConfig,
        WebClient.Builder webClientBuilder) {
        return new StackOverflowClient(applicationConfig, webClientBuilder);
    }

}

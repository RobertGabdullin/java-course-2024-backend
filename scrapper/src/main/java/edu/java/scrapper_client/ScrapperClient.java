package edu.java.scrapper_client;

import edu.java.controller.request.AddLinkRequest;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class ScrapperClient {

    private final WebClient webClient;
    private final static String LINKS = "/links";
    private final static String TGCHATID = "TgChatId";
    private final static String IDURL = "/tg-chat/{id}";

    public ScrapperClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8090").build();
    }

    public ScrapperClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public ResponseEntity<Object> registerChat(Long id) {
        return webClient.post()
            .uri(IDURL, id)
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<Object> deleteChat(Long id) {
        return webClient.delete()
            .uri(IDURL, id)
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<Object> getAllLinks(Long chatId) {
        return webClient.get()
            .uri(LINKS)
            .header(TGCHATID, chatId.toString())
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<Object> addLink(Long chatId, AddLinkRequest request) {
        return webClient.post()
            .uri(LINKS)
            .header(TGCHATID, chatId.toString())
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .toEntity(Object.class)
            .block();
    }

    public ResponseEntity<Object> removeLink(Long chatId, URI uri) {
        return webClient.delete()
            .uri(LINKS)
            .header(TGCHATID, chatId.toString())
            .header("Delete-Link", uri.toString())
            .retrieve()
            .toEntity(Object.class)
            .block();
    }
}


package edu.java.controller;

import edu.java.controller.request.AddLinkRequest;
import edu.java.controller.response.LinkResponse;
import edu.java.controller.response.ListLinksResponse;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<Object> registerChat(@PathVariable("id") Long id) {
        // some logic
        return ResponseEntity.ok("Чат зарегестрирован");
    }

    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<Object> deleteChat(@PathVariable("id") Long id) {
        // ...
        return ResponseEntity.ok("Чат успешно удален");
    }

    @GetMapping(value = "/links", produces = "application/json")
    public ResponseEntity<Object> getAllLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        // ...
        ListLinksResponse res = new ListLinksResponse(null, 1);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/links")
    public ResponseEntity<Object> addLink(
        @RequestHeader("Tg-Chat-Id") Long chatId,
        @RequestBody AddLinkRequest request) {
        // ...
        return ResponseEntity.ok("Ссылка успешно добавлена");
    }

    @DeleteMapping(value = "/links", produces = "application/json")
    public ResponseEntity<Object> removeLink(
        @RequestHeader("Tg-Chat-Id") Long chatId,
        @RequestHeader("Delete-Link") URI request) {
        // ...
        LinkResponse res = new LinkResponse(0, URI.create("123"));
        return ResponseEntity.ok().body(res);
    }

}

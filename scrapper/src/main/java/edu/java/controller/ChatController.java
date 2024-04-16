package edu.java.controller;

import edu.java.dto.db.LinkDTO;
import edu.java.dto.request.AddLinkRequest;
import edu.java.dto.response.LinkResponse;
import edu.java.dto.response.ListLinksResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import edu.java.service.ChatService;
import edu.java.service.LinkService;
import edu.java.service.jdbc.JdbcChatService;
import edu.java.service.jdbc.JdbcLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    ChatService chatService;
    LinkService linkService;

    @Autowired
    ChatController(JdbcChatService jdbcChatService, JdbcLinkService jdbcLinkService){
        chatService = jdbcChatService;
        linkService = jdbcLinkService;
    }

    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<Object> registerChat(@PathVariable("id") Long id) {
        chatService.register(id);
        return ResponseEntity.ok("Чат зарегестрирован");
    }

    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<Object> deleteChat(@PathVariable("id") Long id) {
        chatService.unregister(id);
        return ResponseEntity.ok("Чат успешно удален");
    }

    @GetMapping(value = "/links", produces = "application/json")
    public ResponseEntity<Object> getAllLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        List<LinkDTO> list = linkService.listAll(chatId);
        List<LinkResponse> res = new ArrayList<LinkResponse>();
        for(LinkDTO i : list)
            res.add(new LinkResponse(i.linkId(), URI.create(i.url())));
        ListLinksResponse ans = new ListLinksResponse(res, res.size());
        return ResponseEntity.ok().body(ans);
    }

    @PostMapping("/links")
    public ResponseEntity<Object> addLink(
        @RequestHeader("Tg-Chat-Id") Long chatId,
        @RequestBody AddLinkRequest request) {
        linkService.add(chatId, request.link());
        return ResponseEntity.ok("Ссылка успешно добавлена");
    }

    @DeleteMapping(value = "/links", produces = "application/json")
    public ResponseEntity<Object> removeLink(
        @RequestHeader("Tg-Chat-Id") Long chatId,
        @RequestHeader("Delete-Link") URI request) {

        LinkDTO deleted = linkService.remove(chatId, request);
        LinkResponse res = new LinkResponse(deleted.linkId(), request);
        return ResponseEntity.ok().body(res);
    }

}

package edu.java.controller;

import edu.java.controller.exception.BadRequestException;
import edu.java.controller.request.AddLinkRequest;
import edu.java.controller.request.RemoveLinkRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<Object> registerChat(@PathVariable("id") Long id) {
        return ResponseEntity.ok("Chat registered successfully");
    }

    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<Object> deleteChat(@PathVariable("id") Long id) {
        // ...
        return ResponseEntity.ok("Chat deleted successfully");
    }

    @GetMapping("/links")
    public ResponseEntity<Object> getAllLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        // ...
        return ResponseEntity.ok("All links retrieved successfully");
    }

    @PostMapping("/links")
    public ResponseEntity<Object> addLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody AddLinkRequest request) {
        // ...
        return ResponseEntity.ok("Link added successfully");
    }

    @DeleteMapping("/links")
    public ResponseEntity<Object> removeLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody RemoveLinkRequest request) {
        // ...
        return ResponseEntity.ok("Link removed successfully");
    }

}

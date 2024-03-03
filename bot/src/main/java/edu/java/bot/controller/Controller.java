package edu.java.bot.controller;

import edu.java.bot.controller.request.UpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/updates")
    public ResponseEntity<Object> processUpdate(@RequestBody UpdateRequest linkUpdate) {
        return ResponseEntity.ok("Обновление обработано");
    }


}

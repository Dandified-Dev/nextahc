package dev.dandified.backend.controllers;


import dev.dandified.backend.models.Message;
import dev.dandified.backend.services.MessageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return new ResponseEntity<List<Message>>(messageService.allMessages(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> getSingel(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Message>>(messageService.single(id), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Message> createMessage(@RequestBody Map<String, String> payload) throws ParseException {
        return new ResponseEntity<>(messageService.createMessage(payload.get("message"), payload.get("userId"), payload.get("chatId")), HttpStatus.CREATED);
    }
}

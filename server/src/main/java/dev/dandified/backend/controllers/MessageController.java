package dev.dandified.backend.controllers;


import dev.dandified.backend.models.Message;
import dev.dandified.backend.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
    public ResponseEntity<Optional<Message>> getSingleMessage(@PathVariable String id){
        return new ResponseEntity<>(messageService.single(id), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Message> createMessage(@RequestBody Map<String, String> payload) throws ParseException {
        return new ResponseEntity<>(messageService.createMessage(payload.get("message"), payload.get("userId"), payload.get("chatId")), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Message> deleteMessage(@RequestBody Map<String, String> payload) {
        String messageId = payload.get("messageId");

        if (messageId == null) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if messageId is missing
        }

        try {
            Message deletedMessage = messageService.deleteMessage(messageId);
            return ResponseEntity.ok(deletedMessage); // Return 200 OK with the deleted message
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the message is not found
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Return 500 Internal Server Error for other exceptions
        }
    }
}

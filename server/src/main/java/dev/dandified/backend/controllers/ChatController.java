package dev.dandified.backend.controllers;

import dev.dandified.backend.models.Chat;
import dev.dandified.backend.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<List<Chat>> getAllMessages() {
        return new ResponseEntity<>(chatService.allChats(), HttpStatus.OK);
    }

    @PostMapping("/addParticipants")
    public ResponseEntity<Chat> addParticipants(@RequestBody Map<String, Object> payload) {
        String chatId = (String) payload.get("chatId");
        List<String> participantIds = new ArrayList<>();
        if (payload.get("participantIds") instanceof List) {
            for (Object id : (List<?>) payload.get("participantIds")) {
                if (id instanceof String) {
                    participantIds.add((String) id);
                } else if (id instanceof Map) {
                    Map<?, ?> idMap = (Map<?, ?>) id;
                    if (idMap.containsKey("id")) {
                        participantIds.add((String) idMap.get("id"));
                    }
                }
            }
        }
        try {
            Chat updatedChat = chatService.addParticipants(chatId, participantIds);
            return new ResponseEntity<>(updatedChat, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

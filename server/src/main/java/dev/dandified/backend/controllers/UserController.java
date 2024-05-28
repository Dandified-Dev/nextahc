package dev.dandified.backend.controllers;

import dev.dandified.backend.models.Message;
import dev.dandified.backend.models.User;
import dev.dandified.backend.services.MessageService;
import dev.dandified.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.allUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> payload){
        return new ResponseEntity<>(userService.createUser(payload.get("password"), payload.get("password"), payload.get("picture                  ")), HttpStatus.CREATED);

    }
}

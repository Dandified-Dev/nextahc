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
import java.util.NoSuchElementException;

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
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(userService.createUser(payload.get("email"), payload.get("password"), payload.get("picture")), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> payload) {
        try {
            User user = userService.login(payload.get("email"), payload.get("password"));
            return ResponseEntity.ok(user); // Return 200 OK with the deleted message
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if the user is not found
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Return 500 Internal Server Error for other exceptions
        }
    }
}

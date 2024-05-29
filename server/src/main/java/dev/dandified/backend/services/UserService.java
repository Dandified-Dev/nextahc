package dev.dandified.backend.services;

import dev.dandified.backend.models.Chat;
import dev.dandified.backend.models.User;
import dev.dandified.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> single(String id) {
        return userRepository.findById(id);
    }

    public User createUser(String email, String password, String picture) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        return userRepository.insert(new User(email, hashedPassword, LocalDate.now(), null, "user", picture));
    }

    public User login(String email, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Fetch the user by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            // Handle user not found
            throw new IllegalArgumentException("Invalid email or password");
        }

        // Check if the provided password matches the stored hashed password
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            // Handle incorrect password
            throw new NoSuchElementException("Invalid email or password");
        }
    }
}


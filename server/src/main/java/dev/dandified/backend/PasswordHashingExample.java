package dev.dandified.backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashingExample {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "test";
        String hashedPassword = passwordEncoder.encode(plainPassword);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}

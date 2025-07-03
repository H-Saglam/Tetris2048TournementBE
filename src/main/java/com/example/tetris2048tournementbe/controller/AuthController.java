package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.dto.AuthRequest;
import com.example.tetris2048tournementbe.model.User;
import com.example.tetris2048tournementbe.repo.UserRepo;
import com.example.tetris2048tournementbe.service.AuthService;
import com.example.tetris2048tournementbe.service.JwtService;
import com.example.tetris2048tournementbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody Map<String, String> request) {
        try {
            // Varsayılan olarak username ve password bekleniyor
            String username = request.get("username");
            String password = request.get("password");
            authService.createUser(username, password);
            return new ResponseEntity<>("User created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthRequest request) {
        try {
            Map<String, String> map = authService.login(request);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("token") String token) {
        boolean isVerified = authService.verifyUser(token);
        if (isVerified) {
            return "Email verified successfully.";
        } else {
            return "Invalid or expired token.";
        }
    }

    @GetMapping("/test")
    public User returnUser() {
        String username = jwtService.extractUser(jwtService.getToken());
        User user = userRepo.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}


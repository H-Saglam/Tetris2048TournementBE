package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.dto.AuthRequest;
import com.example.tetris2048tournementbe.repo.UserRepo;
import com.example.tetris2048tournementbe.service.AuthService;
import com.example.tetris2048tournementbe.service.JwtService;
import com.example.tetris2048tournementbe.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

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

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String username = jwtService.extractUser(token);
                return ResponseEntity.ok(Map.of("valid", true, "username", username));
            } catch (Exception e) {
                return ResponseEntity.ok(Map.of("valid", false));
            }
        }

        return ResponseEntity.ok(Map.of("valid", false));
    }
}


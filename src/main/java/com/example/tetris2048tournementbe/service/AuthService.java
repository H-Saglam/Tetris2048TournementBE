package com.example.tetris2048tournementbe.service;

import com.example.tetris2048tournementbe.dto.AuthRequest;
import com.example.tetris2048tournementbe.enums.RoleEnum;
import com.example.tetris2048tournementbe.model.User;
import com.example.tetris2048tournementbe.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthService {
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepo userRepository;
    private  Map<String, User> tokenStorage = new HashMap<>();
    @Autowired
    private  UserService userService;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;

    public void createUser(String username, String password) {
        userRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .ifPresent(user -> {
                    throw new IllegalStateException("User already exists: " + user.getUsername());
                });
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        newUser.setRole(RoleEnum.USER);
        userRepository.save(newUser);
    }

    public Map<String, String> login(AuthRequest request){
        Optional<User> userOpt = userService.getByUsername(request.getUsername());
        User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(request.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", user.getUsername());
            return response;
        }
        throw new UsernameNotFoundException("Username can not be found or the password is incorrect");
    }

    public String createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        tokenStorage.put(token, user);
        return token;
    }

    public boolean verifyUser(String token) {
        User user = tokenStorage.get(token);
        if (user != null) {
            userRepository.save(user);
            tokenStorage.remove(token);
            return true;
        }
        return false;
    }
}


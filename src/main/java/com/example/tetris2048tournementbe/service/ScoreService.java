package com.example.tetris2048tournementbe.service;

import com.example.tetris2048tournementbe.dto.ScoreRequest;
import com.example.tetris2048tournementbe.exception.UserNotFoundException;
import com.example.tetris2048tournementbe.model.Score;
import com.example.tetris2048tournementbe.model.User;
import com.example.tetris2048tournementbe.repo.ScoreRepo;
import com.example.tetris2048tournementbe.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ScoreService {

    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final ScoreRepo scoreRepo;

    public void createScore(ScoreRequest score) {
        String username = jwtService.extractUser(jwtService.getToken());
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Score newScore = new Score();
        newScore.setScore(score.getScore());
        newScore.setUser(user);
        newScore.setCreatedAt(LocalDateTime.now());
        scoreRepo.save(newScore);
    }
}

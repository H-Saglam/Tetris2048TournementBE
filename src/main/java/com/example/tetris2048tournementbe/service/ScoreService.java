package com.example.tetris2048tournementbe.service;

import com.example.tetris2048tournementbe.dto.ScoreRequest;
import com.example.tetris2048tournementbe.exception.UserNotFoundException;
import com.example.tetris2048tournementbe.model.Score;
import com.example.tetris2048tournementbe.model.User;
import com.example.tetris2048tournementbe.repo.ScoreRepo;
import com.example.tetris2048tournementbe.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScoreService {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  ScoreRepo scoreRepo;

    public void createScore(ScoreRequest score) {
        String username = jwtService.getUsernameFromJwt();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Score newScore = new Score();
        newScore.setScore(score.getScore());
        newScore.setUser(user);
        newScore.setCreatedAt(LocalDateTime.now());
        scoreRepo.save(newScore);
    }

    public List<Score> getScoresByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return scoreRepo.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Score> getTop10Scores() {
        return scoreRepo.findTop10ByOrderByScoreDesc();
    }
}

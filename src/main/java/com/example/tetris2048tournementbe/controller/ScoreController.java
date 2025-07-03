package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.dto.ScoreRequest;
import com.example.tetris2048tournementbe.model.Score;
import com.example.tetris2048tournementbe.repo.ScoreRepo;
import com.example.tetris2048tournementbe.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreRepo scoreRepo;
    private final ScoreService scoreService;

    @PostMapping("/create")
    public String createScore(@RequestBody ScoreRequest score) {
        System.out.println("Received score: " + score);
        scoreService.createScore(score);
        System.out.println("Score created successfully");
        return "Score created successfully";
    }

    @GetMapping
    public List<Score> getAllScores() {
        return scoreRepo.findAll();
    }

    @GetMapping("/{id}")
    public Score getScoreById(@PathVariable Long id) {
        return scoreRepo.findById(id).orElse(null);
    }
}

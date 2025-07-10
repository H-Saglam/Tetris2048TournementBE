package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.dto.ScoreRequest;
import com.example.tetris2048tournementbe.model.Score;
import com.example.tetris2048tournementbe.repo.ScoreRepo;
import com.example.tetris2048tournementbe.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @PostMapping("/create")
    public String createScore(@RequestBody ScoreRequest score) {
        System.out.println("Received score: " + score);
        scoreService.createScore(score);
        System.out.println("Score created successfully");
        return "Score created successfully";
    }


    @GetMapping("/user/{username}")
    public List<Score> getScoresByUsername(@PathVariable String username) {
        return scoreService.getScoresByUsername(username);
    }

    @GetMapping("/top10")
    public List<Score> getTop10Scores() {
        return scoreService.getTop10Scores();
    }
}

package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.model.TournamentScore;
import com.example.tetris2048tournementbe.repo.TournamentScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tournament-scores")
public class TournamentScoreController {
    @Autowired
    private TournamentScoreRepo tournamentScoreRepo;

    @GetMapping
    public List<TournamentScore> getAllTournamentScores() {
        return tournamentScoreRepo.findAll();
    }

    @GetMapping("/{id}")
    public TournamentScore getTournamentScoreById(@PathVariable Long id) {
        return tournamentScoreRepo.findById(id).orElse(null);
    }
}


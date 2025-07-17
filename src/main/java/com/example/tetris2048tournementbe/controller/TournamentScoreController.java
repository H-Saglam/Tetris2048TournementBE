package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.dto.TournamentScoreRequest;
import com.example.tetris2048tournementbe.model.TournamentScore;
import com.example.tetris2048tournementbe.repo.TournamentScoreRepo;
import com.example.tetris2048tournementbe.service.TournamentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tournament-scores")
public class TournamentScoreController {
    @Autowired
    private TournamentScoreRepo tournamentScoreRepo;
    @Autowired
    private TournamentScoreService tournamentScoreService;

    @GetMapping
    public List<TournamentScore> getAllTournamentScores() {
        return tournamentScoreRepo.findAll();
    }

    @GetMapping("/{id}")
    public TournamentScore getTournamentScoreById(@PathVariable Long id) {
        return tournamentScoreRepo.findById(id).orElse(null);
    }
    @PostMapping("/create")
    public void createTournamentScore(@RequestBody TournamentScoreRequest tournamentScore) {
        tournamentScoreService.createTournamentScore(tournamentScore);
    }
    @GetMapping("/tournament/{tournamentId}")
    public List<TournamentScore> getScoresByTournamentId(@PathVariable Long tournamentId) {
        return tournamentScoreService.getScoresByTournamentId(tournamentId);
    }
}


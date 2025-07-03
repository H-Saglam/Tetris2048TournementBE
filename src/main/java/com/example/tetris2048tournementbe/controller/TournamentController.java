package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.model.Tournament;
import com.example.tetris2048tournementbe.repo.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    @Autowired
    private TournamentRepo tournamentRepo;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentRepo.findById(id).orElse(null);
    }
}


package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.dto.TournamentRequest;
import com.example.tetris2048tournementbe.model.Tournament;
import com.example.tetris2048tournementbe.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @GetMapping("/all")
    public List<Tournament> getAllTournaments() {
        return  tournamentService.getAllTournaments();
    }

    @PostMapping("/create")
    public void createTournament(@RequestBody TournamentRequest tournamentRequest) {
        tournamentService.createTournament(tournamentRequest);
        System.out.println("adsa");
    }
}


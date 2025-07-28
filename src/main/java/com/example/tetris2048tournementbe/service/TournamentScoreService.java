package com.example.tetris2048tournementbe.service;

import com.example.tetris2048tournementbe.dto.ScoreRequest;
import com.example.tetris2048tournementbe.dto.TournamentScoreRequest;
import com.example.tetris2048tournementbe.exception.UserNotFoundException;
import com.example.tetris2048tournementbe.model.Score;
import com.example.tetris2048tournementbe.model.Tournament;
import com.example.tetris2048tournementbe.model.TournamentScore;
import com.example.tetris2048tournementbe.model.User;
import com.example.tetris2048tournementbe.repo.TournamentRepo;
import com.example.tetris2048tournementbe.repo.TournamentScoreRepo;
import com.example.tetris2048tournementbe.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentScoreService {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  TournamentRepo tournamentRepo;
    @Autowired
    private  TournamentScoreRepo tournamentScoreRepo;

    public void createTournamentScore(TournamentScoreRequest score) {
        String username = jwtService.getUsernameFromJwt();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Tournament tournament = tournamentRepo.findById(score.getTournamentId())
                .orElseThrow(() -> new UserNotFoundException("Tournament not found with ID: " + score.getTournamentId()));

        TournamentScore newScore = new TournamentScore();
        newScore.setScore(score.getScore());
        newScore.setUser(user);
        newScore.setCreatedAt(LocalDateTime.now());
        newScore.setTournament(tournament);
        tournamentScoreRepo.save(newScore);
    }
    public List<TournamentScore> getScoresByTournamentId(Long tournamentId) {
        Tournament tournament = tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new UserNotFoundException("Tournament not found with ID: " + tournamentId));
        return tournamentScoreRepo.getTopScoresByTournamentId(tournamentId);
    }
}

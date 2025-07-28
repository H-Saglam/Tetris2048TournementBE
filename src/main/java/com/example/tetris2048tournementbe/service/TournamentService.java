package com.example.tetris2048tournementbe.service;

import com.example.tetris2048tournementbe.dto.TournamentRequest;
import com.example.tetris2048tournementbe.exception.UserNotFoundException;
import com.example.tetris2048tournementbe.model.Tournament;
import com.example.tetris2048tournementbe.model.User;
import com.example.tetris2048tournementbe.repo.TournamentRepo;
import com.example.tetris2048tournementbe.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService {
    private final UserRepo userRepo;
    private final TournamentRepo tournamentRepo;
    private final JwtService jwtService;

    public List<Tournament> getAllTournaments() {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        return tournamentRepo.findTournamentsCreatedInLastHour(oneHourAgo);
    }

    public void createTournament(TournamentRequest tournamentRequest) {
        System.out.println("A");
        String username = jwtService.extractUser(jwtService.getToken());
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        System.out.println("B");
        Tournament tournament = new Tournament();
        tournament.setCreatedBy(user);
        tournament.setCreatedAt(LocalDateTime.now());
        tournament.setName(tournamentRequest.getName());
        tournamentRepo.save(tournament);


    }
}

package com.example.tetris2048tournementbe.repo;

import com.example.tetris2048tournementbe.model.TournamentScore;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TournamentScoreRepo extends JpaRepository<TournamentScore, Long> {
}

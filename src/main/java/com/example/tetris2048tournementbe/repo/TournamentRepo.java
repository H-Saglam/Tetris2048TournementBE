package com.example.tetris2048tournementbe.repo;

import com.example.tetris2048tournementbe.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepo extends JpaRepository<Tournament, Long> {
}


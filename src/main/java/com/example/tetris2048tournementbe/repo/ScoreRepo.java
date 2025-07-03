package com.example.tetris2048tournementbe.repo;

import com.example.tetris2048tournementbe.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepo extends JpaRepository<Score, Long> {
}


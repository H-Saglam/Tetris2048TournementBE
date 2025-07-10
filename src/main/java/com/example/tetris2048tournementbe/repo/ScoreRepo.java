package com.example.tetris2048tournementbe.repo;

import com.example.tetris2048tournementbe.model.Score;
import com.example.tetris2048tournementbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score, Long> {
    List<Score> findByUserOrderByCreatedAtDesc(User user);

    @Query("SELECT s FROM Score s ORDER BY s.score DESC LIMIT 10")
    List<Score> findTop10ByOrderByScoreDesc();
}

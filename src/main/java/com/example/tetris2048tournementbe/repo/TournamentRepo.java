package com.example.tetris2048tournementbe.repo;

import com.example.tetris2048tournementbe.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TournamentRepo extends JpaRepository<Tournament, Long> {
    @Query("SELECT t FROM Tournament t WHERE t.createdAt >= :oneHourAgo")
    List<Tournament> findTournamentsCreatedInLastHour(@Param("oneHourAgo") LocalDateTime oneHourAgo);
}

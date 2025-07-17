package com.example.tetris2048tournementbe.repo;

import com.example.tetris2048tournementbe.model.Tournament;
import com.example.tetris2048tournementbe.model.TournamentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TournamentScoreRepo extends JpaRepository<TournamentScore, Long> {

    @Query("SELECT ts FROM TournamentScore ts WHERE ts.tournament.id = :tournamentId ORDER BY ts.score DESC LIMIT 10")
    List<TournamentScore> getTopScoresByTournamentId(@Param("tournamentId") Long tournamentId);

}

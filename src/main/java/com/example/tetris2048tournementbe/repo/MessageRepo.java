package com.example.tetris2048tournementbe.repo;

import com.example.tetris2048tournementbe.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer> {
    List<Message> findByTournament_Id(Long tournamentId);
}

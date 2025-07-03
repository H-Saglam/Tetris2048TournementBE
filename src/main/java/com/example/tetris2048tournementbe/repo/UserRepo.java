package com.example.tetris2048tournementbe.repo;

import com.example.tetris2048tournementbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

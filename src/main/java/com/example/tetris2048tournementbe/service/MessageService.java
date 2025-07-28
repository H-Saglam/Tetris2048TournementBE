package com.example.tetris2048tournementbe.service;

import com.example.tetris2048tournementbe.dto.MessageRequest;
import com.example.tetris2048tournementbe.exception.UserNotFoundException;
import com.example.tetris2048tournementbe.model.Message;
import com.example.tetris2048tournementbe.model.Tournament;
import com.example.tetris2048tournementbe.model.User;
import com.example.tetris2048tournementbe.repo.MessageRepo;
import com.example.tetris2048tournementbe.repo.TournamentRepo;
import com.example.tetris2048tournementbe.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  MessageRepo messageRepo;
    @Autowired
    private  TournamentRepo tournamentRepo;
    @Autowired
    private  SimpMessagingTemplate messagingTemplate;


    public void sendMessage(MessageRequest messageRequest, Long tournamentId) {

        String username = jwtService.getUsernameFromJwt();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Tournament tournament = tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new UserNotFoundException("Tournament not found with id: " + tournamentId));
        Message message = new Message();
        message.setUser(user);
        message.setTournament(tournament);
        message.setText(messageRequest.getMessage());
        message.setCreatedAt(LocalDateTime.now());
        messageRepo.save(message);

        // Standart topic pattern kullan
        String destination = "/topic/tournament-" + tournamentId + "-messages";
        System.out.println("Mesaj gönderiliyor - Destination: " + destination);
        System.out.println("Mesaj içeriği: " + message.getText());
        System.out.println("Gönderen kullanıcı: " + user.getUsername());

        messagingTemplate.convertAndSend(destination, message);

        System.out.println("Mesaj gönderildi!");
    }
    public List<Message> getTournamentMessages(Long tournamentId) {
        return messageRepo.findByTournament_Id(tournamentId);
    }
}

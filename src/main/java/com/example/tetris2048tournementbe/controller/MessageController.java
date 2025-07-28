package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.dto.MessageRequest;
import com.example.tetris2048tournementbe.model.Message;
import com.example.tetris2048tournementbe.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message-send/{tournament_id}" )
    public ResponseEntity<String> sendTestNotification(@RequestBody MessageRequest messageRequest,
                                                       @PathVariable("tournament_id") Long tournamentId) {
        messageService.sendMessage(messageRequest, tournamentId);
        return ResponseEntity.ok("message send");
    }

    @GetMapping("/get-messages/{tournament_id}")
    public List<Message> getMessage(@PathVariable("tournament_id") Long tournamentId) {
        return messageService.getTournamentMessages(tournamentId);
    }

}

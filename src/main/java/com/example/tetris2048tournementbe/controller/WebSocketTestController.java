package com.example.tetris2048tournementbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class WebSocketTestController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send-test-message/{tournamentId}")
    public String sendTestMessage(@PathVariable Long tournamentId, @RequestParam String message) {
        String destination = "/topic/tournament-" + tournamentId + "-messages";

        Map<String, Object> testMessage = new HashMap<>();
        testMessage.put("text", message);
        testMessage.put("user", "test-user");
        testMessage.put("timestamp", System.currentTimeMillis());

        System.out.println("Test mesajı gönderiliyor - Destination: " + destination);
        System.out.println("Test mesaj içeriği: " + message);

        messagingTemplate.convertAndSend(destination, testMessage);

        System.out.println("Test mesajı gönderildi!");

        return "Test message sent to " + destination;
    }
}

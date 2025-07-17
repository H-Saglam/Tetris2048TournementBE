package com.example.tetris2048tournementbe.controller;

import com.example.tetris2048tournementbe.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/tournament-created")
    public ResponseEntity<String> sendTestNotification() {
        logger.info("NOTIFICATION_ENDPOINT_CALLED: Test notification endpoint called");
        System.out.println("Sending test notification to all users...");
        notificationService.tournamentCreatedNotification();
        return ResponseEntity.ok("Test notification sent to all users!");
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendCustomNotification(
            @RequestParam String message,
            @RequestParam(defaultValue = "info") String type) {
        notificationService.sendNotificationToAll(message, type);
        return ResponseEntity.ok("Notification sent to all users!");
    }
}

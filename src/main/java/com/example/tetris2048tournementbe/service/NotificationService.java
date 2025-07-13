package com.example.tetris2048tournementbe.service;

import com.example.tetris2048tournementbe.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotificationToAll(String message, String type) {
        Notification notification = new Notification(message, type);
        notification.setId(UUID.randomUUID().toString());

        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

    public void sendTestNotification() {
        sendNotificationToAll("Bu bir test bildirimidir!", "info");
    }
}

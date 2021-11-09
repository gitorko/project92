package com.demo.project92.config;

import java.time.LocalDateTime;

import com.demo.project92.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = headerAccessor.getUser().getName();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setFrom(username);
        chatMessage.setText("Joined chat!");
        chatMessage.setSentAt(LocalDateTime.now().toString());
        messagingTemplate.convertAndSend("/message", chatMessage);
        log.info("New web socket connection created for {}", username);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        log.info("Received a disconnection socket request");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = headerAccessor.getUser().getName();
        if (username != null) {
            log.info("User Disconnected : " + username);
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setFrom(username);
            chatMessage.setText("Left the chat!");
            chatMessage.setSentAt(LocalDateTime.now().toString());
            messagingTemplate.convertAndSend("/message", chatMessage);
            log.info("Connection disconnected");
        }
    }
}

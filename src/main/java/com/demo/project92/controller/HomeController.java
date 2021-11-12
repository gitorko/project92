package com.demo.project92.controller;

import java.time.LocalDateTime;

import com.demo.project92.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
class HomeController {

    @MessageMapping("/send/message")
    @SendTo("/message")
    public ChatMessage sendMessage(SimpMessageHeaderAccessor sha, ChatMessage chat) {
        chat.setFrom(sha.getUser().getName());
        chat.setSentAt(LocalDateTime.now().toString());
        log.info("Received message: {}", chat);
        return chat;
    }
}

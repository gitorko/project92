package com.demo.project92.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/message");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //SockJS is used to enable fallback options if browsers don’t support websocket.
        registry.addEndpoint("/chat-app")
                .setAllowedOrigins("http://localhost:4200")
                .setHandshakeHandler(new CustomHandshakeHandler()) // Set custom handshake handler
                .withSockJS();
    }
}

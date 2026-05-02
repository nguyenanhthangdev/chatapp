package com.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // nơi subscribe
        config.setApplicationDestinationPrefixes("/app"); // nơi gửi
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // ⚠️ CÁI NÀY PHẢI KHỚP
                .setAllowedOriginPatterns("*")
                .withSockJS(); // ⚠️ cần để dùng SockJS
    }
}
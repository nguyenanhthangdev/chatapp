package com.chatapp.controller;

import com.chatapp.dto.ChatMessageDTO;
import com.chatapp.entity.User;
import com.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final UserRepository userRepository; // 👈 thêm

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDTO send(ChatMessageDTO message) {

        System.out.println("📥 RECEIVED: " + message);

        // 🔥 LẤY USER TỪ DB
        User user = userRepository.findById(message.getSenderId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 SET TÊN TỪ DB
        message.setUsername(user.getUsername());

        return message;
    }
}
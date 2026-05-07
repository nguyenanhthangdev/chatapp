package com.chatapp.controller;

import com.chatapp.dto.ChatMessageDTO;
import com.chatapp.entity.Message;
import com.chatapp.entity.User;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final MessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDTO send(ChatMessageDTO message) {

        System.out.println("📥 RECEIVED: " + message);

        // 🔥 LƯU DB
        messageService.saveMessage(message);

        return message;
    }
}
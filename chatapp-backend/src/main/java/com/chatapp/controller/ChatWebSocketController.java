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

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final MessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDTO send(ChatMessageDTO message) {

        // 🔥 LƯU DB
        Message saved = messageService.saveFromSocket(message);

        // 🔥 trả lại DTO (có senderName, time,...)
        ChatMessageDTO response = new ChatMessageDTO();
        response.setChatRoomId(saved.getChatRoomId());
        response.setSenderId(saved.getSenderId());
        response.setUsername(saved.getUsername());
        response.setContent(saved.getContent());

        return response;
    }
}
package com.chatapp.controller;

import com.chatapp.dto.ChatMessageDTO;
import com.chatapp.dto.MessageDTO;
import com.chatapp.entity.ChatRoom;
import com.chatapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom() {
        return chatService.createChatRoom();
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChats() {
        return ResponseEntity.ok(chatService.getAllChats());
    }
}